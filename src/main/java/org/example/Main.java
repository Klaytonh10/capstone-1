package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Transactions list
    static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static Scanner scanner = new Scanner(System.in);
    static char userInput;

    public static void main(String[] args) throws IOException  {

        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //transactions = loadTransactions();
        mainMenu();

    }

    private static void mainMenu() {
        userInput = ' ';
        do {
            System.out.println("""
                    =====================================
                        Accounting Ledger Application
                    =====================================
                    
                    D) Add Deposit
                    P) Make Payment
                    L) Ledger
                    X) Exit
                    
                    """);
            // get input
            userInput = scanner.nextLine().charAt(0);

            switch (Character.toUpperCase(userInput)) {
                case 'D' -> { addDepositMenu(); break;}
                case 'P' -> { makePayment(); break; }
                case 'L' -> { ledgerMenu(); break;}
            }
        } while (Character.toUpperCase(userInput) != 'X');
    }

    public static void makePayment() {
    }

    public static void addDepositMenu() {
        // prompt user for the deposit information and save to csv

        String date = LocalDate.now().toString();
        System.out.print("Enter Current Time: ");
        String time = scanner.nextLine();
        System.out.print("Enter Deposit Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor Name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Deposit Amount: $");
        String amount = scanner.nextLine();
        String[] data = {date, time, description, vendor, amount};
        Transaction transaction = new Transaction(data[0], data[1], data[2], data[3], data[4]);
        transactions.add(transaction);

        System.out.println(transaction + " added");
    }

    public static void ledgerMenu() {
        //display ledger screen
    }

    public static void loadTransactions() {

    }
}