
package BankMain;

import java.sql.SQLException;
import java.util.Scanner;

import DaoImplement.BankAccountDaoImpl;
import DaoInterface.BankAccountDao;
import ExceptionHandling.InsufficientBalanceException;
import ExceptionHandling.InvalidAmountException;
import POJO.Account;
import Threading.TransactionTask;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BankAccountDao dao = new BankAccountDaoImpl();

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("     BANK ACCOUNT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Test Multithreading");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;

                    case 2:
                        depositMoney();
                        break;

                    case 3:
                        withdrawMoney();
                        break;

                    case 4:
                        displayBalance();
                        break;

                    case 5:
                        transactionHistory();
                        break;

                    case 6:
                        displayAllAccounts();
                        break;

                    case 7:
                        testMultithreading();
                        break;

                    case 8:
                        System.out.println("Thank you for using Bank Account System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose 1-8.");
                }

            } else {
                System.out.println("Invalid input. Please enter a valid menu number.");
                scanner.next();
            }

        } while (choice != 8);

        scanner.close();
    }

    private static void createAccount() {

        try {
            System.out.print("Enter account number: ");
            String accountNo = scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            if (balance < 0) {
                throw new InvalidAmountException(
                        "Initial balance cannot be negative."
                );
            }

            Account account = new Account(
                    accountNo,
                    name,
                    balance
            );

            if (dao.createAccount(account)) {
                System.out.println("Account created successfully.");
            } else {
                System.out.println("Account creation failed.");
            }

        } catch (InvalidAmountException e) {
            System.out.println("Validation Error: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void depositMoney() {

        try {
            System.out.print("Enter account number: ");
            String accountNo = scanner.nextLine();

            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Account account = dao.getAccount(accountNo);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            account.deposit(amount);

            if (dao.updateBalance(accountNo, account.getBalance())) {
                dao.recordTransaction(
                        accountNo,
                        "DEPOSIT",
                        amount
                );

                System.out.println("Amount deposited successfully.");
                System.out.println(
                        "Updated Balance: ₹" + account.getBalance()
                );
            }

        } catch (InvalidAmountException e) {
            System.out.println(
                    "Transaction Error: " + e.getMessage()
            );

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );

        } catch (Exception e) {
            System.out.println(
                    "Invalid input: " + e.getMessage()
            );
        }
    }

    private static void withdrawMoney() {

        try {
            System.out.print("Enter account number: ");
            String accountNo = scanner.nextLine();

            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Account account = dao.getAccount(accountNo);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            account.withdraw(amount);

            if (dao.updateBalance(accountNo, account.getBalance())) {
                dao.recordTransaction(
                        accountNo,
                        "WITHDRAW",
                        amount
                );

                System.out.println("Amount withdrawn successfully.");
                System.out.println(
                        "Updated Balance: ₹" + account.getBalance()
                );
            }

        } catch (InvalidAmountException |
                 InsufficientBalanceException e) {

            System.out.println(
                    "Transaction Error: " + e.getMessage()
            );

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );

        } catch (Exception e) {
            System.out.println(
                    "Invalid input: " + e.getMessage()
            );
        }
    }

    private static void displayBalance() {

        try {
            System.out.print("Enter account number: ");
            String accountNo = scanner.nextLine();

            dao.displayBalance(accountNo);

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }

    private static void transactionHistory() {

        try {
            System.out.print("Enter account number: ");
            String accountNo = scanner.nextLine();

            dao.displayTransactions(accountNo);

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }

    private static void displayAllAccounts() {

        try {
            dao.displayAllAccounts();

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }

    private static void testMultithreading() {

        try {
            System.out.print(
                    "Enter target account number for concurrent simulation: "
            );

            String accountNo = scanner.nextLine();

            Account account = dao.getAccount(accountNo);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println(
                    "\n--- Starting Multithreaded Operations ---"
            );

            System.out.println(
                    "Initial Balance: ₹" + account.getBalance()
            );

            Thread user1 = new Thread(
                    new TransactionTask(
                            account,
                            dao,
                            "DEPOSIT",
                            1000
                    ),
                    "User-1"
            );

            Thread user2 = new Thread(
                    new TransactionTask(
                            account,
                            dao,
                            "WITHDRAW",
                            500
                    ),
                    "User-2"
            );

            Thread user3 = new Thread(
                    new TransactionTask(
                            account,
                            dao,
                            "WITHDRAW",
                            2000
                    ),
                    "User-3"
            );

            user1.start();
            user2.start();
            user3.start();

            user1.join();
            user2.join();
            user3.join();

            System.out.println(
                    "\nMultithreading simulation completed successfully."
            );

            System.out.println(
                    "Final Balance: ₹" + account.getBalance()
            );

        } catch (SQLException e) {
            System.out.println(
                    "Database Error: " + e.getMessage()
            );

        } catch (InterruptedException e) {
            System.out.println(
                    "Simulation interrupted: " + e.getMessage()
            );

            Thread.currentThread().interrupt();
        }
    }
}

