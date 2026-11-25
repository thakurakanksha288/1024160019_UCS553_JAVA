import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 1. Employee class implementing Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Dept: %-12s | Salary: ₹%,.2f",
                employeeId, name, department, salary);
    }
}

// 2. Main class to handle Serialization & Deserialization
public class EmployeeSerializationDemo {

    public static void main(String[] args) {
        String filename = "employees.dat";

        // Create multiple Employee objects
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(101, "Aarav Sharma", "Engineering", 85000.00));
        employeeList.add(new Employee(102, "Priya Verma", "Product", 92000.00));
        employeeList.add(new Employee(103, "Rohan Mehta", "Finance", 74500.00));
        employeeList.add(new Employee(104, "Ananya Iyer", "HR", 68000.00));

        // --- SERIALIZATION ---
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employeeList);
            System.out.println("Successfully serialized " + employeeList.size() + " employee records to '" + filename + "'.\n");
        } catch (IOException e) {
            System.err.println("Serialization Error: " + e.getMessage());
        }

        // --- DESERIALIZATION ---
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            @SuppressWarnings("unchecked")
            List<Employee> retrievedEmployees = (List<Employee>) ois.readObject();

            System.out.println("=== DESERIALIZED EMPLOYEE RECORDS ===");
            for (Employee emp : retrievedEmployees) {
                System.out.println(emp);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization Error: " + e.getMessage());
        }
    }
}