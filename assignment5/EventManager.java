package assignment5;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class EventManager {
    private String eventName;
    private LocalDate eventDate;
    private LocalTime startTime;
    private Optional<String> venueName; // Requirement 6: Optional venue name

    // 1. Accept an event name, event date, and start time (along with optional venue)
    public EventManager(String eventName, LocalDate eventDate, LocalTime startTime, String venueName) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.startTime = startTime;
        // Optional.ofNullable handles both present strings and nulls safely
        this.venueName = Optional.ofNullable(venueName);
    }

    // 2. Display the event details
    public void displayEventDetails() {
        System.out.println("----------------------------------------");
        System.out.println("Event Name   : " + eventName);
        System.out.println("Event Date   : " + eventDate);
        System.out.println("Start Time   : " + startTime);
        
        // 5. Calculate the event's end time if the event lasts for two hours
        LocalTime endTime = startTime.plusHours(2);
        System.out.println("End Time     : " + endTime + " (2 hours duration)");

        // 6. Use Optional<String> to handle venue and display fallback if absent
        String venueDisplay = venueName.orElse("Venue not specified");
        System.out.println("Venue        : " + venueDisplay);
        System.out.println("----------------------------------------");
    }

    // 3. Check whether the event date is in the future, today, or in the past
    public void checkEventTimingStatus() {
        LocalDate currentDate = LocalDate.now();

        System.out.print("Status for '" + eventName + "': ");
        if (eventDate.isAfter(currentDate)) {
            System.out.println("The event is in the FUTURE.");
        } else if (eventDate.isBefore(currentDate)) {
            System.out.println("The event is in the PAST.");
        } else {
            System.out.println("The event is TODAY! 📅");
        }
    }

    // 4. Calculate the number of days remaining until the event
    public void calculateDaysRemaining() {
        LocalDate currentDate = LocalDate.now();
        long daysRemaining = ChronoUnit.DAYS.between(currentDate, eventDate);

        if (daysRemaining > 0) {
            System.out.println("Days remaining until event: " + daysRemaining + " day(s).");
        } else if (daysRemaining == 0) {
            System.out.println("The event is happening today!");
        } else {
            System.out.println("The event took place " + Math.abs(daysRemaining) + " day(s) ago.");
        }
    }

    public static void main(String[] args) {
        // Test with a future event including a venue
        LocalDate futureDate = LocalDate.now().plusDays(5);
        EventManager event1 = new EventManager(
            "Java Tech Conference", 
            futureDate, 
            LocalTime.of(10, 30), 
            "Auditorium Hall A"
        );

        // Test with a past event and missing venue (null)
        LocalDate pastDate = LocalDate.now().minusDays(3);
        EventManager event2 = new EventManager(
            "Introductory Coding Workshop", 
            pastDate, 
            LocalTime.of(14, 0), 
            null 
        );

        System.out.println("=== EVENT 1 DETAILS ===");
        event1.displayEventDetails();
        event1.checkEventTimingStatus();
        event1.calculateDaysRemaining();

        System.out.println("\n=== EVENT 2 DETAILS ===");
        event2.displayEventDetails();
        event2.checkEventTimingStatus();
        event2.calculateDaysRemaining();
    }
}