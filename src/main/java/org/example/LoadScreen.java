package org.example;

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

        scanner.nextLine();
    }

    public void ledgerScreen() {

    }
}
