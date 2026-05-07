import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.loadInventoryFromFile("src/Inventory.txt");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Virtual Vending Machine!");

        while (running) {
            machine.displayMenu();

            System.out.print("Enter slot code (or Q to quit): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.print("Enter payment amount: $");

                try {
                    double money = Double.parseDouble(scanner.nextLine());
                    machine.purchase(input, money);
                } catch (OutOfStockException | InsufficientFundsException | ExpiredProductException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                }
            }
        }

        scanner.close();
    }
}
