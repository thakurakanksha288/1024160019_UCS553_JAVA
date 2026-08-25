class InvalidUsernameException extends Exception { public InvalidUsernameException(String m) { super(m); } }
class InvalidPasswordException extends Exception { public InvalidPasswordException(String m) { super(m); } }
class AccountLockedException extends Exception { public AccountLockedException(String m) { super(m); } }

public class LoginSystem {
    private int failedAttempts = 0;
    private final int MAX_ATTEMPTS = 3;

    public void login(String user, String pass) throws Exception {
        if (failedAttempts >= MAX_ATTEMPTS) throw new AccountLockedException("Account locked due to failed attempts.");
        try {
            if (!"admin".equals(user)) throw new InvalidUsernameException("User not found.");
            if (!"pass123".equals(pass)) throw new InvalidPasswordException("Incorrect password.");
            failedAttempts = 0;
        } catch (Exception e) {
            failedAttempts++;
            throw e;
        } finally {
            System.out.println("Attempts used: " + failedAttempts + "/" + MAX_ATTEMPTS);
        }
    }
}