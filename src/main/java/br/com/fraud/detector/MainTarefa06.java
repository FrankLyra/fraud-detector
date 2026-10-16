package br.com.fraud.detector;
import java.io.IOException;
import java.util.List;

public class MainTarefa06 {

    public static void main(String[] args) {

        String fileName = "data/PS_20174392719_1491204439457_log.csv";


        try {
            long startTime = System.currentTimeMillis();
            String notFoundOriginName = "C1234567890";
            String existingOriginName ="C1666544295";
            TransactionIngestor transactionIngestor = new TransactionIngestor();
            List<Transaction> transactions = transactionIngestor.getTransactions(fileName);

            //TransactionListRepository transactionListRepository = new TransactionListRepository(transactions);
            TransactionRepository transactionRepository = new TransactionMapRepository(transactions);

            transactionRepository.findTransactionByOrigin(existingOriginName).ifPresentOrElse(
                    transaction -> System.out.println("Transação encontrada: " + transaction),
                    () -> System.out.println("Transação não encontrada para o cliente de origem " + existingOriginName)
            );

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
