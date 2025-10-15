package org.example;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Transactions list
    static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "src/main/resources/transactions.csv";

    public static void main(String[] args) throws IOException  {

        //FileReader fileReader = new FileReader(fileName);
        //BufferedReader bufferedReader = new BufferedReader(fileReader);
//
        //bufferedReader.readLine();

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
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        };
    }

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
        if(!isPositive) {
            amount = amount * -1;
        }
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //write transactions to file
        try(FileWriter fileWriter = new FileWriter(fileName, true)){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String tranString = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
            bufferedWriter.write(tranString);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
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
                                Ledger Screen            
                    =====================================
                    
                    A) Display all entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    
                    """);
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

    public static ArrayList<Transaction> readTransactions() {
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
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return transactions;
    }

    public static void displayDeposits() {
        System.out.println();
        for(int i=0; i<transactions.size(); i++) {
            if(transactions.get(i).getAmount()>0){
                System.out.println(" " + transactions.get(i).getAmount());
            }
        }
        System.out.println();
    }

    public static void displayAllEntries() {
        System.out.println();
        for(int i=0; i<transactions.size(); i++) {
            if (transactions.get(i).getAmount() < 0) {
                System.out.println(transactions.get(i).getAmount());
            } else {
                System.out.println(" " + transactions.get(i).getAmount());
            }
        }
        System.out.println();
    }

    // get all negative transactions
    public static void displayPayments() {

        System.out.println();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() < 0) {
                System.out.println(transactions.get(i).getAmount());
            }

        }
        System.out.println();
    }

    public static void reportsScreen() {
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
    }

    //public static void searchVendor() {
    //
    //    for(int i = 0; i < transactions.size(); i++) {
    //        if(transactions.get(i).getVendor().equalsIgnoreCase()) {
    //
    //        }
    //    }
    //}
}