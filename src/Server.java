import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

                String password = bufferedReader.readLine(); // Receives password from Client.
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("userDatabase.txt",
                        true)));

                // Writes User info to userDatabase.txt file.
                pw.println(userType + "," + name + "," + email + "," + password);
                pw.flush();
                pw.close();
            } else {






            }

            if (userType.equals("Seller")) {





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

        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            new Thread(server).start();
        }
    }
}