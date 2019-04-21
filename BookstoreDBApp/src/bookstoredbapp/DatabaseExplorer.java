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
        String SQL = "SELECT series_title FROM subscription WHERE customer_name = '" + customerName + "'";
        exploreDB(SQL, 0);
    }

    public void findCustomersViaTelephoneNumber(String telephoneNumber) {
        String SQL = "SELECT customer_name FROM customers WHERE phone_number = '" + telephoneNumber + "'";
        exploreDB(SQL, 1);
    }

    public void comicBooksBySeriesName(String series_name) {
        String SQL = "SELECT series_title FROM comic_book_series WHERE series_title = '" + series_name + "'";
        exploreDB(SQL, 0);
    }

    public void comicBooksByDateTheyComeOut(Date date) {
        String searchDate = simpleDateFormat.format(date);
        String SQL = "SELECT comic_book_series, issue_date FROM comic_book_series WHERE issue_date = '" + searchDate + "'";
        exploreDB(SQL, 3);

    }

    public void comicBooksByPublisher(String publisher) {
        String SQL = "SELECT series_title FROM comic_book_series WHERE publisher = '" + publisher + "'";
        exploreDB(SQL, 0);
    }

    public void customersByComicBookSeriesSubscribed(String series) {
        String SQL = "SELECT customer_name FROM subscription WHERE series_title = '" + series + "'";
        exploreDB(SQL, 1);
    }

    public void comicBooksOrderOnAGivenDateAndHowMany(String date) {
        String SQL = "SELECT\n" +
        "subscription.series_title,\n" +
        "  COUNT(*) AS number_ordered\n" +
        "FROM\n" +
        "  subscription, comic_book\n" +
        "WHERE \n" +
        "  subscription.series_title = comic_book.series_title AND comic_book.issue_date = '"+ simpleDateFormat.format(date) +"' \n" +
        "GROUP BY\n" +
        "  subscription.series_title";
        exploreDB(SQL, 2);
    }

    //prints out the result set for all the customer subscriptions
    //essentially its saying result set get the value from colum series title
    //add it to display data, and print it out on the console
    private void printResultSet(ResultSet rs, int type) {
        displayData.clear();
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
                        displayData.add(customer_name);
                        System.out.println(customer_name);
                        break;
                    case 2:
                        String series_title_on_given_day = rs.getString("series_title");
                        String number_ordered = rs.getString("number_ordered");
                        displayData.add(series_title_on_given_day + " " + number_ordered);
                        System.out.println(series_title_on_given_day + " " + number_ordered);
                        break;
                    case 3:
                        String issue_number = rs.getString("issue_number");
                        Date issue_date = rs.getDate("issue_date");
                        displayData.add(issue_number +" "+issue_date.toString());
                        System.out.println(issue_number +" "+issue_date.toString());
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //display data is what is used to get the information into one convenient location
    //we should call a function and then read the display data to get it to display somwhere
    ArrayList<String> displayData;
    
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
}
