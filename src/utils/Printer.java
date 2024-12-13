package utils;

import java.util.ArrayList;

public class Printer {
    public static final ArrayList<String> printList = new ArrayList<>();

    public static void print(boolean clear) {
        if(printList.isEmpty()) return;
        if(clear) Utils.clearConsole();
        for (String string : printList) {
            System.out.println(string);
        }
        System.out.println("\n");
    }
}
