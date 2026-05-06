import java.util.Date;

/**
 * Represents an item that can expire. Extends Snack.
 */
public class PerishableItem extends Snack {
    private Date expirationDate;

    public PerishableItem(String name, double price, boolean isVegan, Date expirationDate) {
        super(name, price, isVegan);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        Date today = new Date();
        return today.after(expirationDate); // Checks if today is past the expiry date
    }

    @Override
    public String displayDetails() {
        return super.displayDetails() + " [Expires: " + expirationDate.toString().substring(0, 10) + "]";
    }
}