```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        // Sample payments
        gateway.add(new GCashPayment(
                1001, "Ana", 1500.00, "0917-555-0134"));

        gateway.add(new MayaPayment(
                1002, "Jerome", 899.50, "jerome@liceo.edu.ph"));

        gateway.add(new CashPayment(
                1003, "Liza", 250.00));

        int choice;

        do {
            System.out.println();
            System.out.println("======================================");
            System.out.println("              LICEO PAY");
            System.out.println("======================================");
            System.out.println("1. Make Payment");
            System.out.println("2. Show All Receipts");
            System.out.println("3. Find Payment");
            System.out.println("4. Process All Payments");
            System.out.println("5. Refund All Refundable Payments");
            System.out.println("6. Show Service Fees");
            System.out.println("0. Exit");
            System.out.println("======================================");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.println();
                    System.out.println("--- MAKE PAYMENT ---");

                    System.out.print("Enter payment ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter payer name: ");
                    String name = input.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = input.nextDouble();
                    input.nextLine();

                    System.out.println();
                    System.out.println("Choose payment method:");
                    System.out.println("1. GCash");
                    System.out.println("2. Maya");
                    System.out.println("3. Cash");
                    System.out.print("Choice: ");

                    int method = input.nextInt();
                    input.nextLine();

                    Payment newPayment = null;

                    if (method == 1) {

                        System.out.print("Enter mobile number: ");
                        String mobile = input.nextLine();

                        newPayment = new GCashPayment(
                                id, name, amount, mobile);

                    } else if (method == 2) {

                        System.out.print("Enter email: ");
                        String email = input.nextLine();

                        newPayment = new MayaPayment(
                                id, name, amount, email);

                    } else if (method == 3) {

                        newPayment = new CashPayment(
                                id, name, amount);

                    } else {
                        System.out.println("Invalid payment method.");
                        break;
                    }

                    gateway.add(newPayment);

                    System.out.println();
                    System.out.println("Payment added successfully.");
                    newPayment.printReceipt();
                    newPayment.printThankYou();

                    break;

                case 2:
                    System.out.println();
                    System.out.println("--- ALL RECEIPTS ---");

                    if (gateway.count() == 0) {
                        System.out.println("No payments have been made yet.");
                    } else {
                        gateway.processAll();
                    }

                    break;

                case 3:
                    System.out.println();
                    System.out.println("--- FIND PAYMENT ---");

                    System.out.print("Enter payment ID: ");
                    int searchId = input.nextInt();

                    Payment found = gateway.findById(searchId);

                    if (found != null) {
                        System.out.println();
                        System.out.println("Payment found:");
                        found.printReceipt();
                    } else {
                        System.out.println("Payment with ID "
                                + searchId + " was not found.");
                    }

                    break;

                case 4:
                    System.out.println();
                    System.out.println("--- PROCESS ALL PAYMENTS ---");
                    gateway.processAll();

                    break;

                case 5:
                    System.out.println();
                    System.out.println(
                            "--- REFUNDING EVERY PAYMENT THAT CAN BE REFUNDED ---");

                    gateway.refundAll();

                    break;

                case 6:
                    System.out.println();
                    System.out.println(
                            "--- SERVICE FEES (THE TWO SERVICEFEE METHODS) ---");

                    gateway.showServiceFees();

                    break;

                case 0:
                    System.out.println();
                    System.out.println("Thank you for using LICEO PAY!");

                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        input.close();
    }
}
```

### What each screenshot should show

**`01-menu.png`**

Run the program and capture the screen when this appears:

    ```text
======================================
              LICEO PAY
======================================
1. Make Payment
2. Show All Receipts
3. Find Payment
4. Process All Payments
5. Refund All Refundable Payments
6. Show Service Fees
0. Exit
======================================
Choose an option:
```

    **`02-make-payment.png`**

    Choose `1`, then enter something like:

    ```text
Enter payment ID: 1004
Enter payer name: Juan
Enter amount: 750

Choose payment method:
1. GCash
2. Maya
3. Cash
Choice: 1
Enter mobile number: 09171234567

Payment added successfully.
[1004] GCASH  Juan       PHP     750.00
 GCash: PHP 750.00 sent from 09171234567.
 Thank you for your payment.
 An SMS receipt was sent to 09171234567
```

    **`03-all-receipts.png`**

    Choose `2`. Since the program starts with three sample payments and you added one, you should have **4 receipts**.

GCash should have the extra SMS line, while Maya and Cash should not.

**`04-find-payment.png`**

Choose `3` and enter an existing ID such as:

    ```text
Enter payment ID: 1002

Payment found:
[1002] MAYA   Jerome     PHP     899.50
 Maya: PHP 899.50 charged to the wallet of jerome@liceo.edu.ph.
```

    **`05-refund.png`**

    Choose `5`:

```text
--- REFUNDING EVERY PAYMENT THAT CAN BE REFUNDED ---
 GCash refund of PHP 1500.00 returned to 0917-555-0134.
 Maya refund of PHP 899.50 emailed to jerome@liceo.edu.ph.
```

The **Cash payment does not appear** because `CashPayment` does not implement `Refundable`, exactly as required by the assignment.

**`06-service-fees.png`**

Choose `6`:

```text
--- SERVICE FEES (THE TWO SERVICEFEE METHODS) ---
[1001] GCASH  standard 2%: PHP    30.00 student 1%: PHP    15.00
[1002] MAYA   standard 2%: PHP    17.99 student 1%: PHP     9.00
[1003] CASH   standard 2%: PHP     5.00 student 1%: PHP     2.50
[1004] GCASH  standard 2%: PHP    15.00 student 1%: PHP     7.50
```

The assignment specifically requires the service-fee display to call both `serviceFee()` and `serviceFee(0.01)`.

**One important thing:** your teacher's exact starter `Main.java` may have a prescribed menu/output format. If you have that `Main.java` file, upload it and I can fill in **only the missing parts** so your screenshots match the required output exactly.
