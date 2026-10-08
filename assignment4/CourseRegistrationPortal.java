import java.util.*;

public class CourseRegistrationPortal {
    static class Course {
        private final String courseCode;
        private final String title;
        private final int capacity;
        
        private final HashSet<String> enrolledStudentIds; // Unique student enrollment
        private final ArrayList<String> prerequisites;   // Ordered list of prerequisite course codes
        private final LinkedList<String> waitlistQueue;   // FIFO waitlist queue

        public Course(String courseCode, String title, int capacity) {
            this.courseCode = courseCode;
            this.title = title;
            this.capacity = capacity;
            this.enrolledStudentIds = new HashSet<>();
            this.prerequisites = new ArrayList<>();
            this.waitlistQueue = new LinkedList<>();
        }

        public void addPrerequisite(String prereqCode) {
            prerequisites.add(prereqCode);
        }

        public String getCourseCode() { return courseCode; }
        public String getTitle() { return title; }
        public int getCapacity() { return capacity; }
        public HashSet<String> getEnrolledStudentIds() { return enrolledStudentIds; }
        public ArrayList<String> getPrerequisites() { return prerequisites; }
        public LinkedList<String> getWaitlistQueue() { return waitlistQueue; }

        public boolean isFull() {
            return enrolledStudentIds.size() >= capacity;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s | Enrolled: %d/%d | Waitlisted: %d", 
                    courseCode, title, enrolledStudentIds.size(), capacity, waitlistQueue.size());
        }
    }

    private final Map<String, Course> courseCatalog;                      // Fast lookup map
    private final Map<String, Set<String>> studentCompletedCourses;       // Student ID -> Completed Courses

    public CourseRegistrationPortal() {
        this.courseCatalog = new HashMap<>();
        this.studentCompletedCourses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courseCatalog.put(course.getCourseCode(), course);
    }

    public void recordCompletedCourses(String studentId, List<String> completedCodes) {
        studentCompletedCourses.computeIfAbsent(studentId, k -> new HashSet<>()).addAll(completedCodes);
    }

    public String registerStudent(String studentId, String courseCode) {
        Course course = courseCatalog.get(courseCode);
        
        if (course == null) {
            return "ERROR: Course " + courseCode + " does not exist.";
        }

        if (course.getEnrolledStudentIds().contains(studentId)) {
            return "INFO: Student " + studentId + " is already enrolled in " + courseCode + ".";
        }

        Set<String> completed = studentCompletedCourses.getOrDefault(studentId, Collections.emptySet());
        for (String prereq : course.getPrerequisites()) {
            if (!completed.contains(prereq)) {
                return "REJECTED: Student " + studentId + " has not completed prerequisite: " + prereq;
            }
        }

        if (!course.isFull()) {
            course.getEnrolledStudentIds().add(studentId);
            return "SUCCESS: Student " + studentId + " enrolled in " + courseCode + ".";
        } else {
            if (course.getWaitlistQueue().contains(studentId)) {
                return "INFO: Student " + studentId + " is already on the waitlist for " + courseCode + ".";
            }
            course.getWaitlistQueue().addLast(studentId); // Add to end of FIFO queue
            return "WAITLISTED: Course " + courseCode + " is full. Student " + studentId + " added to position #" + course.getWaitlistQueue().size();
        }
    }

 
    public String dropStudent(String studentId, String courseCode) {
        Course course = courseCatalog.get(courseCode);

        if (course == null) {
            return "ERROR: Course " + courseCode + " does not exist.";
        }

        if (course.getEnrolledStudentIds().remove(studentId)) {
            String logMsg = "SUCCESS: Student " + studentId + " dropped from " + courseCode + ".";
            
            if (!course.getWaitlistQueue().isEmpty()) {
                String promotedStudentId = course.getWaitlistQueue().removeFirst(); // FIFO poll
                course.getEnrolledStudentIds().add(promotedStudentId);
                logMsg += " PROMOTED: Student " + promotedStudentId + " moved from waitlist to enrolled status.";
            }
            return logMsg;
        }

        if (course.getWaitlistQueue().remove(studentId)) {
            return "SUCCESS: Student " + studentId + " removed from the waitlist for " + courseCode + ".";
        }

        return "ERROR: Student " + studentId + " was neither enrolled nor waitlisted for " + courseCode + ".";
    }

    public void printPortalSummary() {
        for (Course course : courseCatalog.values()) {
            System.out.println(course);
            System.out.println("  Enrolled: " + course.getEnrolledStudentIds());
            System.out.println("  Waitlist: " + course.getWaitlistQueue());
        }
    }

    public static void main(String[] args) {
        CourseRegistrationPortal portal = new CourseRegistrationPortal();

        Course cs101 = new Course("CS101", "Intro to Programming", 2);
        Course cs102 = new Course("CS102", "Data Structures", 2);
        cs102.addPrerequisite("CS101");

        portal.addCourse(cs101);
        portal.addCourse(cs102);

        portal.recordCompletedCourses("S101", List.of("CS101"));
        portal.recordCompletedCourses("S102", List.of("CS101"));
        portal.recordCompletedCourses("S103", List.of("CS101"));

        System.out.println(portal.registerStudent("S104", "CS102")); 

        System.out.println(portal.registerStudent("S101", "CS102"));
        System.out.println(portal.registerStudent("S102", "CS102")); 

        System.out.println(portal.registerStudent("S103", "CS102")); 

        portal.printPortalSummary();

        System.out.println(portal.dropStudent("S101", "CS102"));

        portal.printPortalSummary();
    }
}