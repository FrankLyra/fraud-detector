package br.com.fraud.detector;

import java.math.BigDecimal;
import java.util.Objects;

public record Customer(String name,BigDecimal oldBalance,BigDecimal newBalance){

    public Customer{
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if(oldBalance.signum() < 0 ) throw new IllegalArgumentException("O valor de oldBalance deve ser positivo ou 0 " + oldBalance);
        if(newBalance.signum() < 0 ) throw new IllegalArgumentException("O valor de newBalance deve ser positivo ou 0 " + newBalance);
        if(name.trim().isEmpty()) throw new IllegalArgumentException("O valor de nome deve ser informado");
    }

}
