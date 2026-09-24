package semifinal;

public abstract class Ride {

    private static int nextId = 1;
    private final int id;
    private final String passenger;
    private final double km;

    public Ride(String passenger, double km) {
    this.id = nextId++;
    this.passenger = passenger;
    this.km = km;
}

public int getId() { return id; }
public String getPassenger() { return passenger; }
public double getKm() { return km; }

public abstract double fare();
public abstract String vehicle();

public void printTicket() {
    System.out.println("--------------------------------");
    System.out.println("  Ticket #" + id + " | " + vehicle());
    System.out.println("  Passenger : " + passenger);
    System.out.printf("  Distance  : %.1f km%n", km);
    System.out.printf("  Fare      : PHP %.2f%n", fare());
}

public void printTicket(String note) {
    printTicket();
    System.out.println("  Note      : " + note);
}
}



package semifinal;

public class Tricycle extends Ride implements StudentDiscount {

    public Tricycle(String passenger, double km) {
    super(passenger, km);
}

@Override
public double fare() {
    if (getKm() <= 2) {
        return 20.00;
    }
    return 20.00 + (getKm() - 2) * 8.00;
}

@Override
public String vehicle() {
    return "Tricycle";
}

@Override
public double discountedFare() {
    return fare() * 0.80;
}
}

package semifinal;

public class Taxi extends Ride {

    public Taxi(String passenger, double km) {
    super(passenger, km);
}

@Override
public double fare() {
    return 45.00 + getKm() * 13.50;
}

@Override
public String vehicle() {
    return "Taxi";
}
}    

package semifinal;

import java.util.ArrayList;

public class RideManager {

    private final ArrayList<Ride> rides = new ArrayList<>();

    public void addRide(Ride ride) {
    rides.add(ride);
}

public Ride findRide(String passenger) {
    for (Ride r : rides) {
        if (r.getPassenger().equalsIgnoreCase(passenger)) {
            return r;
        }
    }
    return null;
}

public void showAllTickets() {
    for (Ride r : rides) {
        r.printTicket();
    }
}

public void showStudentDiscounts() {
    for (Ride r : rides) {
        if (r instanceof StudentDiscount) {
            System.out.println("  " + r.getPassenger() + " (" + r.vehicle() + ")");
            StudentDiscount s = (StudentDiscount) r;
            s.printDiscount();
        }
    }
}

public double totalSales() {
    double total = 0;
    for (Ride r : rides) {
        total += r.fare();
    }
    return total;
}

public int count() {
    return rides.size();
}
}

package semifinal;

public class Jeepney extends Ride implements StudentDiscount {

    public Jeepney(String passenger, double km) {
    super(passenger, km);
}

@Override
public double fare() {
    if (getKm() <= 4) {
        return 13.00;
    }
    return 13.00 + (getKm() - 4) * 1.80;
}

@Override
public String vehicle() {
    return "Jeepney";
}

@Override
public double discountedFare() {
    return fare() * 0.80;
}
}

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
