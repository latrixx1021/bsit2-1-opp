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