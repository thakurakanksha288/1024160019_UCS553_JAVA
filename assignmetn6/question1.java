package assignment6;


import java.io.*;
public class question1 {
    public static void writeStudentDetails(String filePath, String rollNo, String name, String course, double gpa) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String record = rollNo + "," + name + "," + course + "," + gpa;
            writer.write(record);
            writer.newLine();
            System.out.println("Record saved successfully for Roll No: " + rollNo);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void readStudentDetails(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    System.out.printf("Roll No: %-5s | Name: %-15s | Course: %-10s | GPA: %s\n",
                            data[0], data[1], data[2], data[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public static void copyBinaryFile(String sourcePath, String destPath) {
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("\nBinary file copied successfully from '" 
                               + sourcePath + "' to '" + destPath + "'");

        } catch (IOException e) {
            System.err.println("Error copying binary file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String textFileName = "student_records.txt";
        String sampleImage = "student_photo.png";
        String sampleImageCopy = "student_photo_backup.png";

        File dummyBinary = new File(sampleImage);
        if (!dummyBinary.exists()) {
            try (FileOutputStream fos = new FileOutputStream(dummyBinary)) {
                byte[] sampleBytes = { 0x48, 0x65, 0x6C, 0x6C, 0x6F };
                fos.write(sampleBytes);
            } catch (IOException e) {
                System.err.println("Initialization error: " + e.getMessage());
            }
        }

        System.out.println("=== Writing Student Details ===");
        writeStudentDetails(textFileName, "101", "Aarav Sharma", "B.Tech CSE", 8.9);
        writeStudentDetails(textFileName, "102", "Priya Verma", "B.Tech ECE", 9.2);

        System.out.println("\n=== Reading Student Details ===");
        readStudentDetails(textFileName);

        System.out.println("\n=== Copying Binary File ===");
        copyBinaryFile(sampleImage, sampleImageCopy);
    }
}

