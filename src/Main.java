import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // 1. Dynamic File Loading
        machine.loadInventoryFromFile("src/inventory.txt");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Virtual Vending Machine!");

        while (running) {
            machine.displayMenu();
            System.out.print("Enter slot code to purchase (or 'Q' to quit): ");
            String input = scanner.nextLine().toUpperCase();

            // Admin Mode Check
            if (input.equals("0000")) {
                machine.printAdminLog();
                continue;
            }

            if (input.equals("Q")) {
                running = false;
                System.out.println("Thank you! Goodbye!");
                continue;
            }

            System.out.print("Pay with (1) Cash or (2) Card? ");
            String method = scanner.nextLine();

            try {
                if (method.equals("1")) {
                    System.out.print("Insert cash amount: $");
                    double cash = Double.parseDouble(scanner.nextLine());
                    machine.purchase(input, cash);
                } else if (method.equals("2")) {
                    System.out.print("Enter 16-digit card number: ");
                    String card = scanner.nextLine();
                    machine.purchase(input, card);
                } else {
                    System.out.println("Invalid payment method.");
                }
            } catch (OutOfStockException | InsufficientFundsException | ExpiredProductException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format entered.");
            }
        }
        scanner.close();
    }
}