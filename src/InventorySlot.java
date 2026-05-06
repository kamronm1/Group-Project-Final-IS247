/**
 * Generic class representing a slot in the vending machine holding a specific type of item.
 * @param <T> Any type that extends AbstractItem.
 */
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