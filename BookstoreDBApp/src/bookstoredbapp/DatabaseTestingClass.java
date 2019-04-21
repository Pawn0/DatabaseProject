package bookstoredbapp;

/**
 *
 * @author Team2
 */
public class DatabaseTestingClass {

    public static void main(String[] args) {
        System.out.println("Test Begining");
        DatabaseExplorer databaseExplorer = new DatabaseExplorer();
        databaseExplorer.findSubscriptionsViaCustomerName("Damien Wall");
    }
}
