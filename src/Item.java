// Base class for all vending machine items
public abstract class Item {
    private String name;
    private double price;

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