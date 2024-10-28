// File: src/transactions/WithdrawalTransaction.java
package transactions;

import java.util.Calendar;
import bank.BankAccount;
import bank.InsufficientFundsException;

public class WithdrawalTransaction extends BaseTransaction {

    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        try {
            if (ba.getBalance() < amount) {
                System.out.println("Partial withdrawal of " + ba.getBalance() + " due to insufficient funds.");
                ba.withdraw(ba.getBalance());
            } else {
                ba.withdraw(amount);
                System.out.println("Withdrawal successful. New balance: " + ba.getBalance());
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean reverse(BankAccount ba) {
        ba.deposit(amount);
        System.out.println("Reversal successful. New balance: " + ba.getBalance());
        return true;
    }
}
