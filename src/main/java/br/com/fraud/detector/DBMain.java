package br.com.fraud.detector;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class DBMain {

    public static void main(String[] args) {
        TransactionSqlRepository transactionSqlRepository = new TransactionSqlRepository();

        TransactionIngestor transactionIngestor = new TransactionIngestor();
        try {
          List<Transaction> transactions = transactionIngestor.getTransactions("data/PS_20174392719_1491204439457_log.csv");
            transactions.forEach(transactionSqlRepository::saveTransaction);
          System.out.println("Transações inseridas com sucesso: " + transactions.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        transactionSqlRepository.findTransactionByOrigin("C999000001")
                .ifPresentOrElse(
                        transaction -> System.out.println("Transação encontrada: " + transaction),
                        () -> System.out.println("Transação não encontrada para o cliente de origem C999000001")
                );
    }
}
