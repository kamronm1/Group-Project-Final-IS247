public class Item {
    private String code;
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public Item(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Reduce stock when item is purchased
    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    // Checks to see if item is in stock
    public boolean isInStock() {
        return quantity > 0;
    }
}

