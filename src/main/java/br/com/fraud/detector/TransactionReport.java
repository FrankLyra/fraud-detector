package br.com.fraud.detector;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {

    private record ReportTransaction(BigDecimal amount, boolean isFraud) {

    }

    public record Statistics(long totalTransactions, long totalFraud, BigDecimal totalAmount) {

        private final static Statistics ZERO = new Statistics(0, 0, BigDecimal.ZERO);

        private Statistics addReportTransaction(ReportTransaction transaction) {
            long newTotalTransactions = this.totalTransactions + 1;
            long newTotalFraud = this.totalFraud + (transaction.isFraud() ? 1 : 0);
            BigDecimal newTotalAmount = this.totalAmount.add(transaction.amount());
            return new Statistics(newTotalTransactions, newTotalFraud, newTotalAmount);
        }

        private Statistics add(Statistics other) {
            long newTotalTransactions = this.totalTransactions + other.totalTransactions;
            long newTotalFraud = this.totalFraud + other.totalFraud;
            BigDecimal newTotalAmount = this.totalAmount.add(other.totalAmount);
            return new Statistics(newTotalTransactions, newTotalFraud, newTotalAmount);
        }

    }

    public Statistics generateReport(String fileName) {
        Path path = Path.of(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .skip(1)
                    .map(this::parseReportTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(Statistics.ZERO, Statistics::addReportTransaction, Statistics::add);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<ReportTransaction> parseReportTransaction(String line) {
        try {
            String[] parts = line.split(",");
            BigDecimal amount = new BigDecimal(parts[2]);
            boolean isFraud = "1".equals(parts[9].trim()) || Boolean.parseBoolean(parts[9].trim());
            return Optional.of(new ReportTransaction(amount,isFraud));
        } catch (Exception e) {
            System.err.println("Erro ao fazer parse: " + line + "|" + e);
            return Optional.empty();
        }

    }
}
