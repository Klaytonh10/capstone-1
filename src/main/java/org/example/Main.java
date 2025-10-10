package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LoadScreen loadScreen = new LoadScreen();

        //Home Screen
        try {
            loadScreen.homeScreen(scanner);
        } catch(Exception e){
            System.out.println(e);
        }


    }
}