import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream())
        ) {
            // Creates a new user (seller or customer).
            User user = new User();

            String email = "";
            String password = "";

            String userType = bufferedReader.readLine(); // Either (1) Seller or (2) Customer.
            String name = bufferedReader.readLine(); // Name of the user.

            if (userType.equals("1")) {
                userType = "Seller"; // Sets user type to Seller.
            } else {
                userType = "Customer"; // Sets user type to Customer.
            }

            String createAccount = bufferedReader.readLine(); // Receives input on creating or logging in to account.

            if (createAccount.equals("true")) {
                boolean runAgain = true;

                while (runAgain) {
                    email = bufferedReader.readLine();
                    if (user.usernameExists("userDatabase.txt", email)) {
                        writer.write("true"); // Sends "true" to Client if username is accepted.
                        writer.println();
                        writer.flush();
                        runAgain = false;
                    } else {
                        writer.write("false"); // Sends "false" to Client if username is not accepted.
                        writer.println();
                        writer.flush();
                    }
                }

                password = bufferedReader.readLine(); // Receives password from Client.
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("userDatabase.txt",
                        true)));

                // Writes User info to userDatabase.txt file.
                pw.println(userType + "," + name + "," + email + "," + password);
                pw.flush();
                pw.close();
            } else {
                boolean runAgain = true;
                while (runAgain) {
                    email = bufferedReader.readLine();
                    password = bufferedReader.readLine();

                    if (user.validator("userDatabase.txt", email, password)) {
                        writer.write("true"); // Successful login.
                        writer.println();
                        writer.flush();
                        runAgain = false;
                    } else {
                        writer.write("false"); // Unsuccessful login.
                        writer.println();
                        writer.flush();
                    }
                }
            }

            if (userType.equals("Seller")) {
                String storeName = bufferedReader.readLine();

                Seller seller = new Seller(email, storeName, email + ".txt");
                String choice = "";
                boolean runMenuAgain = true;
                while (runMenuAgain) {
                    choice = bufferedReader.readLine(); // Receives choice for action menu.

                    if (choice.equals("1")) { // Handles view current calendars.
                        String currentCalendars = seller.printCalendar();

                        if (currentCalendars.isEmpty()) { // Handles case where no calendars were created.
                            writer.print("No current calendars!");
                            writer.println();
                            writer.flush();
                        } else {
                            writer.print(currentCalendars); // Sends current calendars to Client.
                            writer.println();
                            writer.flush();
                        }
                    } else if (choice.equals("2")) { // Handles create new calendar.
                        String importChoice = bufferedReader.readLine(); // Indicates to create calendar with file or manually.
                        if (importChoice.equals("1")) { // Import file
                            String fileName = bufferedReader.readLine();
                            seller.createCalendarWithFile(System.getProperty("user.home") + "/Desktop/" + fileName); // Creates calendar with file.
                        } else if (importChoice.equals("2")) { // Manually create file.
                            boolean runAgain = true;
                            ArrayList<Appointment> apptList = new ArrayList<>(); // Holds the appointments.

                            String calendarName = bufferedReader.readLine(); // Receives calendar name.
                            String description = bufferedReader.readLine(); // Receives calendar description.

                            while (runAgain) {
                                String appointmentTile = bufferedReader.readLine(); // Receives appointment title
                                int maxAttendees = Integer.parseInt(bufferedReader.readLine()); // Receives max attendees.
                                int approvedBookings = Integer.parseInt(bufferedReader.readLine()); // Receives approved bookings.
                                String startTime = bufferedReader.readLine(); // Receives start time.
                                String endTime = bufferedReader.readLine(); // Receives end time.

                                Appointment newAppt = new Appointment(appointmentTile, maxAttendees,
                                        approvedBookings, startTime, endTime);
                                apptList.add(newAppt);

                                String newAppointment = bufferedReader.readLine(); // (1) add appointment. (2) no appointment.
                                if (newAppointment.equals("2")) { // Indicates User does not have more appointments.
                                    runAgain = false;
                                }
                            }
                            seller.createCalendar(calendarName, description, apptList); // Creates the new calendar.
                        }
                    } else if (choice.equals("3")) { // Handles edit calendar.
                        writer.print(seller.printCalendar()); // Sends created calendars to client.
                        writer.println();
                        writer.flush();

                        String calendarName = bufferedReader.readLine();
                        String oldApptTitle = bufferedReader.readLine();
                        String apptTitle = bufferedReader.readLine();
                        int maxAttendee = Integer.parseInt(bufferedReader.readLine());
                        int approvedBookings = Integer.parseInt(bufferedReader.readLine());
                        String startTime = bufferedReader.readLine();
                        String endTime = bufferedReader.readLine();

                        Appointment editedAppt = new Appointment(apptTitle, maxAttendee, approvedBookings, startTime, endTime);
                        seller.editCalendar(calendarName, oldApptTitle, calendarName + "-" + editedAppt);
                    } else if (choice.equals("4")) { // Handles delete calendar.
                        writer.print(seller.printCalendar()); // Sends created calendars to client.
                        writer.println();
                        writer.flush();

                        String deletedCalendarName = bufferedReader.readLine();
                        seller.deleteCalendar(deletedCalendarName);
                        if (seller.deleteCalendar(deletedCalendarName).equals("Calendar deleted")) {
                            writer.println("Calendar deleted!");
                            writer.flush();
                        } else {
                            writer.println("Calendar not deleted!");
                            writer.flush();
                        }
                    } else if (choice.equals("5")) { // Handles approve/decline appointments.
                        if (seller.getCustomerRequest().equals("No appointment requests")) {
                            writer.println(seller.getCustomerRequest()); // Sends message to Client.
                            writer.flush();

                        } else {
                            writer.println(seller.getCustomerRequest()); // Sends customer requests to Client.
                            writer.flush();

                            String requestedAppointment = bufferedReader.readLine();
                            String action = bufferedReader.readLine();
                            String requestUsername = bufferedReader.readLine();
                            seller.handleCustomerRequests(requestedAppointment, requestUsername, action);
                            if (seller.handleCustomerRequests(requestedAppointment, requestUsername, action).equals("Approved")) {
                                writer.println("Appointment approved!");
                                writer.flush();
                            } else {
                                writer.println("Appointment declined!");
                                writer.flush();
                            }
                        }
                    } else if (choice.equals("6")) { // Handles view currently approved appointments.
                        String approvedAppointments = seller.viewApprovedAppointments();
                        writer.println(approvedAppointments);
                        writer.flush();
                    } else if (choice.equals("7")) { // Handles view statistics.
                        boolean validSort = false;

                        while (!validSort) {
                            String sort = bufferedReader.readLine();
                            if (sort.equalsIgnoreCase("Yes") || sort.equalsIgnoreCase("No")) {
                                validSort = true;
                                writer.println(seller.viewStatistics(sort));
                                writer.flush();
                            } else {
                                writer.println("Invalid response");
                                writer.flush();
                            }
                        }
                    } else {
                        writer.println("You have successfully logged out.");
                        writer.flush();
                        runMenuAgain = false; // Exits the loop.
                    }
                }

            } else {

                boolean runner = true;
                while (runner) {
                    String userName = "userName"; //username needs to be imported in

                    Customer customer = new Customer(userName, userName + ".txt");
                    String choice = bufferedReader.readLine();

                    if (choice.equals("1")) {

                        String appointment = bufferedReader.readLine();
                        String message = customer.makeAppointment(appointment);

                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("2")) {

                        String appointment = bufferedReader.readLine();
                        int response = Integer.parseInt(bufferedReader.readLine());


                        boolean cancelled = customer.cancelAppointment(appointment, response);
                        String message = "";
                        if (cancelled) {
                            message = "Appointment cancelled successfully.";
                        } else {
                            message = "Appointment cancellation failed. " +
                                    "Sorry, not appointment.";
                        }

                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("3")) {

                        String message = customer.viewCalendars();


                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("4")) {

                        String message = customer.viewApprovedAppointments();

                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("5")) {

                        String message = customer.viewAppointmentsWaitingApproval();

                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("6")) {

                        int sort = Integer.parseInt(bufferedReader.readLine());

                        String message = customer.viewDashboard(sort);

                        writer.println(message);
                        writer.flush();

                    } else if (choice.equals("7")) {

                        String outFile = (userName + ".txt");
                        try (BufferedReader reader = new BufferedReader(new FileReader(outFile))) {
                            String desktop = System.getProperty("user.home") + "/Desktop/";
                            String exportPath = desktop + outFile;
                            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(exportPath))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    bufferedWriter.write(line);
                                    bufferedWriter.newLine();
                                }
                                String message = "Your file has been exported! Please check your desktop to view the text file.";

                                writer.println(message);
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else if (choice.equals("8")) {

                        writer.println("Thank you for using our app.");
                        writer.flush();
                        break;

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8008);

        while (true) { // Server is in a continuous loop.
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            new Thread(server).start();
        }
    }
}