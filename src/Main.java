import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Part A: Grade Scale (Array)
    static double[] cutoffs = {90, 80, 70, 60, 0};
    static char[] letters = {'A', 'B', 'C', 'D', 'F'};

    public static char letterFor(double grade) {
        for (int i = 0; i < cutoffs.length; i++) {
            if (grade >= cutoffs[i]) {
                return letters[i];
            }
        }
        return 'F';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> roster = new ArrayList<>();

        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== Grade Tracker ===");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Class average");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.next();

                System.out.print("Grade: ");
                double grade = sc.nextDouble();

                roster.add(new Student(name, grade));
                System.out.println("Student added.");

            } else if (choice == 2) {
                if (roster.isEmpty()) {
                    System.out.println("No students yet.");
                } else {
                    System.out.println("\nClass List:");
                    for (Student s : roster) {
                        System.out.println(s.name + " - " + s.grade + " (" + letterFor(s.grade) + ")");
                    }
                }

            } else if (choice == 3) {
                if (roster.isEmpty()) {
                    System.out.println("No students yet.");
                } else {
                    double total = 0;

                    for (Student s : roster) {
                        total += s.grade;
                    }

                    double average = total / roster.size();
                    System.out.printf("Class average: %.2f (%c)%n", average, letterFor(average));
                }

            } else if (choice == 4) {
                System.out.println("Exiting program...");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}

// Part B: Student class
class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}
