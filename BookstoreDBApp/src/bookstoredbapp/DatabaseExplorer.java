package bookstoredbapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Team2
 */
//this class is used to read from the DB
public class DatabaseExplorer {

    //allows exploring the database, 
    private void exploreDB(String query, int typeOfQuery) {
        DBConnector dbConnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");
        ResultSet result = dbConnector.query(query);

        //query type 0 will find subscriptions via customer name
        //type 1 will find customers via telephoneNumber
        //type 2 will find
        printResultSet(result, typeOfQuery);

        dbConnector.close();
    }

    //perform the query to find the subscriptions via customer name.
    public void findSubscriptionsViaCustomerName(String customerName) {
        String SQL = "SELECT series_title FROM subscription, customers WHERE subscription.customer_id = customers.customer_id AND customers.customer_name = '"+ customerName.replace("'","''")+"'";
        exploreDB(SQL, 0);
    }

    public void findCustomersViaTelephoneNumber(String telephoneNumber) {
        String SQL = "SELECT customer_id, customer_name FROM customers WHERE phone_number = '" + telephoneNumber + "'";
        exploreDB(SQL, 1);
    }

    public void comicBooksBySeriesName(String series_name) {
        String SQL = "SELECT issue_number, publish_date FROM comic_book_belongs_to WHERE series_title = '" + series_name + "'";
        exploreDB(SQL, 3);
    }

    public void comicBooksByDateTheyComeOut(String date) {
        String SQL = "SELECT series_title, publish_date FROM comic_book_belongs_to WHERE publish_date = '" + date + "'";
        exploreDB(SQL, 4);

    }

    public void comicBooksByPublisher(String publisher) {
        String SQL = "SELECT series_title FROM series WHERE publisher = '" + publisher + "'";
        exploreDB(SQL, 0);
    }

    public void customersByComicBookSeriesSubscribed(String series) {
        String SQL = "SELECT subscription.customer_id, customer_name FROM subscription, customers WHERE series_title = '" + series + "' AND subscription.customer_id = customers.customer_id";
        exploreDB(SQL, 1);
    }

    public void comicBooksOrderOnAGivenDateAndHowMany(String date) {
        String SQL = "SELECT\n" +
        "subscription.series_title,\n" +
        "  COUNT(*) AS number_ordered\n" +
        "FROM\n" +
        "  subscription, comic_book_belongs_to\n" +
        "WHERE \n" +
        "  subscription.series_title = comic_book_belongs_to.series_title AND comic_book_belongs_to.publish_date = '"+ date +"' \n" +
        "GROUP BY\n" +
        "  subscription.series_title";
        exploreDB(SQL, 2);
    }

    //prints out the result set for all the customer subscriptions
    //essentially its saying result set get the value from colum series title
    //add it to display data, and print it out on the console
    private void printResultSet(ResultSet rs, int type) {
        //displayData.clear();
        try {
            while (rs.next()) {

                switch (type) {
                    case 0:
                        String series_title = rs.getString("series_title");
                        displayData.add(series_title);
                        System.out.println(series_title);
                        break;
                    case 1:
                        String customer_name = rs.getString("customer_name");
                        int customer_id = rs.getInt("customer_id");
                        displayData.add(Integer.toString(customer_id) + " "+ customer_name);
                        System.out.println(Integer.toString(customer_id) + " "+ customer_name);
                        break;
                    case 2:
                        String series_title_on_given_day = rs.getString("series_title");
                        String number_ordered = rs.getString("number_ordered");
                        displayData.add(series_title_on_given_day + " " + number_ordered);
                        System.out.println(series_title_on_given_day + " " + number_ordered);
                        break;
                    case 3:
                        String issue_number = rs.getString("issue_number");
                        Date issue_date = rs.getDate("publish_date");
                        displayData.add(issue_number +" "+issue_date.toString());
                        System.out.println(issue_number +" "+issue_date.toString());
                        break;
                    case 4:
                        String series_title_for_date_come_out = rs.getString("series_title");
                        Date issue_date_come_out = rs.getDate("publish_date");
                        displayData.add(series_title_for_date_come_out +" "+issue_date_come_out.toString());
                        System.out.println(series_title_for_date_come_out +" "+issue_date_come_out.toString());
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //display data is what is used to get the information into one convenient location
    //we should call a function and then read the display data to get it to display somwhere
    ArrayList<String> displayData = new ArrayList<String>();
    
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
}
