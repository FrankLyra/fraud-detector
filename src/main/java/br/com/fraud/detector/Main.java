package br.com.fraud.detector;


import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        // nome do arquivo (pode vir de args também)
        //String fileName = "data/PS_20174392719_1491204439457_log.csv";
        String fileName = "data/paysim_with_bad_data.csv";

        try {
            TransactionIngestor service = new TransactionIngestor();
            List<Transaction> transactions = service.getTransactions(fileName);
            //transactions.stream().limit(10).forEach(System.out::println);
            System.out.println(transactions.size());
            transactions.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
