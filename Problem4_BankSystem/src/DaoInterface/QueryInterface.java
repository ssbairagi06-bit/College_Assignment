package DaoInterface;

public interface QueryInterface {

    String INSERT_ACCOUNT =
            "INSERT INTO accounts(account_no, name, balance) VALUES (?, ?, ?)";

    String GET_ACCOUNT =
            "SELECT account_no, name, balance FROM accounts WHERE account_no = ?";

    String UPDATE_BALANCE =
            "UPDATE accounts SET balance = ? WHERE account_no = ?";

    String INSERT_TRANSACTION =
            "INSERT INTO transactions(account_no, transaction_type, amount) VALUES (?, ?, ?)";

    String GET_TRANSACTIONS =
            "SELECT transaction_id, account_no, transaction_type, amount, transaction_time " +
            "FROM transactions WHERE account_no = ? ORDER BY transaction_time DESC";

    String GET_ALL_ACCOUNTS =
            "SELECT account_no, name, balance FROM accounts";
}