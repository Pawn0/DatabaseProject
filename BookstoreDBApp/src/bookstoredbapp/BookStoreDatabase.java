package bookstoredbapp;

/**
 *
 * @author Team2
 */
public class BookStoreDatabase {

    public static void main(String[] args) {
        System.out.println("Reading Data Test Begining");
        DatabaseExplorer databaseExplorer = new DatabaseExplorer();
        
        System.out.println("Date\n");
        databaseExplorer.comicBooksByDateTheyComeOut("0019-06-26");
        
        System.out.println("Publisher\n");
        databaseExplorer.comicBooksByPublisher("Avamon");
        
        System.out.println("SeriesByName\n");
        databaseExplorer.comicBooksBySeriesName("All-New Belly Boy");
        
        System.out.println("ComicBooksOnGivenDate\n");
        databaseExplorer.comicBooksOrderOnAGivenDateAndHowMany("0019-06-26");
        
        System.out.println("BySeries Subscribed\n");
        databaseExplorer.customersByComicBookSeriesSubscribed("All-New Belly Boy");

        System.out.println("BySeries Subscribed\n");
        databaseExplorer.findCustomersViaTelephoneNumber("002-350-2168");
        
        System.out.println("Subscription\n");
        databaseExplorer.findSubscriptionsViaCustomerName("Damien Wall");
        
        
        System.out.println("Updating Data Test Begining\n");
        DatabaseUpdater databaseUpdater = new DatabaseUpdater();
        
        System.out.println("Adding new customer.\n");
        databaseUpdater.addNewCustomer("Gerardo Gonzalez Moctezuma", "601 S MLK JrDrive", "336-222-2222");
        
        System.out.println("Adding new subscription.\n");
        databaseUpdater.addNewSubscription("West Coast Flat lad", "Gerardo Gonzalez Moctezuma");
        
        System.out.println("chanigng date comic book released.\n");
        databaseUpdater.changeDateComicBookIsReleased("0019-06-27", 1);
        
        System.out.println("delete all subscription for customer.\n");
        databaseUpdater.deleteAllSubscriptionForCustomer("Gerardo Gonzalez Moctezuma");
        
        System.out.println("delete customer. First subcriptions then customer. \n");
        databaseUpdater.deleteCustomer("Gerardo Gonzalez Moctezuma");
        
        System.out.println("update customer.\n");
        databaseUpdater.updateCustomer("Tanya Dalton", "601 S MLK Jr Drive", "097-272-9181", 2);
    }
}
