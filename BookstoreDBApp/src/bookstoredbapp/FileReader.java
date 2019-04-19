package bookstoredbapp;
import edu.wssu.compsci.databases.comicbook.ComicBookParser;
import edu.wssu.compsci.databases.comicbook.Customer;
import edu.wssu.compsci.databases.comicbook.ComicSeries;
import java.util.List;
/**
 *
 * @author Team 2
 */
public class FileReader {
    
    public void readFile(){
        ComicBookParser bookParser = new ComicBookParser(fileName);
        customerList = bookParser.getCustomerList();
        System.out.println(customerList.toString());
        
        comicSeriesList = bookParser.getSeriesList();
        System.out.println(comicSeriesList.toString());
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public void connectToDB(){
        DBConnector dbconnector = new DBConnector("spring19group2", "jZXg7V3p3VkD");
        readFile();
        insertCustomerData();
        insertComicData();
        dbconnector.close();
    }
    
    public void insertCustomerData(){
     
    }
    
    public void insertComicData(){
    
    }
    
    public String prepareSQLStatementForCustomer(Customer customer){
        customer.getName();
        customer.getStreetAddress();
        customer.getCity();
        customer.getState();
        customer.getZip();

        customer.getNextSubscription();
        customer.hasNextSubscription(); 
        customer.subscriptions();
        
        String SQL = "INSERT INTO customers (customer_name, address, phone_number)"
                + "VALUES ("+customer.getName()+","+ customer.getStreetAddress()+ " " + customer.getCity()+ " " +customer.getState()+" "+customer.getZip() +"," + customer.getPhoneNumber() + ")";
        
        return SQL;
    }
    
    
    public List<Customer> customerList;
    public List<ComicSeries> comicSeriesList;
    public String fileName;
}
