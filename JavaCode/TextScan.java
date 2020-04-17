//Provided by the CSCI 1933 Lecture Examples on canvas.umn.edu
//Modified by Evan Newlund, ID: 5473869, X-500: newlu004
//Modification resulted in scan() method returning an array, rather than simply printing the tokens

// TextScan.java
// Opens text file supplied on the command line
// Usage:  java TextScan filename
// Counts all tokens found (a token is anything delimited by a space character)
// Displays each token found to the screen
import java.util.Scanner;
import java.io.*;

public class TextScan {

    public static String[] scan (String fileName) {

        Scanner readFile = null;
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + fileName);
        try {
            readFile = new Scanner(new File("src/" + fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found");
            System.exit(1);  
        }

        System.out.println("Connection to file: " + fileName + " successful");
        System.out.println();

        StringNode header = new StringNode("");
        StringNode temp = header;
        while (readFile.hasNext()) {
            s = readFile.next();
            StringNode newNode = new StringNode(s);
            temp.setNext(newNode);
            temp = temp.getNext();
            count++;
        }
        String[] tokens = new String[count];
        StringNode ptr = header;
        int count2 = 0;
        while (count2 < tokens.length) {
            tokens[count2] = ptr.getData();
            ptr = ptr.getNext();
            count2++;
        }
        return tokens;

    }  // main

}  // TextScan
