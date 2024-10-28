// File: src/main/Main.java
package main;

import java.util.Calendar;
import bank.BankAccount;
import transactions.DepositTransaction;
import transactions.WithdrawalTransaction;
import utils.DateUtils;
import java.util.Calendar;


public class Main {
    public static void main(String[] args) {
        // Set up bank account and transactions
        BankAccount account = new BankAccount(500.0);
        
        Calendar transactionDate = DateUtils.getCurrentDate();
        
        DepositTransaction deposit = new DepositTransaction(200.0, transactionDate, "D123");
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(100.0, transactionDate, "W123");

        // Apply transactions
        deposit.apply(account);
        withdrawal.apply(account);
        
        // Test reversal of withdrawal
        withdrawal.reverse(account);
        
        // Display details
        deposit.printTransactionDetails();
        withdrawal.printTransactionDetails();
    }
}
