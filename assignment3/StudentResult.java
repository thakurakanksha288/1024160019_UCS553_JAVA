class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) { super(message); }
}

public class StudentResult {
    public static void processMarks(int[] marks) throws InvalidMarksException {
        int total = 0;
        for (int mark : marks) {
            if (mark < 0 || mark > 100) {
                throw new InvalidMarksException("Invalid mark: " + mark + ". Must be between 0 and 100.");
            }
            total += mark;
        }
        double percentage = total / (double) marks.length;
        System.out.println("Total: " + total + " | Percentage: " + percentage + "%");
    }
}