// File: src/bank/InsufficientFundsException.java
package bank;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
