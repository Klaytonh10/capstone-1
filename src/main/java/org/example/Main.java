package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Person person = new Person();
        //System.out.println(person.toString());

        Scanner scanner = new Scanner(System.in);

        LoadScreen loadScreen = new LoadScreen();

        //Home Screen
        loadScreen.homeScreen(scanner);


    }
}