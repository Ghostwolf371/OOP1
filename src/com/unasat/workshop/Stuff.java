package com.unasat.workshop;

public class Stuff {

    public String fizzBuzz(Integer i) {

        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(i);
        }
    }

    public static void main(String[] args) {
        Stuff stuff = new Stuff();
        System.out.println(stuff.fizzBuzz(30));
    }
}
