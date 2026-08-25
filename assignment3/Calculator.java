public class Calculator {
    public static double calculate(String num1Str, String num2Str, char op) throws Exception {
        try {
            double a = Double.parseDouble(num1Str);
            double b = Double.parseDouble(num2Str);

            if (op != '+' && op != '-' && op != '*' && op != '/') {
                throw new IllegalArgumentException("Invalid Operator: " + op);
            }
            if (op == '/' && b == 0) {
                throw new ArithmeticException("Cannot divide by zero.");
            }
            return op == '+' ? a + b : op == '-' ? a - b : op == '*' ? a * b : a / b;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid numeric input entered.");
        }
    }
}
