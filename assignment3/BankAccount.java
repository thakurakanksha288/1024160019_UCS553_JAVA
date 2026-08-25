class InsufficientBalanceException extends Exception { public InsufficientBalanceException(String m) { super(m); } }
class InvalidAmountException extends Exception { public InvalidAmountException(String m) { super(m); } }
class AccountNotFoundException extends Exception { public AccountNotFoundException(String m) { super(m); } }

class BankAccount {
    private String accNo;
    private double balance;

    public BankAccount(String accNo, double balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero.");
        if (amount > balance) throw new InsufficientBalanceException("Insufficient balance.");
        balance -= amount;
    }
}