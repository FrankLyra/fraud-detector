package br.com.fraud.detector;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EfficientTransactionIngestor {


    /* public static final int FRAUD_LIMIT = 50_000;*/
    public static final int FRAUD_LIMIT = 10_000;
    private static final int BATCH_SIZE = 5_000;


    public void getTransactionsStreamBatch(String file, Consumer<List<Transaction>> transactionConsumer) throws IOException {
        Path path = Paths.get(file);
        try (Stream<String> lines = Files.lines(path)) {
            int count = 0;

            Iterator<String> iterator = lines.iterator();
            if (iterator.hasNext()) {
                iterator.next();
            }

            List<String> batch = new ArrayList<>(BATCH_SIZE);
            while (iterator.hasNext()) {
                batch.add(iterator.next());
                count++;
                if(count >= FRAUD_LIMIT) {
                    break;
                }
                if (batch.size() == BATCH_SIZE) {
                    processBatch(batch, transactionConsumer);
                    batch.clear();
                }
            }
            processBatch(batch, transactionConsumer);
        }

    }

    private void processBatch(List<String> batch, Consumer<List<Transaction>> transactionConsumer) {
       List<Transaction> transactions = batch.stream()
                .map(this::parseTransaction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
       transactionConsumer.accept(transactions);
    }

    public void getTransactionsStream(String file, Consumer<Transaction> transactionConsumer) throws IOException {
        Path path = Paths.get(file);
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .limit(FRAUD_LIMIT)
                    .map(Optional::get)
                    .forEach(transactionConsumer);
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        try {
            String[] parts = line.split(",");
            int step = Integer.parseInt(parts[0]);
            TransactionType type = TransactionType.valueOf(parts[1]);
            BigDecimal amount = new BigDecimal(parts[2]);
            var origin = new Customer(parts[3], new BigDecimal(parts[4]), new BigDecimal(parts[5]));
            var destination = new Customer(parts[6], new BigDecimal(parts[7]), new BigDecimal(parts[8]));
            boolean isFraud = "1".equals(parts[9].trim()) || Boolean.parseBoolean(parts[9].trim());
            boolean isFlagFraud = "1".equals(parts[10].trim()) || Boolean.parseBoolean(parts[10].trim());


            return Optional.of(new Transaction(step,type,amount,origin,destination,isFraud,isFlagFraud));


        } catch (Exception e) {
            System.err.println("Erro ao fazer parse: " + line + "|" + e);
            return Optional.empty();
        }

    }




}
