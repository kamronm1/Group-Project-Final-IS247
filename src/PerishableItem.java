import java.util.Date;

public class PerishableItem extends Snack {
    private Date expirationDate;

    public PerishableItem(String name, double price, boolean isVegan, Date expirationDate) {
        super(name, price, isVegan);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return new Date().after(expirationDate);
    }

    @Override
    public String displayDetails() {
        return super.displayDetails() + " (Expires: " + expirationDate + ")";
    }
}