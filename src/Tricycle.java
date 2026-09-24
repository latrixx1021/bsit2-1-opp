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