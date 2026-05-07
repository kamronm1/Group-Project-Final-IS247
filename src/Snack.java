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