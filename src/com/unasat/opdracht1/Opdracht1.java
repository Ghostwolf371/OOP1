package com.unasat.opdracht1;
import java.util.Scanner;

public class Opdracht1 {
    public static void main(String[] args) {
        /* bouw app voor ovg beroodeling
           onvoldoende : 0 - 5,4
           voldoende: 5,5 - 8,0
           goed: 8,1 - 10
           boven 10: "So Valide beoordeling"*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the student grade? ");
        float grade = scanner.nextInt();

        if (grade >= 0 && grade <= 5.4){
            System.out.println("Student has a onvoldoende");
        } else if (grade >= 5.5 && grade <= 8.0) {
            System.out.println("Student has a voldoende");
        } else if (grade >= 8.1 && grade <= 10) {
            System.out.println("Student has a goed grade");
        } else {
            System.out.println("So Valide beoordeling");
        }

    }
}
