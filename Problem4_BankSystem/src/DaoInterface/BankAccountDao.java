package DaoInterface;

import java.sql.SQLException;

import POJO.Account;

public interface BankAccountDao {
	boolean createAccount(Account account) throws SQLException;

    Account getAccount(String accountNo) throws SQLException;

    boolean updateBalance(String accountNo, double balance) throws SQLException;

    boolean recordTransaction(String accountNo, String transactionType, double amount) throws SQLException;

    void displayBalance(String accountNo) throws SQLException;

    void displayTransactions(String accountNo) throws SQLException;

    void displayAllAccounts() throws SQLException;
}
