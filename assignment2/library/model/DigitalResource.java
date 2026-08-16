package library.model;

public class DigitalResource extends LibraryResource implements Printable {

    private static final double FINE_PER_DAY = 2.0;

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays > 0 ? overdueDays * FINE_PER_DAY : 0.0;
    }

    @Override
    public void printDetails() {
        System.out.println(getBaseDetails() + " | Type: Digital Resource | Overdue Days: "
                + overdueDays + " | Fine: Rs." + calculateFine(overdueDays));
    }
}
