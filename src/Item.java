/**
 * (The Blueprint) This is the abstract base class
 * It defines the universal properties every product
 * has (a name and a price) and forces all child classes
 * to create their own version of a displayDetails() method
 *
 */
public abstract class Item {
    private String name;
    private double price;

    /**
     * Class constructor for item
     * @param name name of item
     * @param price price of item
     */
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
// Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
// Most items don't expire unless they're overridden
    public boolean isExpired() {
        return false;
    }

    public abstract String displayDetails();
}