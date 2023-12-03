import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: Update passTextField to a JPasswordField
//TODO: Create GUI for Seller and Customer Login

public class Client extends JComponent implements Runnable {
    private static final int port = 8008;

    private boolean sendDataToServer(JButton button, String data) {
        try {
            Socket socket = new Socket("localhost", port);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            String clientMessage = "";
            String serverResponse = "";
            if (button.getText().equals("Enter")) { //Creating an account
                pw.write("true");
                pw.println();

                String[] info = data.split(",");
                //Sends whether seller or customer
                pw.write(info[0]);
                pw.println();

                //Sends name of user
                pw.write(info[1]);
                pw.println();
                pw.flush();

                //Sends email
                pw.write(info[2]);
                pw.println();
                pw.flush();

                //Sends password
                pw.write(info[3]);
                pw.println();
                pw.flush();

                serverResponse = bfr.readLine();
                if (serverResponse.equals("true")) {
                    return true;
                } else { //response equals "false"
                    //GUI displays error message
                }
            } else { //Button is "Login"
                pw.write("false");
                pw.println();

                String[] info = data.split(",");
                //Sends whether seller or customer
                pw.write(info[0]);
                pw.println();

                //Sends name of user
                pw.write(info[1]);
                pw.println();
                pw.flush();

                //Sends email
                pw.write(info[2]);
                pw.println();
                pw.flush();

                //Sends password
                pw.write(info[3]);
                pw.println();
                pw.flush();

                serverResponse = bfr.readLine();
                if (serverResponse.equals("true")) {
                    return true;
                } else { //response equals "false"
                    //GUI displays error message
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: go back later and change this probably
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());
    }

    public void run() {
        //Create JFrame
        JFrame frame = new JFrame("Hotel Manager");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Create components
        JLabel welcomeMessageLabel = new JLabel("Welcome to the Hotel Manager!");
        JLabel selectOptionLabel = new JLabel("Please select an option.");
        JButton createAccountButton = new JButton("Create Account");
        JButton loginButton = new JButton("Login");

        //Login info components - used for both create account and login
        JComboBox<String> sellerOrCustomer = new JComboBox();
        sellerOrCustomer.addItem("Seller");
        sellerOrCustomer.addItem("Customer");
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameText = new JTextField("", 20);
        JLabel loginInfoLabel = new JLabel("Please enter your email and password.");
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailText = new JTextField("", 20);
        JLabel passLabel = new JLabel("Password: ");
        JTextField passText = new JTextField("", 20);
        JButton createEnterButton = new JButton("Enter");
        JButton loginEnterButton = new JButton("Login");
        JButton createBackButton = new JButton("Go Back");
        JButton loginBackButton = new JButton("Go Back");

        //Customer options components
        JLabel customerOptionsLabel = new JLabel("Select an option from the customer menu below");
        JComboBox<String> customerOptions = new JComboBox<>();
        JButton customerProceedButton = new JButton("Proceed");
        JLabel exitMessage = new JLabel("Thank you for using the Hotel Manager. You have successfully logged out.");
        JLabel closingMessage = new JLabel("This message will close in 5 seconds.");
        JLabel customerAppointmentRequest = new JLabel("<html>Enter the appointment you would like to request exactly as " +
                "it appears in the appointment list:<br/> <br/> Format: [Calendar name]-[Appointment Title]," +
                "[Max Attendees],[Approved Bookings],[Start Time],[End Time]</html>", SwingConstants.CENTER);
        JTextField customerAppointmentText = new JTextField("", 45);
        JButton customerAppointmentButton = new JButton("Make Appointment");
        JLabel customerCancelAppointmentRequest = new JLabel("<html>Enter the appointment you would like to cancel " +
                "exactly as it appears in the appointment list:<br/> <br/> Format: [Calendar name]-[Appointment Title]," +
                "[Max Attendees],[Approved Bookings],[Start Time],[End Time]</html>");
        JTextField customerCancelText = new JTextField("", 45);
        JButton customerCancelButton = new JButton("Cancel Appointment");

        JLabel customerSort = new JLabel("Choose a method to sort statistics dashboard");
        JComboBox<String> customerSortOptions = new JComboBox<>();
        JButton customerSortButton = new JButton("View Statistics");




        //Creates main panel
        welcomeMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel initialOptionPanel = new JPanel();
        initialOptionPanel.setLayout(new BoxLayout(initialOptionPanel, BoxLayout.PAGE_AXIS));
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(welcomeMessageLabel);
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(createAccountButton);
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        initialOptionPanel.add(loginButton);

        //Adds initial option panel to frame
        content.add(initialOptionPanel, BorderLayout.CENTER);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //Creates create account panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        selectOptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(selectOptionLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        sellerOrCustomer.setMaximumSize(new Dimension(100, 25));
        sellerOrCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(sellerOrCustomer);
        infoPanel.add(Box.createRigidArea(new Dimension(1, 20)));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
        namePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(50, 0)));
        nameText.setMaximumSize(new Dimension(200, 25));
        namePanel.add(nameText);

        JPanel loginInfoPanel = new JPanel();
        loginInfoPanel.add(loginInfoLabel);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.LINE_AXIS));
        emailPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        emailText.setMaximumSize(new Dimension(300, 25));
        emailPanel.add(emailText);

        JPanel passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.LINE_AXIS));
        passPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        passPanel.add(passLabel);
        passPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        passText.setMaximumSize(new Dimension(200, 25));
        passPanel.add(passText);

        JPanel createEnterPanel = new JPanel();
        createEnterPanel.setLayout(new BoxLayout(createEnterPanel, BoxLayout.LINE_AXIS));
        createEnterPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        createEnterPanel.add(createBackButton);
        createEnterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        createEnterPanel.add(createEnterButton);
        createEnterPanel.add(Box.createRigidArea(new Dimension(1, 10)));

        JPanel loginEnterPanel = new JPanel();
        loginEnterPanel.setLayout(new BoxLayout(loginEnterPanel, BoxLayout.LINE_AXIS));
        loginEnterPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        loginEnterPanel.add(loginBackButton);
        loginEnterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        loginEnterPanel.add(loginEnterButton);
        loginEnterPanel.add(Box.createRigidArea(new Dimension(1, 10)));

        //Customer panel
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.PAGE_AXIS));
        customerPanel.add(Box.createRigidArea(new Dimension(200, 10)));
        customerOptionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(customerOptionsLabel);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        customerOptions.setMaximumSize(new Dimension(400, 25));
        customerOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerOptions.addItem("Make an appointment request");
        customerOptions.addItem("Cancel an appointment request");
        customerOptions.addItem("View store calendars");
        customerOptions.addItem("View currently approved appointments");
        customerOptions.addItem("View pending appointments");
        customerOptions.addItem("View store statistics");
        customerOptions.addItem("Export a file of your approved appointments");
        customerOptions.addItem("Exit and log out");
        customerPanel.add(customerOptions);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        customerProceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(customerProceedButton);
        customerPanel.add(Box.createRigidArea(new Dimension(20, 0)));


        // Customer make appointment request panel.
        JPanel customerMakeAppointment = new JPanel();
        customerMakeAppointment.add(customerAppointmentRequest);
        customerMakeAppointment.add(customerAppointmentText);
        customerMakeAppointment.add(customerAppointmentButton);

        // Customer cancel appointment panel.
        JPanel customerCancelAppointment = new JPanel();
        customerCancelAppointment.add(new JLabel("")); // Replaced with appointments available to cancel.
        customerCancelAppointment.add(customerCancelAppointmentRequest);
        customerCancelAppointment.add(customerCancelText);
        customerCancelAppointment.add(customerCancelButton);

        // Customer view statistics panel.
        JPanel customerViewStatistics = new JPanel();
        customerViewStatistics.setLayout(new BoxLayout(customerViewStatistics, BoxLayout.Y_AXIS));
        customerViewStatistics.add(customerSort);
        customerSortOptions.setMaximumSize(new Dimension(400, 25));
        customerSortOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerSortOptions.addItem("Most to Least Popular Stores");
        customerSortOptions.addItem("Least to Most Popular Stores");
        customerViewStatistics.add(customerSortOptions);
        customerViewStatistics.add(customerSortButton);


        //Exit panel
        JPanel exitLogOutPanel = new JPanel();
        exitLogOutPanel.setLayout(new BoxLayout(exitLogOutPanel, BoxLayout.PAGE_AXIS));
        exitLogOutPanel.add(Box.createRigidArea(new Dimension(200, 60)));
        exitMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitLogOutPanel.add(exitMessage);
        exitLogOutPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        closingMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitLogOutPanel.add(closingMessage);
        exitLogOutPanel.add(Box.createRigidArea(new Dimension(200, 10)));

        //Action listeners
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == createAccountButton) {
                    content.removeAll(); //Removes initial option menu

                    //Adding create account panels
                    content.setLayout(new GridLayout(6, 1));
                    content.add(infoPanel);
                    content.add(namePanel);
                    content.add(loginInfoPanel);
                    content.add(emailPanel);
                    content.add(passPanel);
                    content.add(createEnterPanel);
                    frame.setSize(600, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == loginButton) {
                    content.removeAll(); //Removes initial option menu

                    //Adding login panels
                    content.setLayout(new GridLayout(6, 1));
                    content.add(infoPanel);
                    content.add(namePanel);
                    content.add(loginInfoPanel);
                    content.add(emailPanel);
                    content.add(passPanel);
                    content.add(loginEnterPanel);
                    frame.setSize(600, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == createEnterButton) {
                    //Gathering data to send to Server
                    String userOrSeller = (String) sellerOrCustomer.getSelectedItem();
                    String name = nameText.getText();
                    String email = emailText.getText();
                    String pass = passText.getText();

                    //Sends info to Server
                    boolean validEmail = sendDataToServer(createEnterButton, userOrSeller + "," + name + "," + email + "," + pass);
                    if (validEmail) {
                        JOptionPane.showMessageDialog(null, "Account created!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE); //Tells user account has been created

                        //Resets all text fields
                        nameText.setText("");
                        emailText.setText("");
                        passText.setText("");

                        content.removeAll(); //Removes current panel

                        //Returning user to initial option menu
                        content.setLayout(new BorderLayout());
                        content.add(initialOptionPanel);
                        frame.setSize(400, 200);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email already exists! Please choose a different email",
                                "Create Account", JOptionPane.ERROR_MESSAGE); //Error message

                        //Resets text fields so user can try again
                        emailText.setText("");
                        passText.setText("");
                    }
                } else if (e.getSource() == loginEnterButton) {
                    //Gathering data to send to Server
                    String userOrSeller = (String) sellerOrCustomer.getSelectedItem();
                    String name = nameText.getText();
                    String email = emailText.getText();
                    String pass = passText.getText();

                    //Sends info to Server
                    boolean validLogin = sendDataToServer(loginEnterButton, userOrSeller + "," + name + "," + email + "," + pass);
                    if (validLogin) {
                        JOptionPane.showMessageDialog(null, "Login successful!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE); //Tells user login was successful

                        //Resets text fields
                        nameText.setText("");
                        emailText.setText("");
                        passText.setText("");

                        //code below is nested inside login check after verification
                        if (userOrSeller.equals("Seller")) {
                            //TODO: Where Seller screen pops up
                        } else if (userOrSeller.equals("Customer")) { //TODO: Customer screen pops up
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            content.add(customerPanel);
                            frame.setSize(750, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                        }
                    } else { //Invalid login
                        JOptionPane.showMessageDialog(null, "Your email or password in incorrect!",
                                "Login", JOptionPane.ERROR_MESSAGE); //Error message

                        //Resets text fields so user can try again
                        emailText.setText("");
                        passText.setText("");
                    }
                } else if (e.getSource() == createBackButton) {
                    content.removeAll(); //Clears the frame

                    //Takes user back to initial option screen
                    content.setLayout(new BorderLayout());
                    content.add(initialOptionPanel);
                    frame.setSize(400, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == loginBackButton) {
                    content.removeAll(); //Clears the frame

                    //Takes user back to initial option screen
                    content.setLayout(new BorderLayout());
                    content.add(initialOptionPanel);
                    frame.setSize(400, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerProceedButton) {
                    int customerMenuSelection = customerOptions.getSelectedIndex();
                    switch (customerMenuSelection) {
                        case 0:
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            content.add(customerMakeAppointment);
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            break;
                        case 1:
                            JOptionPane.showConfirmDialog(null,
                                    "Choose an appointment category to delete from: \n" +
                                    "Yes - Appointments Awaiting Approval\nNo - Appointments Approved",
                                    "Cancel Option", JOptionPane.YES_NO_OPTION);
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            content.add(customerCancelAppointment);
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            break;
                        case 2:
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                           // content.add(); // JLabel with the created calendars.
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            //option 3 here
                            break;
                        case 3:
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            //option 4 here
                            break;
                        case 4:
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            break;
                        case 5:
                            //option 6 here
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            frame.setSize(750, 300);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            break;
                        case 6:
                            JOptionPane.showMessageDialog(null,
                                    "Your file has been exported! Please check your desktop to " +
                                            "view the text file.", "File Export", JOptionPane.PLAIN_MESSAGE);
                            //option 7 here
                            break;
                        case 7:
                            content.removeAll();
                            content.setLayout(new GridLayout(1, 1));
                            content.add(exitLogOutPanel);
                            frame.setSize(600, 200);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
                            //display exit message and end the program in 5 seconds
                            Timer timer = new Timer(5000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        // Add action listeners to buttons
        createAccountButton.addActionListener(actionListener);
        createEnterButton.addActionListener(actionListener);
        loginButton.addActionListener(actionListener);
        loginEnterButton.addActionListener(actionListener);
        createBackButton.addActionListener(actionListener);
        loginBackButton.addActionListener(actionListener);
        customerProceedButton.addActionListener(actionListener);
    }
}