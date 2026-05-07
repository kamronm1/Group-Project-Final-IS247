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