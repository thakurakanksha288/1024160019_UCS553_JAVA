//question8 - Write a program to reverse a number.

import java.util.Scanner;

public class Reversenumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer to reverse: ");
        int number = scanner.nextInt();

        int reversedNumber = reverseNumber(number);
        System.out.println("The reversed number is: " + reversedNumber);
    }

    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }
}