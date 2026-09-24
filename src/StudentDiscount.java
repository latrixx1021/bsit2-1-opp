package semifinal;

public interface StudentDiscount {

    double discountedFare();

    default void printDiscount() {
        System.out.printf("    Student fare : PHP %.2f%n", discountedFare());
    }
}