//question9 - Write a program to find the power of a number.

import java.util.Scanner;

public class Powerofnumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base number: ");
        double base = scanner.nextDouble();
        System.out.print("Enter the exponent: ");
        double exponent = scanner.nextDouble();
        
        double result = Math.pow(base, exponent);
        
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);
        
        scanner.close();
    }
}