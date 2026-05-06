/**
 * Custom exception thrown when a user does not provide enough money.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}