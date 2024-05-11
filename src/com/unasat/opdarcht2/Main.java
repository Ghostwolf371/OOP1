package com.unasat.opdarcht2;

public class Main {

    public static void main(String[] args) {
        Gliss gliss = new Gliss();

        gliss.isPerceelGeregistreerd(50);
        gliss.setPerceelnummmer(50);

        System.out.println(gliss.getPerceelnummmer());
    }
}
