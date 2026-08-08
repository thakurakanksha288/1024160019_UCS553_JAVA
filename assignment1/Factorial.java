//question3- Find the factorial of the number 

import java.util.Scanner;

public class Factorial{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number:");
        int num = scanner.nextInt();
        int factorial = 1;
        for(int i = 1; i <= num; i++){
            factorial *= i;
        }
        System.out.println("Factorial of " + num + " is: " + factorial);
    }
}