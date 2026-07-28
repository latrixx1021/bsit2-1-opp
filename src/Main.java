import java.util.Scanner;

public class ToolBox {

    static class Box {
        int value;
        Box(int value) {
            this.value = value;
        }
    }

    static String greet(String name) {
        return "Hello, " + name + "! Welcome to my Java Toolbox.";
    }

    static double area(double side) { // square
        return side * side;
    }

    static double area(double length, double width) { // rectangle
        return length * width;
    }

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("  (inside swap) a = " + a + ", b = " + b);
    }

    static void addToBox(Box box, int amount) {
        box.value = box.value + amount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== JAVA TOOLBOX =====");
            System.out.println("1 - Greet me");
            System.out.println("2 - Area (square or rectangle)");
            System.out.println("3 - Sum of numbers");
            System.out.println("4 - Swap demo (pass-by-value)");
            System.out.println("5 - Box demo (object mutation)");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.println(greet(name));
                    break;

                case 2:
                    System.out.print("Sides (1 = square, 2 = rectangle): ");
                    int type = Integer.parseInt(sc.nextLine().trim());
                    if (type == 1) {
                        System.out.print("Enter side length: ");
                        double side = Double.parseDouble(sc.nextLine().trim());
                        System.out.println("Area of square = " + area(side));
                    } else {
                        System.out.print("Enter length: ");
                        double length = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Enter width: ");
                        double width = Double.parseDouble(sc.nextLine().trim());
                        System.out.println("Area of rectangle = " + area(length, width));
                    }
                    break;

                case 3:
                    System.out.println("Sum of 4, 8, 15 = " + sum(4, 8, 15));
                    System.out.println("Sum of 2, 4, 6, 8, 10 = " + sum(2, 4, 6, 8, 10));
                    break;

                case 4:
                    int x = 5, y = 9;
                    System.out.println("Before swap: x = " + x + ", y = " + y);
                    swap(x, y);
                    System.out.println("After swap: x = " + x + ", y = " + y + " (unchanged - Java is pass-by-value)");
                    break;

                case 5:
                    Box box = new Box(10);
                    System.out.println("Before: box.value = " + box.value);
                    addToBox(box, 25);
                    System.out.println("After: box.value = " + box.value + " (changed - the object is shared)");
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
