package org.example;

import javax.swing.text.DateFormatter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadScreen {



    public void homeScreen(Scanner scanner) throws IOException {
        System.out.println("""
                  ================================
                   ACCOUNTING LEDGER APPLICATION
                  ================================
                
                  Options:
                
                  D) Add Deposit
                  P) Make Payment (Debit)
                  L) Ledger
                  X) Exit Program
                  
                """);
        String input;
        // loops until valid input is given
        for (int i = 0; i == 0; i = i) {
            input = scanner.nextLine();
            switch (input) {
                case "d":
                    System.out.println("You selected: " + input.toUpperCase());
                    i++;
                    ledgerScreen(scanner);
                    break;
                case "p":
                    System.out.println("You selected: " + input.toUpperCase());
                    i++;
                    paymentScreen(scanner);
                    break;
                case "l":
                    System.out.println("You selected: " + input.toUpperCase());
                    i++;
                    break;
                case "x":
                    System.out.println("You selected: " + input.toUpperCase());
                    i++;
                    break;
                default:
                    System.out.print(input + " is not an option...try again: ");
            }
        }
    }

    public void depositScreen(Scanner scanner) {

    }

    public void paymentScreen(Scanner scanner) throws IOException {
        System.out.println("""
                
                """);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try(FileWriter writer = new FileWriter("src/main/resources/transactions.csv")) {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String input;

            while((input = bufferedReader.readLine()) != null) {
                String[] sections = input.split("\\|");
            }
        } catch(DateTimeException e) {
            System.out.println(e);
        }
    }

    public void ledgerScreen(Scanner scanner) {

        System.out.println("""
                ==================================
                              Ledger              
                ==================================
                
                Display: 
                
                A) All Entries
                D) Deposits Only
                P) Payments Only
                R) Custom Reports
                
                """);


    }

    public void exitProgram() {

    }
}
