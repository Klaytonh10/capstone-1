package org.example;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class LoadScreen {


    public void homeScreen(Scanner scanner) {
        System.out.println("""
                  ================================
                   ACCOUNTING LEDGER APPLICATION
                  ================================
                
                  Options:
                
                  D) Add Deposit
                  P) Make Payment (Debit)
                  L) Ledger
                  X) Exit
                """);

        String input;
        boolean isTrue = true;

        while (true) {
            input = scanner.nextLine();

            switch (input) {
                case "d":
                    System.out.println("You selected: " + input);
                    break;
                case "p":
                    System.out.println("You selected: " + input);
                    break;
                case "l":
                    System.out.println("You selected: " + input);
                    break;
                case "x":
                    System.out.println("You selected: " + input);
                    break;
                default:
                    System.out.println(input + " is not an option...try again");
            }
        }

    }

    public void ledgerScreen() {

    }
}
