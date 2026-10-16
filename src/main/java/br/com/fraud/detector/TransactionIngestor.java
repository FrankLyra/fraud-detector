package br.com.fraud.detector;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {


    /* public static final int FRAUD_LIMIT = 50_000;*/
    public static final int FRAUD_LIMIT = 100_000;

    public List<Transaction> getTransactions(String file) throws IOException {
        Path path = Paths.get(file);
        //List<String> lines = Files.readAllLines(path);
        List<String> lines = Files.lines(path).toList();

        return lines.stream().skip(1)
                .limit(FRAUD_LIMIT)
                .map(this::parseTransaction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

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
