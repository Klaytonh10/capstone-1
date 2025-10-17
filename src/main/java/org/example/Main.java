package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    //Transactions list that is accessible throughout the program
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    //Lazy programmer = good programmer
    static String fileName = "src/main/resources/transactions.csv";

    public static void main(String[] args) {

        //load previous transactions into ArrayList
        readTransactions();

        mainMenu();

    }

    private static void mainMenu() {

        boolean exit = false;

        while (!exit) {

            System.out.println("""
                    =====================================
                        Accounting Ledger Application
                    =====================================
                    
                    D) Add Deposit
                    P) Make Payment
                    L) Ledger
                    X) Exit
                    """);
            System.out.print("Select an option: ");
            // get input
            String userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {
                case "D":
                    addTransaction(true);
                    break;// add deposit
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    exit = true;
                    System.out.println("Bye Bye");
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }

    //Takes boolean to determine if transaction is a deposit or a payment(positive or negative)
    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static void addTransaction(boolean isPositive) {
        // prompt user for the deposit information and save to csv

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String timeDate = localDateTime.format(formatter);
        String[] timeDateParts = timeDate.split("\\|");
        String date = timeDateParts[0];
        String time = timeDateParts[1];


        System.out.print("Enter Deposit Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor Name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Deposit Amount: $");
        double amount = scanner.nextDouble();
        //Turns to negative if payment
        if(!isPositive) {
            amount = amount * -1;
        }
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        if(!isPositive) {
            System.out.println("Payment of " + transaction.getAmount() + " to " + transaction.getVendor());
        } else {
            System.out.println("Deposit of " + transaction.getAmount() + " to " + transaction.getVendor());
        }
        transactions.add(transaction);

        //write transactions to file
        try(FileWriter fileWriter = new FileWriter(fileName, true)){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String tranString = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
            bufferedWriter.write(tranString);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void ledgerMenu() {
        //display ledger screen
        boolean exit = false;
        while(!exit) {
            System.out.println("""
                    =====================================
                                Ledger Screen           \s
                    =====================================
                   \s
                    A) Display all entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                   \s""");
            System.out.print("Select an option: ");
            char input = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch(Character.toUpperCase(input)) {
                case 'A':
                    displayAllEntries();
                    break;
                case 'D':
                    displayDeposits();
                    break;
                case 'P':
                    displayPayments();
                    break;
                case 'R':
                    reportsScreen();
                    break;
                case 'H':
                    exit = true;
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
        }
    }

    //Push existing transactions into ArrayList
    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static void readTransactions() {
        Collections.sort(transactions);
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String input;
            while((input = reader.readLine()) != null) {
                String[] sections = input.split("\\|");
                String date = sections[0];
                String time = sections[1];
                String description = sections[2];
                String vendor = sections[3];
                String amount = sections[4];
                Transaction transaction = new Transaction(date, time, description, vendor, Double.parseDouble(amount));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Display all positive transactions
    public static void displayDeposits() {
        System.out.println();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(" " + transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
        System.out.println();
    }

    //Display all transactions where positive or negative
    public static void displayAllEntries() {
        System.out.println();
        for (Transaction transaction : transactions) {
            System.out.println(" " + transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
        }
        System.out.println();
    }

    //Display all negative transactions
    public static void displayPayments() {
        System.out.println();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(" " + transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }

        }
        System.out.println();
    }

    public static void reportsScreen() {
        boolean exit = false;
        while(!exit){
            System.out.println("""
                    =====================================
                               Reports  Screen
                    =====================================
                    
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                    """);
            System.out.print("Select an option: ");
            char input = scanner.nextLine().charAt(0);
            switch (Character.toUpperCase(input)) {
                case '1':
                    monthToDate();
                    break;
                case '2':
                    previousMonth();
                    break;
                case '3':
                    yearToDate();
                    break;
                case '4':
                    previousYear();
                    break;
                case '5':
                    searchVendor();
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
        }
    }

    //Prompt user to input vendors, then display any transactions under those vendors
    public static void searchVendor() {
        System.out.println();
        System.out.println("Enter vendor name: ");
        String input = scanner.nextLine();
        for(Transaction transaction : transactions) {
            if(transaction.getVendor().equalsIgnoreCase(input)) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
        System.out.println();
    }

    //Display all transactions from previous years
    public static void previousYear() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayLastYear = today.minusYears(1).withDayOfYear(1);
        LocalDate lastDayLastYear = today.withDayOfYear(1).minusDays(1);

        System.out.println("Previous Month's Transactions: \n");

        for(Transaction transaction : transactions) {
            try {
                LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                if(!transactionDate.isBefore(firstDayLastYear) && !transactionDate.isAfter(lastDayLastYear)) {
                    System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
                }
            } catch (Exception e) {
                System.out.println("Error parsing transaction date: " + transaction.getDate());
            }
        }
    }

    //Display all transactions from the month prior to today
    public static void previousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayLastMonth = today.withDayOfMonth(1).minusDays(1);

        System.out.println("Previous Month's Transactions: \n");

        for(Transaction transaction : transactions) {
            try {
                LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                if(!transactionDate.isBefore(firstDayLastMonth) && !transactionDate.isAfter(lastDayLastMonth)) {
                    System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
                }
            } catch (Exception e) {
                System.out.println("Error parsing transaction date: " + transaction.getDate());
            }
        }
    }

    //Display all transactions from this year up until today
    public static void yearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        System.out.println("Month to Date Transactions: \n");
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            if(!transactionDate.isBefore(firstOfMonth) && !transactionDate.isAfter(today)) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
    }

    //Display all transactions from this month up until today
    public static void monthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = today.withDayOfYear(1);
        System.out.println("Month to Date Transactions: \n");
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            if(!transactionDate.isBefore(firstOfYear) && !transactionDate.isAfter(today)) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
    }
}