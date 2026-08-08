//question7 - Write a program to find the number of digits in a given integer.

import java.util.Scanner;

public class Numberofdigits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        int digitCount = countDigits(number);
        System.out.println("The number of digits in " + number + " is: " + digitCount);
    }

    public static int countDigits(int num) {
        if (num == 0) {
            return 1; // Special case for 0
        }
        
        int count = 0;
        num = Math.abs(num); // Handle negative numbers

        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }
}
