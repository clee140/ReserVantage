import java.io.*;
import java.util.*;

/**
 * The main class includes all user input and output processing for sellers and customers.
 *
 * @author Audrey Lupton, Sruthi Lingam
 * @version November 12, 2023
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userTypeOption;
        System.out.println("Welcome to the Hotel Manager!");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1 - Create an account");
            System.out.println("2 - Login into an account");
            System.out.println("3 - Quit");

            int choice;
            while (true) { //Makes sure user inputs an int
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }

            if (choice == 1) { //Create an account
                try {
                    System.out.println("Please select an option:\n1. Seller\n2. Customer");

                    while (true) { //Makes sure user inputs an int
                        if (scanner.hasNextInt()) {
                            userTypeOption = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                            scanner.next();
                        }
                    }

                    String userType = "";
                    String name;
                    boolean validUserType = false; //If the user selected a valid option
                    do {
                        switch (userTypeOption) {
                            case 1 -> { //User is a seller
                                userType = "Seller";
                                validUserType = true;
                            }
                            case 2 -> { //User is a customer
                                userType = "Customer";
                                validUserType = true;
                            }
                            default -> //User selected invalid option
                                    System.out.println("Invalid choice. Please try again");
                        }
                    } while (!validUserType);

                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("userDatabase.txt",
                            true)));

                    String username;
                    boolean validUsername = false; //If user has a valid email
                    do {
                        System.out.println("Please enter your email: ");
                        username = scanner.nextLine();

                        User user = new User();
                        if (user.usernameExists("userDatabase.txt", username)) { //Username already exists
                            System.out.println("Email already exists. Please choose a different email.");
                        } else { //Email doesn't exist, can continue
                            validUsername = true;
                        }
                    } while (!validUsername);

                    boolean validPass = false; //If user creates a matching password
                    do {
                        System.out.println("Please enter your password: ");
                        String password = scanner.nextLine();
                        System.out.println("Please repeat your password: "); //To avoid typos
                        String repeat = scanner.nextLine();

                        if (password.equals(repeat)) { //Passwords match, create new Login
                            System.out.println("Please enter your first and last name: ");
                            name = scanner.nextLine();

                            pw.println(userType + "," + name + "," + username + "," + password);

                            //Creates userfile
                            if (userType.equals("Seller")) { //Creates a seller file
                                Seller seller = new Seller(username, password, username + ".txt");
                                seller.createHotel();
                            } else { //Creates a customer file
                                Customer customer = new Customer(username, username + ".txt");
                                customer.createCustomer();
                            }

                            validPass = true;
                        } else { //Passwords do not match, have user try again
                            System.out.println("Your passwords do not match. Please try again.");
                        }
                    } while (!validPass);
                    pw.flush();
                    pw.close();

                    System.out.println("Your account has been created.\n");
                    //Program will now loop around to the top and ask user to choose an option

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 2) { //User has an account, can continue to log in
                boolean validLogin = false; //If the login info is correct
                do {
                    System.out.println("Choose one of the following users: ");
                    System.out.println("1 - Seller");
                    System.out.println("2 - Customer");

                    while (true) { //Makes sure user inputs an int
                        if (scanner.hasNextInt()) {
                            userTypeOption = scanner.nextInt();
                            if (userTypeOption == 1 || userTypeOption == 2) {
                                scanner.nextLine();
                                break;
                            } else {
                                System.out.println("Invalid input. Choose either option 1 or 2.");
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("Invalid input. Please try again.");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Please enter your email: ");
                    String username = scanner.nextLine();

                    System.out.println("Please enter your password: ");
                    String password = scanner.nextLine();

                    User user = new User();
                    if (user.validator("userDatabase.txt", username, password) && (userTypeOption == 1)) {
                        //Seller log in
                        System.out.println("What is the name of your store?");
                        String storeName = scanner.nextLine();

                        Seller seller = new Seller(username, storeName, username + ".txt");

                        boolean optionLoop = true; //Repeats option menu until user logs out
                        do {
                            System.out.println("Choose an option: ");
                            System.out.println("1. View current calendars");
                            System.out.println("2. Create new calendar");
                            System.out.println("3. Edit calendar");
                            System.out.println("4. Delete calendar");
                            System.out.println("5. Approve/decline appointment requests");
                            System.out.println("6. View currently approved appointments");
                            System.out.println("7. View statistics");
                            System.out.println("8. Logout");

                            int sellerOptions;
                            while (true) { //Makes sure user inputs an int
                                if (scanner.hasNextInt()) {
                                    sellerOptions = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.next();
                                }
                            }

                            switch (sellerOptions) {
                                case 1 -> //View current calendars
                                        System.out.println(seller.printCalendar());
                                case 2 -> { //Create new calendar
                                    boolean validNum = false; //If user entered valid option
                                    do {
                                        System.out.println("Do you want to upload a calendar/appointment list with a file?");
                                        System.out.println("1. Yes\n2. No");

                                        int ifFile;
                                        while (true) { //Makes sure user inputs an int
                                            if (scanner.hasNextInt()) {
                                                ifFile = scanner.nextInt();
                                                scanner.nextLine();
                                                break;
                                            } else {
                                                System.out.println("Invalid input. Please try again.");
                                                scanner.next();
                                            }
                                        }

                                        switch (ifFile) {
                                            case 1 -> { //Upload a file from desktop
                                                validNum = true;
                                                System.out.println("Please ensure the file is in this format: ");
                                                System.out.println("[Store Name],[Calendar Name]," +
                                                        "[Calendar description],[Appointment Title],[Max Attendees]," +
                                                        "[Approved Bookings],[Start Time],[End Time]\nMultiple " +
                                                        "appointments can be on the same line separated by commas." +
                                                        "\nMax Attendees can only be an integer value 0 or 1.\n");
                                                System.out.println("Please enter the filename: ");
                                                String inFile = scanner.nextLine();
                                                String desktopPath = System.getProperty("user.home") + "/Desktop/";
                                                String importFilePath = desktopPath + inFile;
                                                seller.createCalendarWithFile(importFilePath);
                                            }
                                            case 2 -> { //Manually create new calendar
                                                validNum = true;
                                                System.out.println("Please enter the calendar name: ");
                                                String calendarName = scanner.nextLine();
                                                System.out.println("Please enter a description of the calendar: ");
                                                String description = scanner.nextLine();
                                                ArrayList<Appointment> apptList = new ArrayList<>();
                                                boolean apptLoop = true;
                                                do { //Loop to create multiple appointments
                                                    System.out.println("Please enter the appointment title: ");
                                                    String apptTitle = scanner.nextLine();
                                                    System.out.println("Please enter the maximum amount of attendees: ");

                                                    int maxAttendee;
                                                    while (true) { //Makes sure user inputs an int
                                                        if (scanner.hasNextInt()) {
                                                            maxAttendee = scanner.nextInt();
                                                            scanner.nextLine();
                                                            break;
                                                        } else {
                                                            System.out.println("Invalid input. Please try again.");
                                                            scanner.next();
                                                        }
                                                    }

                                                    System.out.println("Please enter the number of approved bookings " +
                                                            "(value can only be a 0 or 1): ");

                                                    int approvedBookings;
                                                    while (true) { //Makes sure user inputs an int
                                                        if (scanner.hasNextInt()) {
                                                            approvedBookings = scanner.nextInt();
                                                            if (approvedBookings == 0 || approvedBookings == 1) {
                                                                scanner.nextLine();
                                                                break;
                                                            } else {
                                                                System.out.println("Invalid input. Please try again.");
                                                                scanner.nextLine();
                                                            }
                                                        } else {
                                                            System.out.println("Invalid input. Please try again.");
                                                            scanner.nextLine();
                                                        }
                                                    }

                                                    System.out.println("Please enter the start time of the appointment " +
                                                            "(in the form hh:mm): ");
                                                    String startTime = scanner.nextLine();
                                                    System.out.println("Please enter the end time of the appointment " +
                                                            "(in the form hh:mm): ");
                                                    String endTime = scanner.nextLine();

                                                    Appointment newAppt = new Appointment(apptTitle, maxAttendee,
                                                            approvedBookings, startTime, endTime);
                                                    apptList.add(newAppt);

                                                    boolean validCreateAnotherAppt = false; //If user entered a valid
                                                    // response for creating another appt
                                                    do {
                                                        System.out.println("Would you like to create another appointment? (Y or N)");
                                                        String anotherAppt = scanner.nextLine();
                                                        if (anotherAppt.equalsIgnoreCase("Y")) { //Create another appointment
                                                            //Program will loop back around and have them create another appt
                                                            validCreateAnotherAppt = true;
                                                        } else if (anotherAppt.equalsIgnoreCase("N")) { //Exit appointment loop
                                                            validCreateAnotherAppt = true;
                                                            apptLoop = false;
                                                        } else { //Invalid response
                                                            System.out.println("Please enter a valid response.");
                                                        }
                                                    } while (!validCreateAnotherAppt);
                                                } while (apptLoop);
                                                seller.createCalendar(calendarName, description, apptList);
                                            }
                                            default -> //Invalid response
                                                    System.out.println("Please enter a valid number.");
                                        }
                                    } while (!validNum);
                                }
                                case 3 -> { //Edit calendar
                                    System.out.println(seller.printCalendar()); //Prints all calendars
                                    System.out.println();
                                    System.out.println("Please enter the name of the calendar you would like to edit: ");
                                    String calendarName = scanner.nextLine();
                                    System.out.println("Please enter the title of the appointment you would like to edit: ");
                                    String oldApptTitle = scanner.nextLine();
                                    System.out.println("Please enter the new appointment title: ");
                                    String apptTitle = scanner.nextLine();
                                    System.out.println("Please enter the new maximum amount of attendees: ");

                                    int maxAttendee;
                                    while (true) { //Makes sure user inputs an int
                                        if (scanner.hasNextInt()) {
                                            maxAttendee = scanner.nextInt();
                                            scanner.nextLine();
                                            break;
                                        } else {
                                            System.out.println("Invalid input. Please try again.");
                                            scanner.next();
                                        }
                                    }
                                    scanner.nextLine();
                                    System.out.println("Please enter the new number of approved bookings (value can only be a 0 or 1): ");

                                    int approvedBookings;
                                    while (true) { //Makes sure user inputs an int
                                        if (scanner.hasNextInt()) {
                                            approvedBookings = scanner.nextInt();
                                            if (approvedBookings == 0 || approvedBookings == 1) {
                                                scanner.nextLine();
                                                break;
                                            } else {
                                                System.out.println("Invalid input. Please try again.");
                                                scanner.nextLine();
                                            }
                                        } else {
                                            System.out.println("Invalid input. Please try again.");
                                            scanner.nextLine();
                                        }
                                    }

                                    System.out.println("Please enter the new start time of the appointment (in the form hh:mm): ");
                                    String startTime = scanner.nextLine();
                                    System.out.println("Please enter the new end time of the appointment (in the form hh:mm): ");
                                    String endTime = scanner.nextLine();
                                    Appointment editedAppt = new Appointment(apptTitle, maxAttendee, approvedBookings, startTime, endTime);
                                    seller.editCalendar(calendarName, oldApptTitle, calendarName + "-" + editedAppt);
                                }
                                case 4 -> { //Delete calendar
                                    System.out.println(seller.printCalendar()); //Prints all calendars
                                    System.out.println();
                                    System.out.println("Please enter the name of the calendar you would like to delete: ");
                                    String deletedCalendarName = scanner.nextLine();
                                    seller.deleteCalendar(deletedCalendarName);
                                }
                                case 5 -> { //Approve/decline appointment requests
                                    if (seller.getCustomerRequest().equals("No appointment requests")) {
                                        System.out.println(seller.getCustomerRequest());
                                    } else {
                                        System.out.println(seller.getCustomerRequest());
                                        System.out.println("Please select an appointment to approve/decline: ");
                                        System.out.println("Enter the appointment exactly as it appears in the above appointment list." +
                                                "\n[Calendar name]-[Appointment Title],[Max Attendees],[Approved Bookings],[Start Time]," +
                                                "[End Time]-[Customer username]");
                                        String requestAppt = scanner.nextLine();
                                        System.out.println("Customer username: ");
                                        String requestUsername = scanner.nextLine();
                                        seller.handleCustomerRequests(requestAppt, requestUsername);
                                    }
                                }
                                case 6 -> //View currently approved appointments
                                        System.out.println(seller.viewApprovedAppointments());
                                case 7 -> { //View statistics
                                    boolean validSort = false; //If user entered a valid response to the prompt
                                    do {
                                        System.out.println("Would you like to sort the appointments by most popular windows? (Yes or No)");
                                        String sort = scanner.nextLine();
                                        if (sort.equalsIgnoreCase("Yes") || sort.equalsIgnoreCase("No")) {
                                            validSort = true;
                                            System.out.println(seller.viewStatistics(sort));
                                        } else {
                                            System.out.println("Please enter a valid response.");
                                        }
                                    } while (!validSort);
                                }
                                case 8 -> //Logout
                                        optionLoop = false;
                                default -> //Invalid response
                                        System.out.println("Please choose a valid option.");
                            }
                            validLogin = true;
                        } while (optionLoop);

                        System.out.println("You have successfully logged out.");

                    } else if (user.validator("userDatabase.txt", username, password) && (userTypeOption == 2)) {
                        Customer customer = new Customer(username, username + ".txt");
                        boolean repeatCustomerChoices;
                        try {
                            do {
                                System.out.println("Choose a customer option: ");
                                System.out.println("1 - Make an appointment request");
                                System.out.println("2 - Cancel an appointment request");
                                System.out.println("3 - View store calendars");
                                System.out.println("4 - View currently approved appointments");
                                System.out.println("5 - View pending appointments");
                                System.out.println("6 - View store statistics");
                                System.out.println("7 - Export a file of your approved appointments");
                                System.out.println("8 - Exit and log out");

                                int customerChoice;
                                while (true) {
                                    if (scanner.hasNextInt()) {
                                        customerChoice = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please try again.");
                                        scanner.next();
                                    }
                                }

                                switch (customerChoice) {
                                    case 1: //make an appointment request
                                        System.out.println(customer.viewCalendars());
                                        System.out.println("Enter the appointment you would like to request exactly as " +
                                                "it appears in the above appointment list:\n[Calendar name]-[Appointment Title]," +
                                                "[Max Attendees],[Approved Bookings],[Start Time],[End Time]");
                                        String appointmentRequest = scanner.nextLine();
                                        customer.makeAppointment(appointmentRequest);
                                        repeatCustomerChoices = true;
                                        break;
                                    case 2: //cancel an appointment request
                                        boolean repeatCancel;
                                        do {
                                            System.out.println("Choose an appointment category to delete from: ");
                                            System.out.println("1 - Appointments Awaiting Approval");
                                            System.out.println("2 - Appointments Approved");
                                            int cancelInput;
                                            //check if valid input, loop until achieved
                                            while (true) {
                                                if (scanner.hasNextInt()) {
                                                    cancelInput = scanner.nextInt();
                                                    scanner.nextLine();
                                                    break;
                                                } else {
                                                    System.out.println("Invalid input. Please try again.");
                                                    scanner.next();
                                                }
                                            }
                                            String appointmentName = "";
                                            if (cancelInput == 1) {
                                                System.out.println(customer.viewAppointmentsWaitingApproval());
                                                System.out.println("Enter the appointment you would like to delete exactly as " +
                                                        "it appears in the above appointment list:\n[Calendar name]-[Appointment Title]," +
                                                        "[Max Attendees],[Approved Bookings],[Start Time],[End Time]");
                                                appointmentName = scanner.nextLine();
                                                repeatCancel = true;
                                            } else if (cancelInput == 2) {
                                                System.out.println(customer.viewApprovedAppointments());
                                                System.out.println("Enter the appointment you would like to delete exactly as " +
                                                        "it appears in the above appointment list:\n[Calendar name]-[Appointment Title]," +
                                                        "[Max Attendees],[Approved Bookings],[Start Time],[End Time]");
                                                appointmentName = scanner.nextLine();
                                                repeatCancel = true;
                                            } else {
                                                System.out.println("Invalid input. Please try again.");
                                                repeatCancel = false;
                                            }
                                            customer.cancelAppointment(appointmentName, cancelInput);
                                        } while (!repeatCancel);
                                        repeatCustomerChoices = true;
                                        break;
                                    case 3:
                                        //view a store's calendar
                                        System.out.println("Displayed below are all store calendars: ");
                                        System.out.println(customer.viewCalendars());
                                        repeatCustomerChoices = true;
                                        break;
                                    case 4: //view approved appointments
                                        System.out.println(customer.viewApprovedAppointments());
                                        repeatCustomerChoices = true;
                                        break;
                                    case 5: //view pending appointments
                                        System.out.println(customer.viewAppointmentsWaitingApproval());
                                        repeatCustomerChoices = true;
                                        break;
                                    case 6: //view store stats
                                        boolean repeatSort = true;
                                        //present option to sort dashboard
                                        do {
                                            System.out.println("Would you like to sort the dashboard?");
                                            System.out.println("1 - Yes");
                                            System.out.println("2 - No");

                                            int yesNo;
                                            while (true) { //Makes sure user inputs an int
                                                if (scanner.hasNextInt()) {
                                                    yesNo = scanner.nextInt();
                                                    scanner.nextLine();
                                                    break;
                                                } else {
                                                    System.out.println("Invalid input. Please try again.");
                                                    scanner.next();
                                                }
                                            }

                                            if (yesNo == 1) {
                                                boolean repeatPopular = true;
                                                do {
                                                    System.out.println("Choose a method to sort statistics dashboard");
                                                    System.out.println("1 - Most to Least Popular Stores");
                                                    System.out.println("2 - Least to Most Popular Stores");

                                                    int popularStore;
                                                    while (true) { //Makes sure user inputs an int
                                                        if (scanner.hasNextInt()) {
                                                            popularStore = scanner.nextInt();
                                                            scanner.nextLine();
                                                            break;
                                                        } else {
                                                            System.out.println("Invalid input. Please try again.");
                                                            scanner.next();
                                                        }
                                                    }

                                                    if (popularStore == 1) {
                                                        System.out.println(customer.viewDashboard(1));
                                                        repeatPopular = false;
                                                    } else if (popularStore == 2) {
                                                        //print by least popular
                                                        System.out.println();
                                                        repeatPopular = false;
                                                    } else {
                                                        System.out.println("Invalid choice. Please try again");
                                                    }
                                                    repeatSort = false;
                                                } while (repeatPopular);
                                            } else if (yesNo == 2) {
                                                System.out.println(customer.viewDashboard(2));
                                                repeatSort = false;
                                            } else {
                                                System.out.println("Invalid choice. Please try again");
                                            }
                                        } while (repeatSort);
                                        repeatCustomerChoices = true;
                                        break;
                                    case 7:
                                        // export a file of approved appointments to the user's desktop
                                        String outFile = (username + ".txt");
                                        try (BufferedReader reader = new BufferedReader(new FileReader(outFile))) {
                                            String desktop = System.getProperty("user.home") + "/Desktop/";
                                            String exportPath = desktop + outFile;
                                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath))) {
                                                String line;
                                                while ((line = reader.readLine()) != null) {
                                                    writer.write(line);
                                                    writer.newLine();
                                                }
                                                System.out.println("Your file has been exported! Please check your desktop to view the text file.");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        repeatCustomerChoices = true;
                                        break;
                                    case 8: //log out and return to main menu
                                        repeatCustomerChoices = false;
                                        System.out.println("You have successfully logged out.");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        repeatCustomerChoices = true;
                                        break;
                                }
                            } while (repeatCustomerChoices); //keep presenting customer options until they log out
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        validLogin = true;
                    } else { //Account doesn't exist
                        System.out.println("Your email or password is incorrect. Please try again.");
                    }
                } while (!validLogin);

            } else if (choice == 3) { //User exits the program
                break;
            } else { //User input an invalid choice, will ask again
                System.out.println("Invalid choice\n");
            }
        }
    }
}