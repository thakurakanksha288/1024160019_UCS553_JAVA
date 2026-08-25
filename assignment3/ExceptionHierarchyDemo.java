public class ExceptionHierarchyDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Triggers ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Format Error: " + e.getMessage());
        } catch (Exception e) { // Catch-all base class
            System.out.println("General Exception: " + e.getMessage());
        }
    }
}