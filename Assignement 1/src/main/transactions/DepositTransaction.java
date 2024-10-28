// File: src/transactions/DepositTransaction.java
package transactions;

import java.util.Calendar;
import bank.BankAccount;


public class DepositTransaction extends BaseTransaction {

    public DepositTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        ba.deposit(amount);
        System.out.println("Deposit successful. New balance: " + ba.getBalance());
    }
}
