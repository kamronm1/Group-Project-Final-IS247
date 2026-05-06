import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The core vending machine logic.
 * Demonstrates Collections, Java Library Classes (Date, File, Scanner), Method Overloading.
 */
public class VendingMachine implements PaymentProcessor {
    // Collection mapping slot codes (e.g., "A1") to inventory slots
    private Map<String, InventorySlot<? extends Item>> inventory;
    private TransactionLog<Item> dailyLog; // Uses the new generic log

    public VendingMachine() {
        this.inventory = new HashMap<>();
        this.dailyLog = new TransactionLog<>();
    }

    /**
     * Reads inventory.txt and populates the machine dynamically.
     */
    public void loadInventoryFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Skip empty lines to prevent crashes
                if(line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                String code = parts[0];
                String type = parts[1];
                String name = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                boolean flag = Boolean.parseBoolean(parts[5]); // isVegan or isCold

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
            System.out.println("Inventory loaded successfully from file.\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error: inventory.txt file not found! Please create it in your project folder.");
        } catch (ParseException e) {
            System.out.println("Error: Date format in the inventory file is incorrect.");
        }
    }

    /**
     * Displays the current inventory to the user.
     */
    public void displayMenu() {
        System.out.println("--- Vending Machine Menu ---");
        for (Map.Entry<String, InventorySlot<? extends Item>> entry : inventory.entrySet()) {
            InventorySlot<? extends Item> slot = entry.getValue();
            System.out.println(entry.getKey() + ": " + slot.getItem().displayDetails() + " [Qty: " + slot.getQuantity() + "]");
        }
        System.out.println("----------------------------");
    }

    /**
     * Implementation of the PaymentProcessor interface.
     */
    @Override
    public boolean processPayment(double price, double provided) {
        return provided >= price;
    }

    /**
     * Method Overloading: Purchase using cash.
     */
    public void purchase(String code, double cashInserted) throws OutOfStockException, InsufficientFundsException, ExpiredProductException {
        executePurchase(code, cashInserted);
    }

    /**
     * Method Overloading: Purchase using a credit card (simulated).
     */
    public void purchase(String code, String creditCardNumber) throws OutOfStockException, InsufficientFundsException, ExpiredProductException {
        InventorySlot<? extends Item> slot = inventory.get(code);
        if (slot != null) {
            System.out.println("Authorizing card ending in " + creditCardNumber.substring(creditCardNumber.length() - 4) + "...");
            executePurchase(code, slot.getItem().getPrice()); // Card gets charged exact amount
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * Core logic for completing a transaction.
     */
    private void executePurchase(String code, double amountProvided) throws OutOfStockException, InsufficientFundsException, ExpiredProductException {
        InventorySlot<? extends Item> slot = inventory.get(code);

        if (slot == null) {
            System.out.println("Invalid slot code.");
            return;
        }

        if (!slot.isInStock()) {
            throw new OutOfStockException("Sorry, " + slot.getItem().getName() + " is completely sold out.");
        }

        if (slot.getItem().isExpired()) {
            throw new ExpiredProductException("Cannot sell " + slot.getItem().getName() + " because it is past its expiration date!");
        }

        double price = slot.getItem().getPrice();

        if (!processPayment(price, amountProvided)) {
            throw new InsufficientFundsException("Not enough money. " + slot.getItem().getName() + " costs $" + price);
        }

        // Processing the successful sale
        slot.reduceQuantity();
        double change = amountProvided - price;

        // Log the transaction
        dailyLog.addLog(slot.getItem(), price);

        System.out.println("\n*** RECEIPT ***");
        System.out.println("Item: " + slot.getItem().getName());
        System.out.println("Price: $" + price);
        System.out.printf("Change returned: $%.2f\n", change);
        System.out.println("***************\n");
    }

    /**
     * Prints the hidden admin log.
     */
    public void printAdminLog() {
        dailyLog.printAdminReport();
    }
}