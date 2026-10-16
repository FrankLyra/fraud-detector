package br.com.fraud.detector;

import java.math.BigDecimal;
import java.util.Objects;

public record Customer(String name,BigDecimal oldbalance,BigDecimal newbalance){

    public Customer{
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldbalance);
        Objects.requireNonNull(newbalance);

        if(oldbalance.signum() < 0 ) throw new IllegalArgumentException("O valor de oldbalance deve ser positivo ou 0 " + oldbalance);
        if(newbalance.signum() < 0 ) throw new IllegalArgumentException("O valor de newBalance deve ser positivo ou 0 " + newbalance);
        if(name.trim().isEmpty()) throw new IllegalArgumentException("O valor de nome deve ser informado");
    }

}
