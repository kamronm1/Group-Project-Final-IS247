
import java.util.Date;
//Item that can expire
public class PerishableItem extends Snack {
    private Date expirationDate;

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