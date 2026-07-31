mport java.util.Scanner;

public class Main {

    static double balance = 1000.00;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" WELCOME TO THE MINI ATM");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    checkBalance();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                    break;
                default:
                    System.out.println("\n[!] Please choose a number from 1 to 4.\n");
            }
        }
    }

    static void printMenu() {
        System.out.println("Current options:");
        System.out.println(" [1] Deposit");
        System.out.println(" [2] Withdraw");
        System.out.println(" [3] Check balance");
        System.out.println(" [4] Exit");
        System.out.print("Enter your choice: ");
    }

    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        String line = input.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);

            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be greater than zero.");
            }

            balance += amount;
            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);

        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --");
        }
    }

    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        String line = input.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);

            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
            }

            if (amount > balance) {
                double shortfall = amount - balance;
                throw new InsufficientFundsException(
                        String.format("Insufficient funds. You are short by PHP %.2f.", shortfall),
                        shortfall);
            }

            balance -= amount;
            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);

        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --");
        }
    }

    static void checkBalance() {
        System.out.printf("%nYour current balance is: PHP %.2f%n%n", balance);
    }
}

class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
