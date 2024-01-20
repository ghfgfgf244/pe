package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Check;
import model.Company;
import static model.Company.getCompany;
import model.Customer;
import view.Menu;

public class CompanyManagement extends Menu<String> {

    static Scanner sc = new Scanner(System.in);
    static String[] menu = {"Display all customer ", "Search customer", "Sort customer by Name", "Statistical homenetwork", "Exit"};
    static String fName = "\\customer.txt";
    static Check check = new Check();

    public CompanyManagement() {
        super("Staff Management System", menu);
        Company.loadData(fName);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                displayAllCustomer();
            }
            case 2 -> {
                searchCustomer();
            }
            case 3 -> {
                Company.sortByName();
            }
            case 4 -> {
                Customer.StatisticalHomenetwork();
            }
            case 5 -> {
                System.out.println("Exiting...");
            }
            default -> {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void main(String[] args) {
        CompanyManagement me = new CompanyManagement();
        me.run();
    }

    public void displayAllCustomer() {
        Company.printCustomerList();
    }

    public static void searchCustomer() {
        String[] tt = {"Find by ID", "Find by Date of birth", "Back to menu"};
        Menu searchCustomer = new Menu("Customer Searching", tt) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        String ID = check.enterID("Enter id: ");
                        Company.search(getCompany(), id -> id.getCustomerID().equals(ID));
                    }
                    case 2 -> {
                      System.out.print("Enter the date of birth: ");
                        String year = sc.nextLine();
                        Company.search(getCompany(), date -> {
                            Date dob = date.getDateOfBirth();
                            if (dob != null) {
                                String bookYear = new SimpleDateFormat("dd/MM/yyyy").format(dob);
                                return bookYear.equals(year);
                            }
                            return false;
                        });
                    }                    
                    case 3 -> {
                        System.out.println("Returning menu...");
                    }
                    default -> {
                        System.out.println("Invalid choice. Please choose again.");
                    }
                }
            }
        };
        searchCustomer.run();
    }

}
