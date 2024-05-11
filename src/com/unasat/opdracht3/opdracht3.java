package com.unasat.opdracht3;

public class opdracht3 {

    private String week;
    private int day;
    static String [] weeks = new String[] {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
    static int [] cijfers = new int[]{1,2,3,4,5,6,7,8,9,10};




    public static void main(String[] args) {
        opdracht3 main = new opdracht3();
        weken(weeks);
        tafel(cijfers, 10);
        boolean isErLess = weken_2(weeks, "maandag");

        if (isErLess = true){
            System.out.println("Er is les");
        }




    }

    static void weken(String [] weeks){
        for (int i = 0; i < weeks.length; i++){
            if (i == 3 || i == 4){
                System.out.println( weeks[i]+ ": " +  "Er is les");
            }else {
                System.out.println(weeks[i]+ ": " +  "geen les");
            }
        }
    }

    static void tafel(int[] cijfers, int tafel){
        for(int i = 0; i < cijfers.length; i++){
            System.out.println((i + 1) + " x " + tafel + " = " + (cijfers[i] * tafel));
        }
    }

    static boolean weken_2(String [] weeks, String dagNaam){

        boolean isErLes = false;
        for (int i = 0; i < weeks.length; i++){
            switch (dagNaam){
                case "maandag":
                    System.out.println("maandag: geen les");
                    break;
                case "dinsdag":
                    System.out.println("dinsdag: geen les");
                    break;
                case "woensdag":
                    System.out.println("woensdag: geen les");
                    break;
                case "donderdag":
                    System.out.println("donderdag: Er is les");
                    break;
                case "vrijdag":
                    System.out.println("vrijdag: Er is les");
                default:
                    System.out.println("Broken");
            }
        }

        return isErLes;
    }
}
