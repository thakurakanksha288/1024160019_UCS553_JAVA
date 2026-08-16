package library.model;

public abstract class LibraryResource {

    private int resourceId;
    private String title;
    private String author;
    protected int overdueDays;
    private static String libraryName = "GNDEC Central Library";

    private static int resourceCounter = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        this.overdueDays = 0;
        resourceCounter++;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public abstract double calculateFine(int overdueDays);

    protected int getResourceId() {
        return resourceId;
    }

    public int getId() {
        return resourceId;
    }

    protected void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String getAuthor() {
        return author;
    }

    protected void setAuthor(String author) {
        this.author = author;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static void setLibraryName(String name) {
        libraryName = name;
    }

    public static int getResourceCounter() {
        return resourceCounter;
    }

    public static void displayTotalResources() {
        System.out.println("Total resources created: " + resourceCounter);
    }

    public String getBaseDetails() {
        return "Resource ID: " + resourceId + " | Title: " + title
                + " | Author: " + author + " | Library: " + libraryName;
    }
}
