package bookstoredbapp;

/**
 *
 * @author Team2
 */
public class DatabaseUpdater {

    public void UpdateDB(String query) {
        DBConnector dbConnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");
        dbConnector.changingQuery(query);
        dbConnector.close();
    }

    public void addNewCustomer(String name, String address, String phoneNumber) {
        String SQL = "INSERT INTO customers (customer_name, address, phone_number)"
                + "VALUES (" + "'" + name.replace("'","''") + "'" + "," + "'" + address.replace("'","''")
                + "," + "'" + phoneNumber + "'" + ")";
        UpdateDB(SQL);
    }

    public void addNewSubscription(String subscription, String customerName) {
        String SQL = "INSERT INTO subscription (series_title, customer_name)"
                + "VALUES ('" + subscription + "','" + customerName.replace("'","''") + "')";
        UpdateDB(SQL);
    }

    public void updateCustomer(String name, String address, String phoneNumber, int customerID) {
        String SQL = "UPDATE customers SET  customer_name = '" + name + "', address = '"+address + "',"+ "phone ='"+ phoneNumber +"' WHERE customer_id = "+customerID;
    }

    public void changeDateComicBookIsReleased(int issueID, String series_title, String date) {
        
    }

    public void deleteCustomer() {
        deleteSubscriptionForCustomer();
    }

    public void deleteSubscriptionForCustomer() {
    }
}
