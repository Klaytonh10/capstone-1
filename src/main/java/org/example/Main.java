package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        try(FileWriter write = new FileWriter("src/main/resources/transactions.csv")) {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String input;


            // date|time|description|vendor|amount
            while((input = bufferedReader.readLine()) != null) {
                String[] sections = input.split("\\|");
                int date; // use datetime
                String time; // use datetime

                System.out.println(input);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}