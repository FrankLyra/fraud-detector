package br.com.fraud.detector;

import java.io.IOException;

public class ConcorrenciaMain {
    public static void main(String[] args) {
        TransactionSqlRepository transactionSqlRepository = new TransactionSqlRepository();
        var transactionIngestor = new EfficientTransactionIngestor();
        
        try {
           transactionIngestor.getTransactionsStreamBatch("data/PS_20174392719_1491204439457_log.csv",
                    transactionSqlRepository::saveAll);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
