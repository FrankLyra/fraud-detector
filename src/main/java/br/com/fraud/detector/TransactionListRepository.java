package br.com.fraud.detector;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    List<Transaction> transactionList;

    public TransactionListRepository(List<Transaction> transactionList) {
        Objects.requireNonNull(transactionList);
        this.transactionList = transactionList;
    }

    //método que busca uma transação por nome do cliente de origem
    @Override
    public Optional<Transaction> findTransactionByOrigin(String originName) {
        return transactionList.stream()
                .filter(transaction -> transaction.origin().name().equals(originName))
                .findFirst();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        Objects.requireNonNull(transaction);
        transactionList.add(transaction);
    }

}
