import java.util.Scanner;

public class Main {

    static final int MAX_STUDENTS = 10;

    // Parallel arrays
    static int[] ids = new int[MAX_STUDENTS];
    static String[] names = new String[MAX_STUDENTS];
    static int[] ages = new int[MAX_STUDENTS];
    static String[] courses = new String[MAX_STUDENTS];
    static double[] grades = new double[MAX_STUDENTS];
    static boolean[] enrolled = new boolean[MAX_STUDENTS];

    static int studentCount = 0; // how many slots are currently filled

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    viewStatistics();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Student Information System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    static void printMenu() {
        System.out.println("===== STUDENT INFORMATION SYSTEM =====");
        System.out.println("[1] Add Student");
        System.out.println("[2] View All Students");
        System.out.println("[3] Search Student by ID");
        System.out.println("[4] View Statistics");
        System.out.println("[5] Exit");
    }

    // ---------- Option 1: Add Student ----------
    static void addStudent() {
        System.out.println();

        if (studentCount >= MAX_STUDENTS) {
            System.out.println(">> Cannot add student. The list is full (max " + MAX_STUDENTS + " students).\n");
            return;
        }

        int index = studentCount;

        System.out.print("Enter Student ID: ");
        int id = readInt();

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();

        int age;
        while (true) {
            System.out.print("Enter Age: ");
            age = readInt();
            if (age > 0) {
                break;
            }
            System.out.println("Invalid age. Age must be positive.");
        }

        System.out.print("Enter Course: ");
        String course = scanner.nextLine().trim();

        double grade;
        while (true) {
            System.out.print("Enter Grade: ");
            grade = readDouble();
            if (grade >= 0 && grade <= 100) {
                break;
            }
            System.out.println("Invalid grade. Grade must be between 0 and 100.");
        }

        boolean isEnrolled = readBoolean("Is Enrolled (true/false): ");

        // Store into parallel arrays
        ids[index] = id;
        names[index] = name;
        ages[index] = age;
        courses[index] = course;
        grades[index] = grade;
        enrolled[index] = isEnrolled;

        studentCount++;

        System.out.println(">> Student added successfully!\n");
    }

    // ---------- Option 2: View All Students ----------
    static void viewAllStudents() {
        System.out.println();
        System.out.println("--- STUDENT RECORDS ---");

        if (studentCount == 0) {
            System.out.println("No student records found.\n");
            return;
        }

        System.out.printf("%-5s %-15s %-5s %-10s %-7s %-15s%n",
                "ID", "NAME", "AGE", "COURSE", "GRADE", "STANDING");

        for (int i = 0; i < studentCount; i++) {
            String standing;
            if (grades[i] >= 90) {
                standing = "Dean's Lister";
            } else if (grades[i] >= 75) {
                standing = "Passed";
            } else {
                standing = "Failed";
            }

            System.out.printf("%-5d %-15s %-5d %-10s %-7.1f %-15s%n",
                    ids[i], names[i], ages[i], courses[i], grades[i], standing);
        }
        System.out.println();
    }

    // ---------- Option 3: Search Student by ID ----------
    static void searchStudentById() {
        System.out.println();
        System.out.print("Enter Student ID to search: ");
        int searchId = readInt();

        int foundIndex = -1;
        for (int i = 0; i < studentCount; i++) {
            if (ids[i] == searchId) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println(">> Student with ID " + searchId + " not found.\n");
        } else {
            String standing;
            if (grades[foundIndex] >= 90) {
                standing = "Dean's Lister";
            } else if (grades[foundIndex] >= 75) {
                standing = "Passed";
            } else {
                standing = "Failed";
            }

            System.out.println("--- STUDENT FOUND ---");
            System.out.println("ID          : " + ids[foundIndex]);
            System.out.println("Name        : " + names[foundIndex]);
            System.out.println("Age         : " + ages[foundIndex]);
            System.out.println("Course      : " + courses[foundIndex]);
            System.out.println("Grade       : " + grades[foundIndex]);
            System.out.println("Enrolled    : " + enrolled[foundIndex]);
            System.out.println("Standing    : " + standing);
            System.out.println();
        }
    }

    // ---------- Option 4: View Statistics ----------
    static void viewStatistics() {
        System.out.println();
        System.out.println("--- STATISTICS ---");

        if (studentCount == 0) {
            System.out.println("No student records found.\n");
            return;
        }

        double sum = 0;
        int topIndex = 0;

        for (int i = 0; i < studentCount; i++) {
            sum += grades[i];
            if (grades[i] > grades[topIndex]) {
                topIndex = i;
            }
        }

        double average = sum / studentCount;

        System.out.println("Total Students: " + studentCount);
        System.out.printf("Average Grade : %.2f%n", average);
        System.out.printf("Top Student   : %s (%.1f)%n", names[topIndex], grades[topIndex]);
        System.out.println();
    }

    // ---------- Input helper methods ----------
    static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter again: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter again: ");
            }
        }
    }

    static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }
    }
}