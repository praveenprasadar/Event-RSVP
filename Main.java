import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServiceRSVP service = new ServiceRSVP();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== RSVP Management System ===");
            System.out.println("1. Add RSVP");
            System.out.println("2. View All RSVPs (sorted by ID)");
            System.out.println("3. View RSVP by ID");
            System.out.println("4. Update RSVP");
            System.out.println("5. Delete RSVP");
            System.out.println("6. View All RSVPs (sorted by Guest Name)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter RSVP ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Guest Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();

                    try {
                        RSVP r = new RSVP(id, name, email, status);
                        service.addRSVP(r);
                    } catch (ServiceRSVP.InvalidEmailException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    List<RSVP> all = service.getAllRSVPs();
                    System.out.println("\nAll RSVPs (sorted by ID):");
                    for (RSVP r : all) {
                        System.out.println(r);
                    }
                }

                case 3 -> {
                    System.out.print("Enter RSVP ID to view: ");
                    int viewId = sc.nextInt();
                    try {
                        RSVP r = service.getRSVP(viewId);
                        System.out.println(r);
                    } catch (ServiceRSVP.RSVPNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.print("Enter RSVP ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Guest Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();

                    try {
                        RSVP updated = new RSVP(updateId, newName, newEmail, newStatus);
                        service.updateRSVP(updated);
                    } catch (ServiceRSVP.InvalidEmailException | ServiceRSVP.RSVPNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }


                case 5 -> {
                    System.out.print("Enter RSVP ID to delete: ");
                    int deleteId = sc.nextInt();
                    try {
                        service.deleteRSVP(deleteId);
                    } catch (ServiceRSVP.RSVPNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 6 -> {
                    List<RSVP> sortedByName = service.getAllRSVPs();
                    Collections.sort(sortedByName, new GuestNameComparator());
                    System.out.println("\nAll RSVPs sorted by Guest Name");
                    for (RSVP r : sortedByName) {
                        System.out.println(r);
                    }
                }

                case 7 -> System.out.println("Exiting... Goodbye!");

                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }
    
}