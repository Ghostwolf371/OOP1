package com.unasat.opdarcht2;

public class Gliss {

    private int perceelnummmer;

    public int getPerceelnummmer() {
        return perceelnummmer;
    }

    public void setPerceelnummmer(int perceelnummmer) {
        this.perceelnummmer = perceelnummmer;
    }

    public boolean isPerceelGeregistreerd(int perceelnum){

        boolean geregistreerd = false;
        if (perceelnum > 0){
            geregistreerd = true;
            System.out.println("You are Registered");
        }else {
            System.out.println("You are not Registered");
        }
        return geregistreerd;
    }

    public static void main(String[] args) {
        Gliss gliss = new Gliss();

    }
}
