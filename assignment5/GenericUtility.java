package assignment5;
import java.util.Objects;
import java.util.Optional;

public class GenericUtility {

    // 1. isEqual(T a, T b) - checks whether two objects are equal (null-safe)
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    // 2. getDefault(Optional<T> value, T defaultValue) - returns contained value or default
    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    // 3. max(T a, T b) - returns the larger of two comparable objects
    public static <T extends Comparable<T>> T max(T a, T b) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) >= 0 ? a : b;
    }

    // 4. isNull(Object obj) - checks whether an object is null using the Objects class
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Testing isEqual() ===");
        System.out.println("isEqual(100, 100) : " + isEqual(100, 100));
        System.out.println("isEqual(\"Java\", \"Python\") : " + isEqual("Java", "Python"));
        System.out.println("isEqual(null, \"Hello\") : " + isEqual(null, "Hello"));

        System.out.println("\n=== 2. Testing getDefault() ===");
        Optional<String> presentEmail = Optional.of("user@example.com");
        Optional<String> emptyEmail = Optional.empty();
        
        System.out.println("Present Optional : " + getDefault(presentEmail, "guest@example.com"));
        System.out.println("Empty Optional   : " + getDefault(emptyEmail, "guest@example.com"));

        System.out.println("\n=== 3. Testing max() ===");
        System.out.println("max(45, 89) (Integers) : " + max(45, 89));
        System.out.println("max(\"Apple\", \"Banana\") (Strings) : " + max("Apple", "Banana"));

        System.out.println("\n=== 4. Testing isNull() ===");
        String nullString = null;
        String activeString = "Active Status";
        
        System.out.println("isNull(nullString) : " + isNull(nullString));
        System.out.println("isNull(activeString) : " + isNull(activeString));
    }
}