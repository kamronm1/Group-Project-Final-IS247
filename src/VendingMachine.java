import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Date;

/**
 * Core vending machine logic
 */
public class VendingMachine {
    private Map<String, InventorySlot<? extends Item>> inventory;

    public VendingMachine() {
        inventory = new HashMap<>();
    }

    public void loadInventoryFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String code = parts[0];
                String type = parts[1];
                String name = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                boolean flag = Boolean.parseBoolean(parts[5]);

                if (type.equals("Snack")) {
                    inventory.put(code, new InventorySlot<>(new Snack(name, price, flag), quantity));
                } else if (type.equals("Beverage")) {
                    inventory.put(code, new InventorySlot<>(new Beverage(name, price, flag), quantity));
                } else if (type.equals("Perishable")) {
                    Date expiry = dateFormat.parse(parts[6]);
                    inventory.put(code, new InventorySlot<>(new PerishableItem(name, price, flag, expiry), quantity));
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    public void displayMenu() {
        System.out.println("\n--- MENU ---");

        for (String code : inventory.keySet()) {
            InventorySlot<? extends Item> slot = inventory.get(code);
            System.out.println(code + ": " + slot.getItem().displayDetails()
                    + " [Qty: " + slot.getQuantity() + "]");
        }
    }

    public void purchase(String code, double money)
            throws OutOfStockException, InsufficientFundsException, ExpiredProductException {

        InventorySlot<? extends Item> slot = inventory.get(code);

        if (slot == null) {
            System.out.println("Invalid selection.");
            return;
        }

        if (!slot.isInStock()) {
            throw new OutOfStockException("Item is out of stock.");
        }

        if (slot.getItem().isExpired()) {
            throw new ExpiredProductException("Item is expired.");
        }

        double price = slot.getItem().getPrice();

        if (money < price) {
            throw new InsufficientFundsException("Not enough money.");
        }

        slot.reduceQuantity();

        System.out.println("Dispensing: " + slot.getItem().getName());
        System.out.println("Change: $" + (money - price));
    }

    public void restockItem(String code, int amount) {
        InventorySlot<? extends Item> slot = inventory.get(code);

        if (slot != null) {
            slot.addQuantity(amount);
            System.out.println("Restocked successfully.");
        } else {
            System.out.println("Invalid slot code.");
        }
    }

    public void viewRevenue() {
        System.out.println("Revenue tracking not enabled in this version.");
    }
}