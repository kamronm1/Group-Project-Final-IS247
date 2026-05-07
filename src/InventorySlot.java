public class InventorySlot<T extends Item> {
    private T item;
    private int quantity;

    public InventorySlot(T item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public boolean isInStock() {
        return quantity > 0;
    }
}