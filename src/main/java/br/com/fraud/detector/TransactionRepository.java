package br.com.fraud.detector;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    //método que busca uma transação por nome do cliente de origem
    Optional<Transaction> findTransactionByOrigin(String originName);
    void saveTransaction(Transaction transaction);
    void saveAll(List<Transaction> transactions);
}
