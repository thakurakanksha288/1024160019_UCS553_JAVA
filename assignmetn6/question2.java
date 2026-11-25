import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class question2 {

    public static void main(String[] args) {
        String inputFile = "application.log";
        String outputFile = "filtered_logs.txt";

        // Create a sample log file so the code runs standalone
        createSampleLogFile(inputFile);

        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write("=== FILTERED LOG ENTRIES (ERROR & WARNING) ===");
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip blank lines
                totalEntries++;

                if (line.contains("ERROR") || line.contains("WARNING")) {
                    writer.write(line);
                    writer.newLine();

                    if (line.contains("ERROR")) errorCount++;
                    if (line.contains("WARNING")) warningCount++;
                }
            }

            // Append summary at the end of the output file
            writer.newLine();
            writer.write("=== LOG ANALYSIS SUMMARY ===");
            writer.newLine();
            writer.write("Total Log Entries Processed: " + totalEntries);
            writer.newLine();
            writer.write("Total ERRORs found: " + errorCount);
            writer.newLine();
            writer.write("Total WARNINGs found: " + warningCount);
            writer.newLine();

            System.out.println("Log analysis complete!");
            System.out.println("Total Entries: " + totalEntries + " | Errors: " + errorCount + " | Warnings: " + warningCount);
            System.out.println("Filtered results saved to: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error processing log files: " + e.getMessage());
        }
    }

    // Helper method to generate sample input data
    private static void createSampleLogFile(String fileName) {
        try (BufferedWriter sampleWriter = new BufferedWriter(new FileWriter(fileName))) {
            sampleWriter.write("2026-11-25 10:00:01 INFO Application started successfully.");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-25 10:02:14 WARNING High memory usage detected (82%).");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-25 10:05:30 INFO User 'rahul_k' logged in.");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-25 10:08:45 ERROR Database connection timed out.");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-25 10:12:00 WARNING Disk space running low on /dev/sda1.");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-25 10:15:22 ERROR NullPointerException in PaymentService.");
            sampleWriter.newLine();
        } catch (IOException e) {
            System.err.println("Could not create sample log file: " + e.getMessage());
        }
    }
}