import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnrollmentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();

        int choice;

        do {
            System.out.println("LICEO ENROLLMENT SYSTEM");
            System.out.println("1. Register Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Students");
            System.out.println("5. View Courses");
            System.out.println("6. View Enrollments");
            System.out.println("7. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Student ID: ");
                    String id = sc.nextLine();

                    System.out.print("Student Name: ");
                    String name = sc.nextLine();

                    students.add(new Student(id, name));
                    enrollments.put(id, new ArrayList<>());
                    System.out.println("Student Registered");
                    break;

                case 2:
                    System.out.print("Course Code: ");
                    String code = sc.nextLine();

                    System.out.print("Course Name: ");
                    String cname = sc.nextLine();

                    courses.add(new Course(code, cname));
                    System.out.println("Course Added");
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    String sid = sc.nextLine();

                    System.out.print("Course Code: ");
                    String ccode = sc.nextLine();

                    if (enrollments.containsKey(sid)) {
                        enrollments.get(sid).add(ccode);
                        System.out.println("Enrollment Successful");
                    } else {
                        System.out.println("Student Not Found");
                    }
                    break;

                case 4:
                    for (Student s : students) {
                        System.out.println(s.getId() + " - " + s.getName());
                    }
                    break;

                case 5:
                    for (Course c : courses) {
                        System.out.println(c.getCode() + " - " + c.getName());
                    }
                    break;

                case 6:
                    for (Student s : students) {
                        System.out.println(s.getName());

                        ArrayList<String> list = enrollments.get(s.getId());

                        if (list.size() == 0) {
                            System.out.println("No Courses");
                        } else {
                            for (String x : list) {
                                System.out.println(x);
                            }
                        }

                        System.out.println();
                    }
                    break;

                case 7:
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 7);
    }
}
