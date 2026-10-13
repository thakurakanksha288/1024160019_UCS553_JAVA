package assignment5;

public class NumericArraySum {
    public static <T extends Number> double calculateSum(T[] array) {
        double sum = 0.0;
        for (T element : array) {
            if (element != null) {
                sum += element.doubleValue();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Integer[] intArray = {10, 20, 30, 40, 50};
        System.out.println("Sum of Integer array: " + calculateSum(intArray));

        Float[] floatArray = {1.5f, 2.5f, 3.5f, 4.5f};
        System.out.println("Sum of Float array: " + calculateSum(floatArray));

        Double[] doubleArray = {10.55, 20.45, 30.0};
        System.out.println("Sum of Double array: " + calculateSum(doubleArray));
    }
}