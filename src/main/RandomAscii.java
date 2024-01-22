package main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class RandomAscii {
    Random rand;
    int code;
    boolean upper;
    char[] codeColl;
    String original, descend, ascend;
    public static void main(String[] args) {
        new RandomAscii();
    }
    public RandomAscii() {
        rand = new Random();
        codeColl = new char[6];
        original = "";
        descend = "";
        ascend = "";
        genRandomCode();
        completeCode();
        save();
        load();
    }
    public void genRandomCode() {
        for (int i = 0; i < 6; i++) {
            codeColl[i] = (char) choose();
            original += codeColl[i];
        }
    }
    public void completeCode() {
        char[] ascendedCode = sort(codeColl.clone(), false);
        char[] descendedCode = sort(codeColl.clone(), true);
        for (int i = 0;i < 6; i++) {
            ascend += ascendedCode[i];
        }
        for (int i = 0;i < 6; i++) {
            descend += descendedCode[i];
        }
    }
    public int choose() {
        upper = rand.nextBoolean();
        if (upper) {
            code = rand.nextInt(65, 91);
        } else {
            code = rand.nextInt(97, 123);
        }
        return code;
    }
    public char[] sort(char[] arr, boolean descend) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if (descend) {
                    if (arr[j] < arr[j + 1]) {
                        char temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                } else {
                    if (arr[j] > arr[j + 1]) {
                        char temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }

            }
        }
        return arr;
    }
    public void save() {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("data.txt"));
            writer.println("Ascend code: " + ascend);
            writer.println("Descend code: " + descend);
            writer.println("Original code: " + original);
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to saved");
        }
    }
    public void load() {
        try {
            Scanner reader = new Scanner(new File("data.txt"));
            while(reader.hasNext()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch(IOException e) {
            System.out.println("Failed to output text");
        }
    }
}
