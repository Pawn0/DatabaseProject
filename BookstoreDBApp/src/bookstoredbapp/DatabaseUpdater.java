package bookstoredbapp;

import java.sql.ResultSet;

/**
 *
 * @author Team2
 */
public class DatabaseUpdater {

    public void UpdateDB(String query, int typeOfQuery) {
        DBConnector dbConnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");
        dbConnector.changingQuery(query);

        //query type 0 will find subscriptions via customer name
        //type 1 will find customers via telephoneNumber
        //type 2 will find
        executeChange(typeOfQuery);

        dbConnector.close();
    }

    private void executeChange(int changeType) {
    }

    public void addNewCustomer() {
    }

    public void addNewSubscription(String customer) {
    }

    public void updateCustomer() {
    }

    public void changeDateComicBookIsReleased() {
    }

    public void deleteCustomer() {
        deleteSubscriptionForCustomer();
    }

    public void deleteSubscriptionForCustomer() {
    }
}
