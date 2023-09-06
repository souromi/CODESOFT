import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private Scanner scanner;
    private static final String FILENAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(students);
            System.out.println("Student data saved to file.");
        } catch (IOException e) {
            System.out.println("Error while saving student data to file.");
        }
    }

    public void loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Student data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading student data from file.");
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Students to File");
            System.out.println("6. Load Students from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveStudentsToFile();
                    break;
                case 6:
                    loadStudentsFromFile();
                    break;
                case 7:
                    System.out.println("Exiting Student Management System.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(name, rollNumber, grade);
        addStudent(student);
        System.out.println("Student added: " + student);
    }

    private void removeStudent() {
        System.out.print("Enter roll number of student to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        removeStudent(rollNumber);
        System.out.println("Student with roll number " + rollNumber + " removed.");
    }

    private void searchStudent() {
        System.out.print("Enter roll number of student to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        Student student = searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }
}

public class student1 {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}
