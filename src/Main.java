/**
 * (The User Interface) This contains the public static void main method.
 * It is responsible for the infinite while loop that keeps the program running,
 * asking the user for input via Scanner,
 * and catching any errors if the user does something wrong.
 *
 * @author Dharma Vyas
 * @author Kamron Mason
 * @author Drew Belloff
 *
 */
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
