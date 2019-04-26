package bookstoredbapp;

/**
 *
 * @author Team2
 */
public class DatabaseTestingClass {

    public static void main(String[] args) {
        System.out.println("Test Begining");
        DatabaseExplorer databaseExplorer = new DatabaseExplorer();
        
        //dates
        System.out.println("Date");
        databaseExplorer.comicBooksByDateTheyComeOut("0019-06-26");
        
        //Avamon
        System.out.println("Publisher");
        databaseExplorer.comicBooksByPublisher("Avamon");
        
        System.out.println("SeriesByName");
        databaseExplorer.comicBooksBySeriesName("All-New Belly Boy");
        
        System.out.println("ComicBooksOnGivenDate");
        databaseExplorer.comicBooksOrderOnAGivenDateAndHowMany("0019-06-26");
        
        //Dates
        System.out.println("BySeries Subscribed");
        databaseExplorer.customersByComicBookSeriesSubscribed("All-New Belly Boy");
        
        //Dates
        System.out.println("BySeries Subscribed");
        databaseExplorer.findCustomersViaTelephoneNumber("002-350-2168");
        
        //Damien
        System.out.println("Subscription");
        databaseExplorer.findSubscriptionsViaCustomerName("Damien Wall");
    }
}
