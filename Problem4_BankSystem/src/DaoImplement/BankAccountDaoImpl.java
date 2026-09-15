package DaoImplement;

import DaoInterface.BankAccountDao;
import DaoInterface.QueryInterface;
import DaoInterface.DbConnection;
import POJO.Account;

import java.sql.*;

public class BankAccountDaoImpl implements BankAccountDao {

    private final DbConnection dbConnection;

    public BankAccountDaoImpl() {
        this.dbConnection = new DbConnectionImp();
    }

    @Override
    public boolean createAccount(Account account) throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.INSERT_ACCOUNT)) {

            ps.setString(1, account.getAccountNo());
            ps.setString(2, account.getName());
            ps.setDouble(3, account.getBalance());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Account getAccount(String accountNo) throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.GET_ACCOUNT)) {

            ps.setString(1, accountNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getString("account_no"),
                            rs.getString("name"),
                            rs.getDouble("balance")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateBalance(String accountNo, double balance) throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.UPDATE_BALANCE)) {

            ps.setDouble(1, balance);
            ps.setString(2, accountNo);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean recordTransaction(String accountNo, String transactionType, double amount) throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.INSERT_TRANSACTION)) {

            ps.setString(1, accountNo);
            ps.setString(2, transactionType);
            ps.setDouble(3, amount);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void displayBalance(String accountNo) throws SQLException {
        Account account = getAccount(accountNo);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\n------------------------------");
        System.out.println("Account Number : " + account.getAccountNo());
        System.out.println("Account Holder : " + account.getName());
        System.out.println("Current Balance: ₹" + account.getBalance());
        System.out.println("------------------------------");
    }

    @Override
    public void displayTransactions(String accountNo) throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.GET_TRANSACTIONS)) {

            ps.setString(1, accountNo);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n----- Transaction History for Account: " + accountNo + " -----");
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                            "ID: " + rs.getInt("transaction_id") +
                            " | Type: " + rs.getString("transaction_type") +
                            " | Amount: ₹" + rs.getDouble("amount") +
                            " | Timestamp: " + rs.getTimestamp("transaction_time")
                    );
                }

                if (!found) {
                    System.out.println("No transactions found.");
                }
                System.out.println("---------------------------------------------------------");
            }
        }
    }

    @Override
    public void displayAllAccounts() throws SQLException {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryInterface.GET_ALL_ACCOUNTS);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- All Registered Accounts -----");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "Account No: " + rs.getString("account_no") +
                        " | Holder: " + rs.getString("name") +
                        " | Balance: ₹" + rs.getDouble("balance")
                );
            }

            if (!found) {
                System.out.println("No accounts found.");
            }
            System.out.println("-----------------------------------");
        }
    }
}