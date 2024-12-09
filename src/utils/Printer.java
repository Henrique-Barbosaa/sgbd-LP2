package utils;

import java.util.ArrayList;

public class Printer {
    public static final ArrayList<String> printList = new ArrayList<>();

    public static void print(){
        Utils.clearConsole();
        System.out.println("----------------------------------");
        for (String string : printList) {
            System.out.println(string);
        }
        System.out.println("----------------------------------\n");
    }
}
