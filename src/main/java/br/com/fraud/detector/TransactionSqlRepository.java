package br.com.fraud.detector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;

public class TransactionSqlRepository implements TransactionRepository {



    @Override
    public Optional<Transaction> findTransactionByOrigin(String originName) {
        try (var connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TransactionQueries.FIND_TRANSACTION_BY_ORIGIN_NAME)) {
             preparedStatement.setString(1, originName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                Transaction transaction = mapResultSetToTransaction(resultSet);
                return Optional.of(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        try (var connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TransactionQueries.INSERT_TRANSACTION)) {
            preparedStatement.setInt(1, transaction.step());
            preparedStatement.setString(2, transaction.type().name());
            preparedStatement.setBigDecimal(3, transaction.amount());

            preparedStatement.setString(4, transaction.origin().name());
            preparedStatement.setBigDecimal(5, transaction.origin().oldBalance());
            preparedStatement.setBigDecimal(6, transaction.origin().newBalance());

            preparedStatement.setString(7, transaction.destination().name());
            preparedStatement.setBigDecimal(8, transaction.destination().oldBalance());
            preparedStatement.setBigDecimal(9, transaction.destination().newBalance());

            preparedStatement.setBoolean(10, transaction.isFraud());
            preparedStatement.setBoolean(11, transaction.isFlaggedFraud());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("Falha ao inserir a transação no banco de dados.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static Transaction mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        return new Transaction(
                resultSet.getInt("step"),
                TransactionType.valueOf(resultSet.getString("type")),
                resultSet.getBigDecimal("amount"),
                new Customer(
                        resultSet.getString("name_origin"),
                        resultSet.getBigDecimal("old_balance_origin"),
                        resultSet.getBigDecimal("new_balance_origin")
                ),
                new Customer(
                        resultSet.getString("name_recipient"),
                        resultSet.getBigDecimal("old_balance_recipient"),
                        resultSet.getBigDecimal("new_balance_recipient")
                ),
                resultSet.getBoolean("is_fraud"),
                resultSet.getBoolean("is_flagged_fraud")
        );
    }


}
