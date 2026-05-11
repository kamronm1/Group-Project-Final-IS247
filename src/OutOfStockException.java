/**
 * Exception that is thrown when item is out of stock
 * Extends exception
 */
public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}