package bookstoredbapp;

/**
 *
 * @author Team2
 */
public class DatabaseUpdater {

    private void UpdateDB(String query) {
        DBConnector dbConnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");
        dbConnector.changingQuery(query);
        dbConnector.close();
    }

    public void addNewCustomer(String name, String address, String phoneNumber) {
        String SQL = "INSERT INTO customers (customer_name, address, phone_number)"
                + "VALUES (" + "'" + name.replace("'","''") + "','" + address.replace("'","''") + "','" + phoneNumber + "'" + ")";
        UpdateDB(SQL);
    }

    public void addNewSubscription(String subscription, String customerName) {
        String SQL = "INSERT INTO subscription (series_title, customer_name)"
                + "VALUES ('" + subscription + "','" + customerName.replace("'","''") + "')";
        UpdateDB(SQL);
    }

    public void updateCustomer(String name, String address, String phoneNumber, int customer_id) {
        String SQL = "UPDATE customers SET  customer_name = '" + name.replace("'","''") + "', address = '"+address + "',"+ "phone_number ='"+ phoneNumber +"' WHERE customer_id = "+customer_id + ";";
        UpdateDB(SQL);
    }

    public void changeDateComicBookIsReleased(String date, int comic_book_id) {
        String SQL = "UPDATE comic_book SET  issue_date = '" + date + "' WHERE comic_book_id = "+comic_book_id +";";
        UpdateDB(SQL);
    }

    public void deleteCustomer(String customer_name) {
        deleteAllSubscriptionForCustomer(customer_name);
        String SQL = "DELETE FROM customers WHERE customer_name = '"+ customer_name.replace("'","''") +"';";
        UpdateDB(SQL);
    }

    public void deleteAllSubscriptionForCustomer(String customer_name) {
        String SQL = "DELETE FROM subscription WHERE customer_name ='"+ customer_name.replace("'","''") +"';";
        UpdateDB(SQL);
    }
    
    public void deleteOneSubscriptionForCustomer(int subscription_id){
        String SQL = "DELETE FROM subscription WHERE subscription_id = " + subscription_id + ";";
        UpdateDB(SQL);
    }
}
