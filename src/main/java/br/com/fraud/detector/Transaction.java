package br.com.fraud.detector;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        Customer origin,
        Customer destination,
        boolean isFraud,
        boolean isFlaggedFraud
) {
    public Transaction{
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(destination);

        if(step <= 0) throw new IllegalArgumentException("O valor de step deve ser positivo");
        if(amount.signum() <= 0) throw new IllegalArgumentException("O valor de amount deve ser positivo");

    }
}

