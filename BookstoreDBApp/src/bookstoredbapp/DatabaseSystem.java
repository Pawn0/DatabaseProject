/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoredbapp;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class DatabaseSystem {
    public void runSystem(){
        boolean continueSystem = true;
        System.out.println("Welcome to the BookStore Database System");
        
        while(continueSystem){
            System.out.println("Please enter an option:");
            System.out.println("1. First Run Enter Data");
            System.out.println("2. View Data");
            System.out.println("3. Update Data");
            System.out.println("4. Exit System");
            System.out.print("What do you wish to do with the System?: ");

            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            scanner.nextLine();
            if (selection != 4){
                switch(selection){
                    case 1:
                        System.out.println("First Run Entering Data");
                        System.out.println("Please give the name of the file");
                        String fileName = scanner.nextLine();
                        fileReader.setFileName(fileName);
                        fileReader.connectToDB();
                        System.out.println("Loaded Data!");
                        break;
                    case 2:
                        System.out.println("Viewing Data!");
                        System.out.println("1.View Comic Books By Date they Come Out");
                        System.out.println("2. View Comic Books by Publisher");
                        System.out.println("3. View Comic Book Series By Name");
                        System.out.println("4. View how many comic books to order on a certain date");
                        System.out.println("5. View customers by comic books subscribed to");
                        System.out.println("6. Find Customers Via Telephone Number");
                        System.out.println("7. Find Subscriptions via Customer Name");
                        System.out.println("8. Exit Viewing Data");
                        System.out.print("Enter Which Data to view:");
                        selection = scanner.nextInt();
                        scanner.nextLine();
                        if (selection != 8){
                            switch(selection){
                                case 1:
                                    System.out.print("Please enter date to search by: ");
                                    String date = scanner.nextLine();
                                    databaseExplorer.comicBooksByDateTheyComeOut(date);
                                    break;
                                case 2:
                                    System.out.print("Please enter the publisher name to find comic books: ");
                                    String publisher = scanner.nextLine();
                                    databaseExplorer.comicBooksByPublisher(publisher);
                                    break;
                                case 3:
                                    System.out.print("Please enter comic book series to find by name: ");
                                    String seriesName = scanner.nextLine();
                                    databaseExplorer.comicBooksBySeriesName(seriesName);
                                    break;
                                case 4:
                                    System.out.print("Please enter the date to view how many comic books to order on a particular date: ");
                                    date = scanner.nextLine();
                                    databaseExplorer.comicBooksOrderOnAGivenDateAndHowMany(date);
                                    break;
                                case 5:
                                    System.out.print("Please enter series to see customers subscribed to the series: ");
                                    seriesName = scanner.nextLine();
                                    databaseExplorer.customersByComicBookSeriesSubscribed(seriesName);
                                    break;
                                case 6:
                                    System.out.print("Please enter telephone number to find the customers via telephone number: ");
                                    String telephoneNumber = scanner.nextLine();
                                    databaseExplorer.findCustomersViaTelephoneNumber(telephoneNumber);
                                    break;
                                case 7:
                                    System.out.print("Please enter customer name to find subscriptions associated with it: ");
                                    String customerName = scanner.nextLine();
                                    databaseExplorer.findSubscriptionsViaCustomerName(customerName);
                                    break;
                            }
                        }
                        break;
                        
                    case 3:
                        System.out.println("Update Data in Database Selected!");
                        System.out.println("1. Add New Customer.");
                        System.out.println("2. Add New Subscription");
                        System.out.println("3. Update Customer");
                        System.out.println("4. Change Date Comic Book Released");
                        System.out.println("5. Delete Customer");
                        System.out.println("6. Delete All Subscriptions for Customer");
                        System.out.println("7. Exit");
                        System.out.print("Please enter selection:");
                        selection = scanner.nextInt();
                        scanner.nextLine();
                        if (selection != 7){
                            switch(selection){
                                case 1:
                                    System.out.println("Please enter the customer information:");
                                    System.out.print("Customer Name: ");
                                    String customerName = scanner.nextLine();
                                    System.out.print("Customer Address: ");
                                    String customerAddress = scanner.nextLine();
                                    System.out.print("Customer Phone Number:");
                                    String phoneNumber = scanner.nextLine();
                                    databaseUpdater.addNewCustomer(customerName, customerAddress, phoneNumber);
                                    break;
                                case 2:
                                    System.out.println("Please enter customer subscription information: ");
                                    System.out.print("Series Title:");
                                    String seriesTitle = scanner.nextLine();
                                    System.out.print("Customer ID:");
                                    int customer_id = scanner.nextInt();
                                    scanner.nextLine();
                                    databaseUpdater.addNewSubscription(seriesTitle, customer_id);
                                    break;
                                case 3:
                                    System.out.println("Please enter the customer information:");
                                    System.out.print("Customer Name: ");
                                    customerName = scanner.nextLine();
                                    System.out.print("Customer Address: ");
                                    customerAddress = scanner.nextLine();
                                    System.out.print("Customer Phone Number:");
                                    phoneNumber = scanner.nextLine();
                                    System.out.print("Customer ID:");
                                    customer_id = scanner.nextInt();
                                    scanner.nextLine();
                                    databaseUpdater.updateCustomer(customerName, customerAddress, phoneNumber, customer_id);
                                    break;
                                case 4:
                                    System.out.println("Please enter date comic book is released");
                                    System.out.print("Date: ");
                                    String date = scanner.nextLine();
                                    System.out.print("Book ID: ");
                                    int bookId = scanner.nextInt();
                                    scanner.nextLine();
                                    databaseUpdater.changeDateComicBookIsReleased(date, bookId);
                                    break;
                                case 5:
                                    System.out.println("Please enter the ID number of the customer to delete");
                                    System.out.print("Customer ID:");
                                    customer_id = scanner.nextInt();
                                    scanner.nextLine();
                                    databaseUpdater.deleteCustomer(customer_id);
                                    break;
                                case 6:
                                    System.out.println("Please enter customer to delete all subscriptions for");
                                    System.out.print("Customer ID:");
                                    customer_id = scanner.nextInt();
                                    scanner.nextLine();
                                    databaseUpdater.deleteAllSubscriptionForCustomer(customer_id);
                                    break;
                            }
                        }
                        break;
                }
            
            }
            else{
                System.out.println("Exiting System, thank you!");
                continueSystem = false;
            }
        }
        
    }
    
    FileReader fileReader = new FileReader();
    DatabaseExplorer databaseExplorer = new DatabaseExplorer();
    DatabaseUpdater databaseUpdater = new DatabaseUpdater();
}
