package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Check {

    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Check() {
    }

    public String enterID(String st) {
        System.out.print(st);
        String id = sc.nextLine();
        while (checkID(id) == false) {
            System.out.println("Invalid ID| ID format: KHxx");
            System.out.print(" Try again. " + st);
            id = sc.nextLine();
        }
        return id;
    }

    public boolean checkID(String data) {
        boolean isDataValid = true;
        try {
            isValidCusId(data);
        } catch (ValidationException e) {
            isDataValid = false;
        }
        return isDataValid;
    }

    public Date enterDob(String st) {
        System.out.print(st);
        String dateStr = sc.nextLine();
        while (checkDob(dateStr) == false) {
            System.out.println("Invalid date! Date format: dd/MM/yyyy");
            System.out.print(" Try again. " + st);
            dateStr = sc.nextLine();
        }
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            System.out.println("Invalid date! Date format: dd/MM/yyyy");
        }
        return date;
    }

    public boolean checkDob(String data) {
        boolean isDataValid = true;
        try {
            isValidateDob(data);
        } catch (ValidationException ex) {
            isDataValid = false;
        }
        return isDataValid;
    }
     
    public void isValidCusId(String data) throws ValidationException {
        if (!data.startsWith("KH") || data.length() != 4 || !data.matches("KH\\d{2}")) {
            throw new ValidationException("The CusID does not accept the format: KHxx");
        }
    }

    public void isValidateDob(String data) throws ValidationException {
        if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ValidationException("The Date Published must be match format: dd/MM/yyyy");
        }
    }

    public class ValidationException extends Exception {
        public ValidationException(String st) {
            super(st);
        }
    }

}
