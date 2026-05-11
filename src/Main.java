import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.loadInventoryFromFile("src/Inventory");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Virtual Vending Machine!");

        while (running) {
            machine.displayMenu();

            System.out.print("Enter slot code, ADMIN, or Q to quit: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                running = false;
                System.out.println("Goodbye!");
            }

            else if (input.equals("ADMIN")) {
                boolean adminRunning = true;

                while (adminRunning) {
                    System.out.println("\n--- ADMIN MODE ---");
                    System.out.println("1. View Inventory");
                    System.out.println("2. Restock Item");
                    System.out.println("3. View Revenue");
                    System.out.println("4. Exit");

                    System.out.print("Choose option: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            machine.displayMenu();
                            break;

                        case "2":
                            System.out.print("Enter slot code: ");
                            String code = scanner.nextLine().toUpperCase();

                            System.out.print("Enter amount to add: ");
                            int amount = Integer.parseInt(scanner.nextLine());

                            machine.restockItem(code, amount);
                            break;

                        case "3":
                            machine.viewRevenue();
                            break;

                        case "4":
                            adminRunning = false;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }

            else {
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