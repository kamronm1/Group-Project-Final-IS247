/**
 * (The Basic Products) These classes extend Item.
 * Snack adds a specific boolean property to check if it's vegan,
 * while Beverage adds a boolean to check if it is cold.
 * They both override displayDetails() to print their specific information.
 */
public class Beverage extends Item {
    private boolean isCold;

    public Beverage(String name, double price, boolean isCold) {
        super(name, price);
        this.isCold = isCold;
    }

    @Override
    public String displayDetails() {
        return getName() + " - $" + getPrice() + " (Cold: " + isCold + ")";
    }
}