package bookstoredbapp;

import edu.wssu.compsci.databases.comicbook.ComicBookParser;
import edu.wssu.compsci.databases.comicbook.Customer;
import edu.wssu.compsci.databases.comicbook.ComicSeries;
import edu.wssu.compsci.databases.comicbook.Issue;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Team 2
 */
public class FileReader {
    
    //read from the file and get the customer list and the series
    public void readFile() {
        ComicBookParser bookParser = new ComicBookParser(fileName);
        customerList = bookParser.getCustomerList();
        System.out.println(customerList.toString());

        comicSeriesList = bookParser.getSeriesList();
        System.out.println(comicSeriesList.toString());
    }

    //function to set filename for futher reading
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    //this function will be used to connect to the DB for inserting data
    public void connectToDB(boolean queryFlag, boolean insertAllDataFlag, boolean updateDataFlag) {
        DBConnector dbconnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");

        //we have to first have the comic series data
        //so that customers will have something to subscribe to
        readFile();
        insertComicSeriesData(dbconnector);
        insertCustomerData(dbconnector);

        dbconnector.close();
    }

    //inserts the data and also it makes sure that the customer subscriptions are available
    public void insertCustomerData(DBConnector dbConnector) {
        for (Customer customer : customerList) {
            dbConnector.changingQuery(prepareSQLStatementForCustomer(customer));
            
            List<String> subscriptionList = customer.subscriptions();
            for (String subscription : subscriptionList) {
                dbConnector.changingQuery(prepareSQLStatementForSubscription(subscription, customer));
            }
        }
    }

    //inserts comic series data and then makes sure that the issues for it are there
    public void insertComicSeriesData(DBConnector dbConnector) {
        for (ComicSeries series : comicSeriesList) {
            dbConnector.changingQuery(prepareSQLStatementForSeries(series));
            
            List<Issue> issueList = series.getIssues();
            for(Issue issue : issueList){
                dbConnector.changingQuery(prepareSQLStatementForIssue(issue, series));
            }
        }
    }

    public String prepareSQLStatementForSeries(ComicSeries comicSeries) {
        String SQL = "INSERT INTO comic_book_series (series_title, publisher)" + "VALUES (" + "'" + comicSeries.getTitle() + "','" + comicSeries.getPublisher() + "')";

        return SQL;
    }
    
    
    //dates are backwards in postGre for some reason so YY-MM-DD, so 0019-6-23
    public String prepareSQLStatementForIssue(Issue issue, ComicSeries comicSeries) {
        //2016-06-23
        String issueDate = simpleDateFormat.format(issue.getDate());
        String SQL = "INSERT INTO comic_book (issue_date, issue_number, series_title)" + "VALUES ('" + issueDate + "',"+ issue.getNumber() +",'" + comicSeries.getTitle() + "')";

        return SQL;
    }

    //perpares sql statements for the customer entry
    public String prepareSQLStatementForCustomer(Customer customer) {

        String SQL = "INSERT INTO customers (customer_name, address, phone_number)"
                + "VALUES (" + "'" + customer.getName().replace("'","''") + "'" + "," + "'" + customer.getStreetAddress().replace("'","''") + " " + customer.getCity() + " " + customer.getState() + " " + customer.getZip() + "'" + "," + "'" + customer.getPhoneNumber() + "'" + ")";

        return SQL;
    }

    //perpares sql for inserting subscriptions
    public String prepareSQLStatementForSubscription(String subscription, Customer customer) {
        String SQL = "INSERT INTO subscription (series_title, customer_name)"
                + "VALUES ('" + subscription + "','" + customer.getName().replace("'","''") + "')";

        return SQL;
    }
    
    //date formating util
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public List<Customer> customerList;
    public List<ComicSeries> comicSeriesList;
    public String fileName;
}
