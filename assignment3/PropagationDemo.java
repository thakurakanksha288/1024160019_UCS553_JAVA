public class PropagationDemo {
    static void compute() {
        int value = 10 / 0; // Unhandled here; propagates up the call stack
    }

    public static void main(String[] args) {
        try { // Outer block
            try { // Inner block
                compute();
            } catch (NullPointerException e) {
                System.out.println("Inner caught NPE");
            }
        } catch (ArithmeticException e) { // Outer block catches propagated exception
            System.out.println("Outer block caught propagated error: " + e.getMessage());
        }
    }
}