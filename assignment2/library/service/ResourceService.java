package library.service;

import library.model.LibraryResource;
import library.model.Printable;

public class ResourceService {

    public void displayDetails(LibraryResource resource) {
        if (resource instanceof Printable) {
            ((Printable) resource).printDetails();
        }
    }

    public double calculateTotalOverdueFine(LibraryResource[] resources) {
        double totalFine = 0.0;
        for (LibraryResource resource : resources) {
            if (resource != null && resource.getOverdueDays() > 0) {
                totalFine += resource.calculateFine(resource.getOverdueDays());
            }
        }
        return totalFine;
    }
}
