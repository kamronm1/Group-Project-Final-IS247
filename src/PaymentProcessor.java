/**
 * Interface defining the contract for processing payments.
 */
public interface PaymentProcessor {
    /**
     * Processes a payment for a given amount.
     * @param amount The cost of the item.
     * @param provided The amount given by the user.
     * @return True if successful, false otherwise.
     */
    boolean processPayment(double amount, double provided);
}