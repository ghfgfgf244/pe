package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer implements Comparable<Customer> {

    protected String customerID;
    protected String name;
    protected String phone;
    protected Date dateOfBirth;

    public Customer(String customerID, String name, String phone, Date dateOfBirth) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static String getValue(String ms) {
        Scanner sc = new Scanner(System.in);
        System.out.print(ms);
        return sc.nextLine();
    }

    @Override
    public int compareTo(Customer o) {
        return this.dateOfBirth.compareTo(o.dateOfBirth);

    }

    public static void StatisticalHomenetwork() {
        ArrayList<Customer> list1 = new ArrayList<>();
        ArrayList<Customer> list2 = new ArrayList<>();
        ArrayList<Customer> list3 = new ArrayList<>();

        for (Customer c : Company.getCompany()) {
            if (c.getPhone().contains("098")) {
                list1.add(c);
            } else if (c.getPhone().contains("090")) {
                list2.add(c);
            } else if (c.getPhone().contains("093")) {
                list3.add(c);
            }
        }
        printInfo(list1, "Viettel");
        printInfo(list2, "Vinaphone");
        printInfo(list3, "Mobiphone");

    }
    
    public static void printInfo(ArrayList<Customer> list,String homenetwork){
        System.out.println("Customer of " + homenetwork);
        System.out.println("------------------------");
        list.forEach(System.out::println);
        System.out.println("------------------------");
        System.out.println("Total: " + list.size());
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(getDateOfBirth());
        return customerID + " | "
                + name + " | "
                + phone + " | "
                + date;
    }

}
