package semifinal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        RideManager manager = new RideManager();

        manager.addRide(new Jeepney("Ana Reyes", 6.5));
        manager.addRide(new Tricycle("Jerome Tan", 3.0));
        manager.addRide(new Taxi("Liza Cruz", 5.0));

        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("========= LICEO RIDE =========");
            System.out.println("1. Book a ride");
            System.out.println("2. Show all tickets");
            System.out.println("3. Find a passenger");
            System.out.println("4. Show student discounts");
            System.out.println("5. Show total sales");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = readInt(in);

            if (choice == 1) {
                System.out.println("Vehicle: 1 = Jeepney, 2 = Tricycle, 3 = Taxi");
                System.out.print("Choose vehicle: ");
                int type = readInt(in);
                System.out.print("Passenger name: ");
                String name = in.nextLine().trim();
                System.out.print("Distance in km: ");
                double km = readDouble(in);

                Ride ride;
                if (type == 1) {
                    ride = new Jeepney(name, km);
                } else if (type == 2) {
                    ride = new Tricycle(name, km);
                } else if (type == 3) {
                    ride = new Taxi(name, km);
                } else {
                    System.out.println("Invalid vehicle.");
                    continue;
                }
                manager.addRide(ride);
                ride.printTicket("Booked! Ingat sa biyahe.");
            } else if (choice == 2) {
                System.out.println("ALL TICKETS (" + manager.count() + ")");
                manager.showAllTickets();
            } else if (choice == 3) {
                System.out.print("Passenger name: ");
                String name = in.nextLine().trim();
                Ride found = manager.findRide(name);
                if (found == null) {
                    System.out.println("No ride found for " + name + ".");
                } else {
                    found.printTicket();
                }
            } else if (choice == 4) {
                System.out.println("STUDENT DISCOUNTS (20% off)");
                manager.showStudentDiscounts();
            } else if (choice == 5) {
                System.out.printf("TOTAL SALES: PHP %.2f from %d rides%n",
                        manager.totalSales(), manager.count());
            } else if (choice == 0) {
                System.out.println("Salamat! Goodbye.");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static int readInt(Scanner in) {
        try {
            return Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double readDouble(Scanner in) {
        try {
            return Double.parseDouble(in.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Not a number - using 1.0 km.");
            return 1.0;
        }
    }
}