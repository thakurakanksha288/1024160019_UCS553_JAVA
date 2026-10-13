package assignment5;
import java.util.Objects;
import java.util.Optional;

// Generic class bounded by T extends Number
public class Result<T extends Number> {
    private final String studentName;
    private final T[] marks;
    private final double passingThreshold;

    // 5. Use Objects.requireNonNull() to validate required inputs
    public Result(String studentName, T[] marks, double passingThreshold) {
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null!");
        this.marks = Objects.requireNonNull(marks, "Marks array cannot be null!");
        this.passingThreshold = passingThreshold;
    }

    // 2. Calculate percentage or average using numeric conversion methods
    public double calculateAverage() {
        if (marks.length == 0) return 0.0;
        
        double sum = 0.0;
        for (T mark : marks) {
            if (mark != null) {
                sum += mark.doubleValue(); // Safe numeric conversion
            }
        }
        return sum / marks.length;
    }

    // 3. Determine whether the student has passed based on a specified threshold
    public boolean hasPassed() {
        return calculateAverage() >= passingThreshold;
    }

    // 4. Display the result using Optional if the marks are available
    public void displayResult() {
        Optional<T[]> optionalMarks = Optional.ofNullable(marks);

        optionalMarks.ifPresent(m -> {
            double average = calculateAverage();
            boolean passed = hasPassed();
            System.out.println("----------------------------------------");
            System.out.println("Student Name : " + studentName);
            System.out.println("Average Mark : " + String.format("%.2f", average));
            System.out.println("Threshold    : " + passingThreshold);
            System.out.println("Status       : " + (passed ? "PASSED 🎉" : "FAILED ❌"));
            System.out.println("----------------------------------------");
        });
    }

    public static void main(String[] args) {
        // 1. Store marks using a generic type (Integer array)
        Integer[] intMarks = { 85, 90, 78, 92, 88 };
        Result<Integer> student1 = new Result<>("Alice Johnson", intMarks, 75.0);
        student1.displayResult();

        // Test with Double marks
        Double[] doubleMarks = { 55.5, 60.0, 48.5, 52.0 };
        Result<Double> student2 = new Result<>("Bob Smith", doubleMarks, 50.0);
        student2.displayResult();

        // Testing Objects.requireNonNull() validation
        try {
            System.out.println("\nTesting input validation (passing null name)...");
            Result<Integer> invalidStudent = new Result<>(null, intMarks, 50.0);
        } catch (NullPointerException e) {
            System.out.println("Caught Expected Validation Error: " + e.getMessage());
        }
    }
}