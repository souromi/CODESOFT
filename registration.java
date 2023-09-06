import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int id;
    private String name;
    private List<String> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<Integer, Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            int availableSlots = course.getCapacity() - countRegisteredStudents(course.getCode());
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + availableSlots + " slots available)");
        }
    }

    public void registerStudentForCourse(int studentId, String courseCode) {
        if (students.containsKey(studentId) && courses.containsKey(courseCode)) {
            Course course = courses.get(courseCode);
            Student student = students.get(studentId);

            if (countRegisteredStudents(courseCode) < course.getCapacity()) {
                student.registerCourse(courseCode);
                System.out.println("Student " + student.getName() + " registered for course " + course.getTitle());
            } else {
                System.out.println("Course is already full. Cannot register.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public void dropCourseForStudent(int studentId, String courseCode) {
        if (students.containsKey(studentId) && courses.containsKey(courseCode)) {
            Student student = students.get(studentId);

            if (student.getRegisteredCourses().contains(courseCode)) {
                student.dropCourse(courseCode);
                System.out.println("Student " + student.getName() + " dropped course " + courseCode);
            } else {
                System.out.println("Student is not registered for this course.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private int countRegisteredStudents(String courseCode) {
        int count = 0;
        for (Student student : students.values()) {
            if (student.getRegisteredCourses().contains(courseCode)) {
                count++;
            }
        }
        return count;
    }
}

public class registration {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner sc= new Scanner(System.in);

        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 50, "Mon/Wed 10:00 AM - 11:30 AM");
        Course course2 = new Course("MAT202", "Linear Algebra", "Vectors and matrices", 40, "Tue/Thu 1:00 PM - 2:30 PM");

        Student student1 = new Student(1001, "John");
        Student student2 = new Student(1002, "Jane");

        system.addCourse(course1);
        system.addCourse(course2);
        system.addStudent(student1);
        system.addStudent(student2);

        boolean running = true;
        while (running) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Course for Student");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int studentId =sc.nextInt();
                    System.out.print("Enter course code: ");
                    String courseCode = sc.next();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = sc.nextInt();
                    System.out.print("Enter course code: ");
                    courseCode = sc.next();
                    system.dropCourseForStudent(studentId, courseCode);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
