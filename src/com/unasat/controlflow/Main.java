package com.unasat.controlflow;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("What is 50 - 25? ");
//        int answer = scanner.nextInt();
//        int x = 9;
//        int y = 25;
//
//        if (x >= 10 && x <= 12 || y >= 20 && y <= 30 ){
//            System.out.println("Good!!");
//        }else {
//            System.out.println("Bad!!!");
//        }

//        if (answer == 25){
//            System.out.println("Nice!!!");
//        } else {
//            System.out.println("Wrong!!!!!");
//        }

        System.out.println("How old are you?");
        int age = scanner.nextInt();

        if (age <= 17){
            System.out.println("a");
        } else if (age >= 18 && age <= 20) {
            System.out.println("b");
        } else if (age >= 21 && age <= 59) {
            System.out.println("c");
        } else if (age >= 60) {
            System.out.println("d");
        }


    }
}