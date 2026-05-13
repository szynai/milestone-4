package oakwood;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ResidentDAO rDAO = new ResidentDAO();
        VisitorDAO vDAO = new VisitorDAO();
        EventDAO eDAO = new EventDAO();
        PaymentDAO pDAO = new PaymentDAO();

        while (true) {
            System.out.println("\n=== HOA SYSTEM ===");
            System.out.println("1. Register Resident");
            System.out.println("2. View Residents");
            System.out.println("3. Register Visitor");
            System.out.println("4. View Visitors");
            System.out.println("5. Register Event Reservation");
            System.out.println("6. View Events");
            System.out.println("7. Visitor Exit");
            System.out.println("8. Cancel Event");
            System.out.println("9. View Income Statement");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Contact: ");
                    String contact = sc.nextLine();

                    System.out.print("Address: ");
                    String address = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Resident r = new Resident(name, contact, address, email);
                    rDAO.addResident(r);
                    break;

                case 2:
                    rDAO.viewResidents();
                    break;

                case 3:
                    System.out.print("Visitor Name: ");
                    String vname = sc.nextLine();

                    System.out.print("Resident to Visit: ");
                    String res = sc.nextLine();

                    if (!rDAO.checkResident(res)) {
                        break;
                    }

                    System.out.print("Purpose: ");
                    String purpose = sc.nextLine();

                    System.out.print("Date: ");
                    String date = sc.nextLine();

                    System.out.print("Time In: ");
                    String time = sc.nextLine();

                    Visitor v = new Visitor(vname, res, purpose, date, time, "Inside");
                    vDAO.addVisitor(v);
                    break;

                case 4:
                    vDAO.viewVisitors();
                    break;

                case 5:
                    System.out.print("Organizer Name: ");
                    String ename = sc.nextLine();

                    System.out.print("Contact: ");
                    String econ = sc.nextLine();

                    System.out.print("Event Name: ");
                    String ev = sc.nextLine();

                    System.out.print("Event Date: ");
                    String edate = sc.nextLine();

                    Event e = new Event(ename, econ, ev, edate, "Active");
                    int eventId = eDAO.addEvent(e);

                    if (eventId != -1) {
                        System.out.println("\n=== Reservation Payment ===");

                        double reservationFee = 500.00;
                        double discountRate = 0.05;

                        System.out.printf("Reservation Fee: PHP %.2f%n", reservationFee);

                        System.out.print("Payment Method: ");
                        String paymentMethod = sc.nextLine();

                        System.out.print("Payment Received: PHP ");
                        double paymentReceived = sc.nextDouble();
                        sc.nextLine();

                        ReservationPayment payment = new ReservationPayment(
                                reservationFee,
                                discountRate,
                                paymentMethod,
                                paymentReceived
                        );

                        boolean paid = payment.processInvoice();

                        if (paid) {
                            pDAO.addPayment("Event Reservation", eventId, payment);
                        } else {
                            eDAO.cancelEvent(eventId);
                            System.out.println("Event reservation cancelled due to failed payment.");
                        }
                    }

                    break;

                case 6:
                    eDAO.viewEvents();
                    break;

                case 7:
                    System.out.print("Visitor ID to exit: ");
                    int vid = sc.nextInt();
                    sc.nextLine();

                    vDAO.visitorExit(vid);
                    break;

                case 8:
                    System.out.print("Event ID to cancel: ");
                    int eid = sc.nextInt();
                    sc.nextLine();

                    eDAO.cancelEvent(eid);
                    break;

                case 9:
                    pDAO.showIncomeStatement();
                    break;

                case 10:
                    System.out.println("Exiting HOA System...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
