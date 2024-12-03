package Assignment_1;

import Lecture4_interfaces_abstract_classes.BankAccount;
import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private BankAccount lastAppliedAccount;
    private double unprocessedAmount;
    
    public WithdrawalTransaction(double amount, Calendar date) {
        super(amount, date);
        this.unprocessedAmount = 0;
    }
    
    @Override
    public void printTransactionDetails() {
        System.out.println("WITHDRAWAL TRANSACTION");
        super.printTransactionDetails();
        if (unprocessedAmount > 0) {
            System.out.println("Unprocessed amount: " + unprocessedAmount);
        }
    }
    
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        if (ba.getBalance() < getAmount()) {
            throw new InsufficientFundsException(getAmount(), ba.getBalance());
        }
        
        double newBalance = ba.getBalance() - getAmount();
        ba.setBalance(newBalance);
        lastAppliedAccount = ba;
        System.out.println("Withdrawal successful. New balance: " + newBalance);
    }
    
    public void apply(BankAccount ba, boolean allowPartial) {
        try {
            if (ba.getBalance() <= 0) {
                throw new InsufficientFundsException(getAmount(), ba.getBalance());
            }
            
            if (allowPartial && ba.getBalance() < getAmount()) {
                unprocessedAmount = getAmount() - ba.getBalance();
                double withdrawAmount = ba.getBalance();
                ba.setBalance(0);
                lastAppliedAccount = ba;
                System.out.println("Partial withdrawal successful. Withdrawn: " + withdrawAmount);
                System.out.println("Remaining unprocessed: " + unprocessedAmount);
            } else {
                apply(ba);
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Transaction processed - Final balance: " + ba.getBalance());
        }
    }
    
    public boolean reverse() {
        if (lastAppliedAccount != null) {
            double currentBalance = lastAppliedAccount.getBalance();
            lastAppliedAccount.setBalance(currentBalance + getAmount() - unprocessedAmount);
            System.out.println("Transaction reversed. New balance: " + lastAppliedAccount.getBalance());
            return true;
        }
        return false;
    }
}