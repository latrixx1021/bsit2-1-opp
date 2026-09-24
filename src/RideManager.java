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