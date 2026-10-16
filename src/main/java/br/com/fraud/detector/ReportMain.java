package br.com.fraud.detector;

public class ReportMain {

    static void main() {
        String fileName = "data/PS_20174392719_1491204439457_log.csv";
        TransactionReport report = new TransactionReport();
        TransactionReport.Statistics statistics = report.generateReport(fileName);
        System.out.println("Total de transações: " + statistics.totalTransactions());
        System.out.println("Total de fraudes: " + statistics.totalFraud());
        System.out.println("Valor total: " + statistics.totalAmount());

    }
}
