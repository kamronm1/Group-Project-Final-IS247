//Exception that is thrown when item is expired
public class ExpiredProductException extends Exception {
    public ExpiredProductException(String message) {
        super(message);
    }
}