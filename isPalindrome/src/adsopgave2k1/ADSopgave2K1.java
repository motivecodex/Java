package adsopgave2k1;

import java.util.Scanner;
import java.util.Random;

/**
 * @author MOTIVECODEX
 */
public class ADSopgave2K1 {

    public static void main(String[] args) {

        String s = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a string to print forwards:");
        s = input.nextLine();
        System.out.println("Output");
        print(s, 0);
        System.out.println("\n");
        
        System.out.println("Enter a string to print backwards:");
        s = input.nextLine();
        System.out.println("Output");
        printB(s, 0);
        System.out.println("\n");
        
        System.out.println("Enter a string to check if it's Palindrome:");
        s = input.nextLine();
        String low = s.toLowerCase();
        if (isPalindrome(low, 0) == true) {
            System.out.println(s + " is a Palindrome");
        } else {
            System.out.println(s + " is not a Palindrome");
        }
    }

    /*
     This method 'print' will print the string entered by a user one by one
     */
    public static void print(String s, int pos) {
        if (pos < s.length()) {
            System.out.println(s.charAt(pos));
            print(s, pos + 1);
        }
    }

    /*
     This method 'printB' will print the string entered by a user one by one, 
     but this time it will reverse it.
     */
    public static void printB(String s, int pos) {
        if (pos < s.length()) {
            printB(s, pos + 1);
            System.out.println(s.charAt(pos));
        }
    }

    /*
     This method will match the first letter with the last letter.
     For example we use the word roor. These will start from 0 til 3, 0 being r and 3 being r.
     Every time the first letter matches the letter from the end it will try and match the next one.
     e.g. 0 matches 3, 1, matches 2. So this will be a Palindrome.
     Otherwise, if one does not match the other side, it will say it is not a Palindrome.
     */
    public static boolean isPalindrome(String s, int pos) {
        if (pos < s.length()) {
            if (s.charAt(pos) == s.charAt(s.length() - pos - 1)) {
                return isPalindrome(s, pos + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
