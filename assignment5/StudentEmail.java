package assignment5;
public import java.util.Optional;

public class StudentEmail{

    public static void main(String[] args) {
        Optional<String> studentEmail = Optional.of("123@example.com");

        Optional<String> emptyEmail = Optional.empty();

        System.out.println("Display email using ifPresent()");

        studentEmail.ifPresent(email -> System.out.println("Found student email: " + email));

        emptyEmail.ifPresent(email -> System.out.println("Found student email: " + email));



        System.out.println("Use orElse() for default values");

        String email1 = studentEmail.orElse("default.student@university.edu");
        String email2 = emptyEmail.orElse("default.student@university.edu");

        System.out.println("Student Email 1: " + email1);
        System.out.println("Student Email 2: " + email2 + "\n");


        System.out.println("Use orElseThrow() to handle missing values");

    }
}
