/**
 * (The Advanced Product) This class extends Snack.
 * It introduces a java.util.Date property for an expiration date.
 * It overrides the isExpired() method from the base class to check
 * if today's date is past the item's expiration date.
 *
 */

import java.util.Date;

public class PerishableItem extends Snack {
    private Date expirationDate;

    /**
     * Class constructor for perishable item
     * @param name
     * @param price
     * @param isVegan
     * @param expirationDate
     */
    public PerishableItem(String name, double price, boolean isVegan, Date expirationDate) {
        super(name, price, isVegan);
        this.expirationDate = expirationDate;
    }
//Checks to see if the item is expired
    @Override
    public boolean isExpired() {
        return new Date().after(expirationDate);
    }

    @Override
    public String displayDetails() {
        return super.displayDetails() + " (Expires: " + expirationDate + ")";
    }
}