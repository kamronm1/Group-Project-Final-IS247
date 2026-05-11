/**
 * Exception that is thrown when item is out of stock
 */
public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}