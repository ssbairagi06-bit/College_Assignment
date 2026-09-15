package Threading;

import DaoInterface.BankAccountDao;
import ExceptionHandling.InsufficientBalanceException;
import ExceptionHandling.InvalidAmountException;
import POJO.Account;

public class TransactionTask implements Runnable {

    private final Account account;
    private final BankAccountDao dao;
    private final String operation;
    private final double amount;

    public TransactionTask(Account account, BankAccountDao dao, String operation, double amount) {
        this.account = account;
        this.dao = dao;
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public void run() {

        synchronized (account) {
            try {
                if ("DEPOSIT".equalsIgnoreCase(operation)) {
                    account.deposit(amount);
                    dao.updateBalance(account.getAccountNo(), account.getBalance());
                    dao.recordTransaction(account.getAccountNo(), "DEPOSIT", amount);

                    System.out.println(Thread.currentThread().getName() +
                            " Deposited ₹" + amount + " | New Balance: ₹" + account.getBalance());

                } else if ("WITHDRAW".equalsIgnoreCase(operation)) {
                    account.withdraw(amount);
                    dao.updateBalance(account.getAccountNo(), account.getBalance());
                    dao.recordTransaction(account.getAccountNo(), "WITHDRAW", amount);

                    System.out.println(Thread.currentThread().getName() +
                            " Withdrew ₹" + amount + " | New Balance: ₹" + account.getBalance());
                }
            } catch (InvalidAmountException | InsufficientBalanceException e) {
                System.out.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " Database Error: " + e.getMessage());
            }
        }
    }
}