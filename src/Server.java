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
                boolean runAgain = true;
                while (runAgain) {
                    String userEmail = bufferedReader.readLine();
                    String password = bufferedReader.readLine();


                    if (user.validator("userDatabase.txt", userEmail, password)) {
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





            } else {

                String userName = "userName";

                Customer customer = new Customer(userName, userName + ".txt");
                String choice = bufferedReader.readLine();

                if (choice.equals("1")) {

                    String appointment = bufferedReader.readLine();
                    String message = customer.makeAppointment(appointment);

                    writer.println(message);
                    writer.flush();
                    writer.close();

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