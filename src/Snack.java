/**
 * (The Basic Products) These classes extend Item.
 * Snack adds a specific boolean property to check if it's vegan,
 * while Beverage adds a boolean to check if it is cold.
 * They both override displayDetails() to print their specific information.
 */
public class Snack extends Item {
    private boolean isVegan;

    public Snack(String name, double price, boolean isVegan) {
        super(name, price);
        this.isVegan = isVegan;
    }

    @Override
    public String displayDetails() {
        return getName() + " - $" + getPrice() + " (Vegan: " + isVegan + ")";
    }
}