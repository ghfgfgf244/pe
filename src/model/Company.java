package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;

public class Company {

    private static ArrayList<Customer> company = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static File file = new File("src\\");
    static String path = file.getAbsolutePath();
    static Check check = new Check();

    public Company(String path, String fName) {
        loadData("\\customer.txt");
    }

    public static ArrayList<Customer> getCompany() {
        return company;
    }

    public static void setCompany(ArrayList<Customer> company) {
        Company.company = company;
    }

    public static void loadData(String fName) {
        String staffInfo;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + fName));
            while ((staffInfo = br.readLine()) != null) {
                String[] b = staffInfo.split(",");

                if (b.length == 4) {
                    String id = b[0];
                    String name = b[1].split(" ")[1];
                    String pn = b[2];
                    String dob = b[3].split(" ")[1];

                    if (check.checkID(id) && check.checkDob(dob)) {
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
                        } catch (ParseException ex) {
                            ex.getMessage();
                        }
                        company.add(new Customer(id, name, pn, date));
                    }
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void saveData(String fName) {
        try (PrintWriter pr = new PrintWriter(path + fName)) {
            for (Customer s : company) {
                String line = s.getCustomerID() + "," + s.getName() + ","
                        + new SimpleDateFormat("dd/MM/yyyy").format(s.getDateOfBirth()) + "," + s.getPhone() + "\n";
                pr.println(line);
            }
            pr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printCustomerList() {
        if (company.isEmpty()) {
            System.out.println("No customer found.");
        } else {
            for (Customer st : company) {
                System.out.println(st.toString());
            }
        }

    }

    public static void search(ArrayList<Customer> cus, Predicate<Customer> condition) {
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer s : cus) {
            if (condition.test(s)) {
                result.add(s);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No customer found with the option.");
        } else {
            result.forEach(System.out::println);
            System.out.println("Total: " + result.size());
        }
    }

    public static void sortByName() {
        System.out.println("List all customer that have DOB before user input date ");
        company.forEach(System.out::println);
        company.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("List all customer that have DOB after user input date ");
        company.forEach(System.out::println);
    }
//
//    public static void fixDuplicateIds() {
//        ArrayList<String> existIds = new ArrayList<>();
//        ArrayList<Customer> customerId = new ArrayList<>();
//        Random random = new Random();
//
//        for (Customer sta : company) {
//            String cusId = sta.getCustomerID();
//            if (existIds.contains(cusId)) {
//                customerId.add(sta);
//            } else {
//                existIds.add(cusId);
//            }
//        }
//
//        for (Customer staff : customerId) {
//            String newId;
//            do {
//                int ran = random.nextInt(100);
//                newId = "KH" + String.format("%02d", ran);
//            } while (existIds.contains(newId));
//
//            staff.setCustomerID(newId);
//            existIds.add(newId);
//
//        }
//        saveData("\\customer.txt");
//    }

}
