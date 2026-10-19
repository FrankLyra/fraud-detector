package br.com.fraud.detector;

public final class TransactionQueries {

    public static final String INSERT_TRANSACTION = """
            INSERT INTO zenon_frauds.transactions (step, `type`, amount, name_origin, old_balance_origin, new_balance_origin,
            name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;


    private TransactionQueries() {
    }

    public static final String FIND_TRANSACTION_BY_ORIGIN_NAME = """
            SELECT id, step, `type`, amount, name_origin, old_balance_origin, new_balance_origin,
            name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud
            FROM zenon_frauds.transactions
            WHERE name_origin = ?
            ORDER BY step
            LIMIT 1;
            """;
}
