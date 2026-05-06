/**
 * Abstract base class representing a general item in the vending machine.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // By default, items don't expire. Subclasses can override this.
    public boolean isExpired() {
        return false;
    }

    /**
     * Abstract method to be implemented by subclasses.
     */
    public abstract String displayDetails();
}