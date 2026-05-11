/**
 * (The Container) This is a Generic class
 * Instead of storing just items, the vending machine stores InventorySlot objects
 * This class holds the actual item (like a Snack)
 * and the quantity of that item remaining in the machine
 *
 */
public class InventorySlot<T extends Item> {
    private T item;
    private int quantity;

    /**
     * Class constructor for Inventory Slot
     * @param item item for slot
     * @param quantity how much of item
     */
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
// Lower the quantity after the purchase
    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public boolean isInStock() {
        return quantity > 0;
    }
}