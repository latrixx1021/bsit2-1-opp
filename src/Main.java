// Main.java
// This file is COMPLETE. You do not need to change anything here.

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static UserManager manager = new UserManager();
    private static int nextId = 1;

    public static void main(String[] args) {
        seedSampleUsers();

        boolean running = true;

        while (running) {
            showMenu();

            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                addUser();
            } else if (choice.equals("2")) {
                manager.listAll();
            } else if (choice.equals("3")) {
                searchUser();
            } else if (choice.equals("4")) {
                deleteUser();
            } else if (choice.equals("5")) {
                manager.exportAll();
            } else if (choice.equals("6")) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please enter 1 to 6.");
            }

            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("===== USER MANAGEMENT SYSTEM =====");
        System.out.println("1. Add user");
        System.out.println("2. List all users");
        System.out.println("3. Search user by ID");
        System.out.println("4. Delete user by ID");
        System.out.println("5. Export all users");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addUser() {
        System.out.println("Type of user: 1 = Admin 2 = Teacher 3 = Student");
        System.out.print("Choose type: ");

        String type = input.nextLine().trim();

        System.out.print("Name: ");
        String name = input.nextLine().trim();

        System.out.print("Email: ");
        String email = input.nextLine().trim();

        User user;

        if (type.equals("1")) {
            user = new Admin(nextId, name, email);

        } else if (type.equals("2")) {
            System.out.print("Department: ");
            String dept = input.nextLine().trim();

            user = new Teacher(nextId, name, email, dept);

        } else if (type.equals("3")) {
            System.out.print("Course: ");
            String course = input.nextLine().trim();

            user = new Student(nextId, name, email, course);

        } else {
            System.out.println("Unknown type. User was not added.");
            return;
        }

        manager.add(user);
        nextId++;
    }

    private static void searchUser() {
        System.out.print("Enter ID to search: ");

        int id = readInt();

        User found = manager.findById(id);

        if (found == null) {
            System.out.println("No user found with ID " + id + ".");
        } else {
            System.out.println("Found:");
            found.display();
        }
    }

    private static void deleteUser() {
        System.out.print("Enter ID to delete: ");

        int id = readInt();

        if (manager.deleteById(id)) {
            System.out.println("User " + id + " was deleted.");
        } else {
            System.out.println("No user found with ID " + id + ".");
        }
    }

    private static int readInt() {
        while (true) {
            String line = input.nextLine().trim();

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("That is not a number. Try again: ");
            }
        }
    }

    private static void seedSampleUsers() {
        manager.add(new Admin(nextId, "Razz", "razz@liceo.edu.ph"));
        nextId++;

        manager.add(new Teacher(nextId, "Maria", "maria@liceo.edu.ph", "CIT"));
        nextId++;

        manager.add(new Student(nextId, "Ana", "ana@liceo.edu.ph", "BSIT"));
        nextId++;

        System.out.println();
    }
}
public abstract class User implements Exportable {

    private final int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public abstract String role();

    public abstract String permissions();

    public void display() {
        System.out.printf(
                "[%d] %-12s %-24s %-8s %s%n",
                id, name, email, role(), permissions()
        );
    }

    @Override
    public String toCsv() {
        return id + "," + name + "," + email + "," + role();
    }
}
public class Admin extends User {

    public Admin(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String role() {
        return "ADMIN";
    }

    @Override
    public String permissions() {
        return "create, read, update, delete";
    }
}
public class Teacher extends User {

    private String department;

    public Teacher(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String role() {
        return "TEACHER";
    }

    @Override
    public String permissions() {
        return "read, update grades";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + department;
    }
}
public class Student extends User {

    private String course;

    public Student(int id, String name, String email, String course) {
        super(id, name, email);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String role() {
        return "STUDENT";
    }

    @Override
    public String permissions() {
        return "read only";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + course;
    }
}
import java.util.ArrayList;

public class UserManager {

    private ArrayList<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
        System.out.println("Added: " + user.getName() + " (" + user.role() + ")");
    }

    public void listAll() {
        if (users.isEmpty()) {
            System.out.println("No users yet.");
            return;
        }

        String header =
                "ID NAME EMAIL ROLE PERMISSIONS";
        System.out.println(header);
        System.out.println("-".repeat(69));

        for (User u : users) {
            u.display();
        }

        System.out.println("Total users: " + users.size());
    }

    public User findById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public boolean deleteById(int id) {
        User found = findById(id);

        if (found == null) {
            return false;
        }

        users.remove(found);
        return true;
    }

    public void exportAll() {
        if (users.isEmpty()) {
            System.out.println("Nothing to export.");
            return;
        }

        System.out.println("--- CSV EXPORT ---");

        for (User u : users) {
            u.printExport();
        }

        System.out.println("--- END OF EXPORT ---");
    }

    public int count() {
        return users.size();
    }
}
public interface Exportable {

    String toCsv();

    default void printExport() {
        System.out.println(toCsv());
    }
}
