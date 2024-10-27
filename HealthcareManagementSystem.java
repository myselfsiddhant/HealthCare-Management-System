import java.util.Scanner;

public class HealthcareManagementSystem {
    private static String adminId = "admin";
    private static String adminPassword = "admin123";
    private static String patientId = "patient";
    private static String patientPassword = "patient123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (id.equals(adminId) && password.equals(adminPassword)) {
            adminDashboard(scanner);
        } else if (id.equals(patientId) && password.equals(patientPassword)) {
            patientDashboard(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }

        scanner.close();
    }

    private static void adminDashboard(Scanner scanner) {
        boolean continueSession = true;

        while (continueSession) {
            System.out.println("\nWelcome to Admin Dashboard!");
            System.out.println("1. Manage Users");
            System.out.println("2. View Reports");
            System.out.println("3. Exit Dashboard");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageUsers();
                    break;
                case 2:
                    viewReports();
                    break;
                case 3:
                    continueSession = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageUsers() {
        System.out.println("Managing users...");
    }

    private static void viewReports() {
        System.out.println("Viewing reports...");
    }

    private static void patientDashboard(Scanner scanner) {
        boolean continueSession = true;

        while (continueSession) {
            System.out.println("\nWelcome to Patient Dashboard!");
            System.out.println("1. View Medical Reports");
            System.out.println("2. Book Appointment");
            System.out.println("3. Exit Dashboard");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMedicalReports();
                    break;
                case 2:
                    bookAppointment(scanner);
                    break;
                case 3:
                    continueSession = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewMedicalReports() {
        System.out.println("Displaying medical reports...");
    }

    private static void bookAppointment(Scanner scanner) {
        System.out.println("Checking appointment availability...");

        boolean slotsAvailable = true; // This would typically check a database

        if (slotsAvailable) {
            System.out.println("Appointment booked! Confirmation sent.");
        } else {
            System.out.println("No slots available. Choose from available dates:");
        }
    }
}
