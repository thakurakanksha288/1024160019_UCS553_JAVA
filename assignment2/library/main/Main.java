package library.main;

import library.model.LibraryResource;
import library.model.Book;
import library.model.DigitalResource;
import library.service.ResourceService;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];

        resources[0] = new Book(1, "Effective Java", "Joshua Bloch");
        resources[0].setOverdueDays(4);

        resources[1] = new DigitalResource(2, "Deep Learning eBook", "Ian Goodfellow");
        resources[1].setOverdueDays(10);

        resources[2] = new Book(3, "Clean Code", "Robert C. Martin");
        resources[2].setOverdueDays(0);

        resources[3] = new DigitalResource(4, "OS Concepts (PDF)", "Silberschatz");
        resources[3].setOverdueDays(7);

        resources[4] = new Book(5, "Data Structures", "Seymour Lipschutz");
        resources[4].setOverdueDays(2);

        ResourceService service = new ResourceService();

        for (LibraryResource resource : resources) {
            if (!InputValidator.isValidResourceId(resource.getId())
                    || !InputValidator.isValidOverdueDays(resource.getOverdueDays())) {
                System.out.println("Invalid resource skipped.");
                continue;
            }
            service.displayDetails(resource);
        }

        System.out.println();
        LibraryResource.displayTotalResources();

        double totalFine = service.calculateTotalOverdueFine(resources);
        System.out.println("Total fine of all overdue resources: Rs." + totalFine);
    }
}
