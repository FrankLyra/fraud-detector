package br.com.fraud.detector;



import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;


public class i18nMain {

    public static void main(String[] args) {



        String fileName = "data/PS_20174392719_1491204439457_log.csv";
        TransactionReport report = new TransactionReport();
        TransactionReport.Statistics statistics = report.generateReport(fileName);

        Locale locale = Locale.of("pt");
        NumberFormat integerFormatter = NumberFormat.getIntegerInstance(locale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("BRL"));

        String fmtTotalTransactions = integerFormatter.format(statistics.totalTransactions());
        String fmtTotalFraud = integerFormatter.format(statistics.totalFraud());
        String fmtTotalAmount = currencyFormatter.format(statistics.totalAmount());

        var resourceBundle = ResourceBundle.getBundle("report", locale);
        String msgTotalTransaction = resourceBundle.getString("label.total.transactions");
        String msgTotalFraud = resourceBundle.getString("label.total.frauds");
        String msgTotalAmount = resourceBundle.getString("label.total.amount");

        System.out.printf(
                """
                         %s: %s
                         %s: %s
                         %s: %s
                        %n""".formatted(msgTotalTransaction, fmtTotalTransactions,
                                msgTotalFraud, fmtTotalFraud,
                                msgTotalAmount, fmtTotalAmount));
    }
}
