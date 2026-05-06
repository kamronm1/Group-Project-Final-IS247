import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A generic class to keep track of purchased items.
 */
public class TransactionLog<T extends Item> {
    private List<String> history;
    private double totalRevenue;

    public TransactionLog() {
        this.history = new ArrayList<>();
        this.totalRevenue = 0.0;
    }

    public void addLog(T item, double pricePaid) {
        String logEntry = new Date().toString() + " | Sold: " + item.getName() + " | Revenue: $" + pricePaid;
        history.add(logEntry);
        totalRevenue += pricePaid;
    }

    public void printAdminReport() {
        System.out.println("\n=== ADMIN TRANSACTION LOG ===");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String log : history) {
                System.out.println(log);
            }
        }
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("=============================\n");
    }
}