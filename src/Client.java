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

    private String sendDataToServer(JButton button, String data) {
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
                    return serverResponse;
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
                    return serverResponse;
                } else { //response equals "false"
                    //GUI displays error message
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: go back later and change this probably
        return null;
    }

    private String sellerSendOptionToServer(int option, String data) {
        return null;
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

        JButton customerAppointmentBackButton = new JButton("Go Back");
        JButton customerCancelBackButton = new JButton("Go Back");
        JButton customerViewCalendarsBackButton = new JButton("Go Back");
        JButton customerViewApprovedAppointmentsBackButton = new JButton("Go Back");
        JButton customerViewAppointmentsAwaitingBackButton = new JButton("Go Back");
        JButton customerExportFileBackButton = new JButton("Go Back");
        JButton customerViewStatisticsBackButton = new JButton("Go Back");


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
        customerMakeAppointment.add(customerAppointmentBackButton);

        // Customer cancel appointment panel.
        JPanel customerCancelAppointment = new JPanel();
        customerCancelAppointment.add(new JLabel("")); // Replaced with appointments available to cancel.
        customerCancelAppointment.add(customerCancelAppointmentRequest);
        customerCancelAppointment.add(customerCancelText);
        customerCancelAppointment.add(customerCancelButton);
        customerCancelAppointment.add(customerCancelBackButton);

        // Customer view calendars panel.
        JPanel customerViewCalendars = new JPanel();
        customerViewCalendars.add(new JLabel("")); // Replace with calendars.
        customerViewCalendars.add(customerViewCalendarsBackButton);

        // Customer view approved appointments panel.
        JPanel customerViewApprovedAppointments = new JPanel();
        customerViewApprovedAppointments.add(new JLabel("")); // Replace with approved appointments.
        customerViewApprovedAppointments.add(customerViewApprovedAppointmentsBackButton);

        // Customer view appointments awaiting approval panel.
        JPanel customerViewAppointmentsAwaiting = new JPanel();
        customerViewAppointmentsAwaiting.add(new JLabel("")); // Replace with appointments awaiting approval.
        customerViewAppointmentsAwaiting.add(customerViewAppointmentsAwaitingBackButton);


        // Customer view statistics panel.
        JPanel customerViewStatistics = new JPanel();
        customerViewStatistics.setLayout(new BoxLayout(customerViewStatistics, BoxLayout.PAGE_AXIS));

        customerViewStatistics.add(Box.createRigidArea(new Dimension(200, 10)));
        customerSort.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerViewStatistics.add(customerSort);
        customerViewStatistics.add(Box.createRigidArea(new Dimension(1, 10)));

        customerSortOptions.setMaximumSize(new Dimension(400, 25));
        customerSortOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerSortOptions.addItem("Most to Least Popular Stores");
        customerSortOptions.addItem("Least to Most Popular Stores");
        customerViewStatistics.add(customerSortOptions);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        customerSortButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerViewStatistics.add(customerSortButton);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        customerViewStatisticsBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerViewStatistics.add(customerViewStatisticsBackButton);

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

        //Seller panels

        //Options panel
        JLabel sellerOptionsLabel = new JLabel("Select an option from the seller menu below");
        JComboBox<String> sellerOptions = new JComboBox<>();
        JButton sellerProceedButton = new JButton("Proceed");
        JButton sellerLogoutButton = new JButton("Logout");
        JPanel sellerPanel = new JPanel();
        sellerPanel.setLayout(new BoxLayout(sellerPanel, BoxLayout.PAGE_AXIS));
        sellerPanel.add(Box.createRigidArea(new Dimension(200, 10)));
        JPanel storeNamePanel = new JPanel();
        JLabel storeNameLabel = new JLabel("Please enter the name of your store you would like to manage: ");
        JTextField storeNameText = new JTextField("", 20);
        storeNamePanel.add(storeNameLabel);
        storeNamePanel.add(storeNameText);
        sellerPanel.add(storeNamePanel);
        sellerPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        sellerOptionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerPanel.add(sellerOptionsLabel);
        sellerPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        sellerOptions.setMaximumSize(new Dimension(400, 25));
        sellerOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerOptions.addItem("[Choose an option]");
        sellerOptions.addItem("View current calendars");
        sellerOptions.addItem("Create new calendar");
        sellerOptions.addItem("Edit calendar");
        sellerOptions.addItem("Delete calendar");
        sellerOptions.addItem("Approve/decline appointment requests");
        sellerOptions.addItem("View statistics");
        sellerPanel.add(sellerOptions);
        sellerPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        sellerProceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel sellerProceedAndLogoutPanel = new JPanel();
        sellerProceedAndLogoutPanel.add(sellerProceedButton);
        sellerProceedAndLogoutPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        sellerProceedAndLogoutPanel.add(sellerLogoutButton);
        sellerPanel.add(sellerProceedAndLogoutPanel);

        //View current calendars panel
        JLabel viewCalendarsLabel = new JLabel("");
        JButton viewBackButton = new JButton("Go Back");
        viewCalendarsLabel.setBounds(5, 5, 400, 400);
        JPanel viewCalendarsPanel = new JPanel();
        viewCalendarsPanel.setLayout(new BoxLayout(viewCalendarsPanel, BoxLayout.PAGE_AXIS));
        JScrollPane jsp = new JScrollPane(viewCalendarsPanel);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        viewCalendarsPanel.add(viewCalendarsLabel);
        viewBackButton.setMaximumSize(new Dimension(100, 25));
        viewCalendarsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        viewBackButton.setAlignmentX(CENTER_ALIGNMENT);
        viewCalendarsPanel.add(viewBackButton);

        //Create new calendar panel
        JButton importFileButton = new JButton("Import File");
        JButton manualButton = new JButton("Manually Create Calendar");
        JPanel createCalendarPanel = new JPanel();
        createCalendarPanel.add(importFileButton);
        createCalendarPanel.add(manualButton);

        //Import file panel
        JLabel importFileFormatLabel = new JLabel("Please ensure your file is in this format: ", SwingConstants.CENTER);
        JLabel exampleFormatLabel1 = new JLabel("[Store Name],[Calendar Name],[Calendar description],[Appointment Title],[Max Attendees]," +
                "[Approved Bookings],[Start Time],[End Time]");
        JLabel exampleFormatLabel2 = new JLabel("Multiple appointments can be on the same line separated by commas.");
        JLabel exampleFormatLabel3 = new JLabel("Max Attendees can only be an integer value 0 or 1.");
        JLabel importFileLabel = new JLabel("Please enter the filename: ");
        JTextField importFileText = new JTextField("", 20);
        JButton importButton = new JButton("Import");
        JButton importBackButton = new JButton("Go Back");
        JPanel importFilePanel = new JPanel();
        importFilePanel.setLayout(new BoxLayout(importFilePanel, BoxLayout.PAGE_AXIS));
        importFilePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        importFileFormatLabel.setAlignmentX(CENTER_ALIGNMENT);
        importFilePanel.add(importFileFormatLabel);
        importFilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        exampleFormatLabel1.setAlignmentX(CENTER_ALIGNMENT);
        exampleFormatLabel2.setAlignmentX(CENTER_ALIGNMENT);
        exampleFormatLabel3.setAlignmentX(CENTER_ALIGNMENT);
        importFilePanel.add(exampleFormatLabel1);
        importFilePanel.add(exampleFormatLabel2);
        importFilePanel.add(exampleFormatLabel3);
        importFilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JPanel importDataPanel = new JPanel();
        importDataPanel.add(importFileLabel);
        importDataPanel.add(importFileText);
        importDataPanel.add(importButton);
        importFilePanel.add(importDataPanel);
        importFilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        importBackButton.setAlignmentX(CENTER_ALIGNMENT);
        importBackButton.setMaximumSize(new Dimension(100, 25));
        importFilePanel.add(importBackButton);
        importFilePanel.add(Box.createRigidArea(new Dimension(0, 30)));

        //Manually create calendar panel
        JLabel calendarNameLabel = new JLabel("   Calendar name: ");
        JTextField calendarNameText = new JTextField("", 20);
        JLabel calendarDescriptionLabel = new JLabel("Calendar description: ");
        JTextField calendarDescriptionText = new JTextField("", 20);
        JButton addAppointmentButton = new JButton("Add Appointment");
        JLabel appointmentTitleLabel = new JLabel("   Appointment Title");
        JTextField appointmentTitleText = new JTextField("", 20);
        JLabel maxAttendeesLabel = new JLabel("Maximum amount of attendees: ");
        JTextField maxAttendeesText = new JTextField("", 20);
        JLabel approvedBookingsLabel = new JLabel("Number of approved bookings (value can only be a 0 or 1): ");
        JTextField approvedBookingsText = new JTextField("", 20);
        JLabel startTimeLabel = new JLabel("Start time of the appointment (in the form hh:mm): ");
        JTextField startTimeText = new JTextField("", 20);
        JLabel endTimeLabel = new JLabel("End time of the appointment (in the form hh:mm): ");
        JTextField endTimeText = new JTextField("", 20);
        JButton createAppointmentButton = new JButton("Create Appointment");
        JLabel appointmentListLabel = new JLabel("Appointments: None");
        JLabel appointmentListLabel1 = new JLabel("");
        JButton createCalendarButton = new JButton("Create Calendar");
        JButton appointmentBackButton = new JButton("Go Back");
        JPanel manualCalendarPanel = new JPanel();
        manualCalendarPanel.setLayout(new BoxLayout(manualCalendarPanel, BoxLayout.PAGE_AXIS));
        calendarNameLabel.setAlignmentX(LEFT_ALIGNMENT + 0.1f);
        manualCalendarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        manualCalendarPanel.add(calendarNameLabel);
        calendarNameText.setAlignmentX(LEFT_ALIGNMENT);
        calendarNameText.setMaximumSize(new Dimension(200, 25));
        manualCalendarPanel.add(calendarNameText);
        calendarDescriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
        manualCalendarPanel.add(calendarDescriptionLabel);
        calendarDescriptionText.setAlignmentX(LEFT_ALIGNMENT);
        calendarDescriptionText.setMaximumSize(new Dimension(500, 25));
        manualCalendarPanel.add(calendarDescriptionText);
        manualCalendarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        appointmentListLabel.setAlignmentX(LEFT_ALIGNMENT);
        manualCalendarPanel.add(appointmentListLabel);
        appointmentListLabel1.setAlignmentX(LEFT_ALIGNMENT);
        manualCalendarPanel.add(appointmentListLabel1);
        manualCalendarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel appointmentAndCalendarPanel = new JPanel();
        appointmentAndCalendarPanel.add(addAppointmentButton);
        appointmentAndCalendarPanel.add(createCalendarButton);
        appointmentAndCalendarPanel.setAlignmentX(LEFT_ALIGNMENT);
        manualCalendarPanel.add(appointmentAndCalendarPanel);

        //Appointment info panel
        JPanel appointmentInfoPanel = new JPanel();
        appointmentInfoPanel.setLayout(new BoxLayout(appointmentInfoPanel, BoxLayout.PAGE_AXIS));
        appointmentInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        appointmentTitleLabel.setAlignmentX(LEFT_ALIGNMENT + 0.1f);
        appointmentInfoPanel.add(appointmentTitleLabel);
        appointmentTitleText.setAlignmentX(LEFT_ALIGNMENT);
        appointmentTitleText.setMaximumSize(new Dimension(200, 25));
        appointmentInfoPanel.add(appointmentTitleText);
        maxAttendeesLabel.setAlignmentX(LEFT_ALIGNMENT);
        appointmentInfoPanel.add(maxAttendeesLabel);
        maxAttendeesText.setAlignmentX(LEFT_ALIGNMENT);
        maxAttendeesText.setMaximumSize(new Dimension(50, 25));
        appointmentInfoPanel.add(maxAttendeesText);
        approvedBookingsLabel.setAlignmentX(LEFT_ALIGNMENT);
        appointmentInfoPanel.add(approvedBookingsLabel);
        approvedBookingsText.setAlignmentX(LEFT_ALIGNMENT);
        approvedBookingsText.setMaximumSize(new Dimension(50, 25));
        appointmentInfoPanel.add(approvedBookingsText);
        startTimeLabel.setAlignmentX(LEFT_ALIGNMENT);
        appointmentInfoPanel.add(startTimeLabel);
        startTimeText.setAlignmentX(LEFT_ALIGNMENT);
        startTimeText.setMaximumSize(new Dimension(50, 25));
        appointmentInfoPanel.add(startTimeText);
        endTimeLabel.setAlignmentX(LEFT_ALIGNMENT);
        appointmentInfoPanel.add(endTimeLabel);
        endTimeText.setAlignmentX(LEFT_ALIGNMENT);
        endTimeText.setMaximumSize(new Dimension(50, 25));
        appointmentInfoPanel.add(endTimeText);
        appointmentInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel createAndBackPanel = new JPanel();
        createAndBackPanel.add(appointmentBackButton);
        createAndBackPanel.add(createAppointmentButton);
        createAndBackPanel.setAlignmentX(LEFT_ALIGNMENT);
        appointmentInfoPanel.add(createAndBackPanel);

        //TODO: Edit calendar panel

        //TODO: Delete calendar panel

        //TODO: Approve/decline appointment requests panel

        //TODO: View statistics panel

        //TODO: Logout panel

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
                    String validEmail = sendDataToServer(createEnterButton, userOrSeller + "," + name + "," + email + "," + pass);
                    if (validEmail.equals("true")) {
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
                    String validLogin = sendDataToServer(loginEnterButton, userOrSeller + "," + name + "," + email + "," + pass);
                    if (validLogin.equals("true")) {
                        JOptionPane.showMessageDialog(null, "Login successful!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE); //Tells user login was successful

                        //Resets text fields
                        nameText.setText("");
                        emailText.setText("");
                        passText.setText("");

                        //code below is nested inside login check after verification
                        if (userOrSeller.equals("Seller")) {
                            //TODO: Where Seller screen pops up
                            content.removeAll();
                            frame.repaint();
                            content.setLayout(new GridLayout(2, 1));
                            content.add(sellerPanel);
                            frame.setSize(750, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            updateUI();
                            frame.setVisible(true);
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
                             int customerCancelChoice = JOptionPane.showConfirmDialog(null,
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
                            content.add(customerViewCalendars);
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
                            content.add(customerViewApprovedAppointments);
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
                            content.add(customerViewAppointmentsAwaiting);
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
                            content.add(customerViewStatistics);
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
                } else if (e.getSource() == customerAppointmentBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerCancelBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerViewCalendarsBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerViewApprovedAppointmentsBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerViewAppointmentsAwaitingBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerExportFileBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == customerViewStatisticsBackButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(customerPanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == importFileButton) {
                    content.removeAll(); //Clears the frame
                    content.setLayout(new BorderLayout());
                    content.add(importFilePanel);
                    frame.setSize(900, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == manualButton) {
                    content.removeAll(); //Clears the frame
                    content.setLayout(new BorderLayout());
                    content.add(manualCalendarPanel);
                    frame.setSize(700, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == importButton) {

                } else if (e.getSource() == addAppointmentButton) {
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(appointmentInfoPanel);
                    frame.setSize(700, 300);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == createAppointmentButton) {
                    //Getting all the info
                    String apptTitle = appointmentTitleText.getText();
                    int maxAttendees = Integer.parseInt(maxAttendeesText.getText());
                    int approvedBookings = Integer.parseInt(approvedBookingsText.getText());
                    String startTime = startTimeText.getText();
                    String endTime = endTimeText.getText();

                    //Clearing the textfields
                    appointmentTitleText.setText("");
                    maxAttendeesText.setText("");
                    approvedBookingsText.setText("");
                    startTimeText.setText("");
                    endTimeText.setText("");

                    //Creating appointment object
                    Appointment newAppt = new Appointment(apptTitle, maxAttendees, approvedBookings, startTime, endTime);

                    //Adding appointment to calendar page
                    if (appointmentListLabel.getText().length() > 14) {
                        appointmentListLabel.setText(appointmentListLabel.getText().substring(0, 13));
                    }

                    if (appointmentListLabel1.getText().length() > 1) {
                        appointmentListLabel1.setText(appointmentListLabel1.getText().substring(0, appointmentListLabel1.getText().length() - 7) + "<br/>" + newAppt + "</html>");
                    } else {
                        appointmentListLabel1.setText(appointmentListLabel1.getText() + "<html><br/>" + newAppt + "</html>");
                    }


                    //Bringing user back to calendar page
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(manualCalendarPanel);
                    frame.setSize(700, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);

                } else if (e.getSource() == sellerProceedButton) {
                    int sellerMenuSelection = sellerOptions.getSelectedIndex();
                    switch (sellerMenuSelection) {
                        case 1: //View current calendars
                            viewCalendarsLabel.setText(sellerSendOptionToServer(1, storeNameText.getText()));
                            content.removeAll(); //Clears the frame
                            content.setLayout(new BorderLayout());
                            content.add(viewCalendarsPanel);
                            frame.setSize(900, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setVisible(true);
                            break;
                        case 2: //Create new calendar
                            content.removeAll(); //Clears the frame
                            content.setLayout(new BorderLayout());
                            content.add(createCalendarPanel);
                            frame.setSize(400, 200);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setVisible(true);
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                    }
                } else if (e.getSource() == sellerLogoutButton) {

                } else if (e.getSource() == createCalendarButton) {

                } else if (e.getSource() == appointmentBackButton) {
                    //Clearing the textfields
                    appointmentTitleText.setText("");
                    maxAttendeesText.setText("");
                    approvedBookingsText.setText("");
                    startTimeText.setText("");
                    endTimeText.setText("");

                    //Going back to manually create calendar page
                    content.removeAll(); //Clears the frame
                    frame.repaint();
                    content.setLayout(new BorderLayout());
                    content.add(manualCalendarPanel);
                    frame.setSize(700, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } else if (e.getSource() == viewBackButton) {
                    content.removeAll();
                    frame.repaint();
                    content.setLayout(new GridLayout(2, 1));
                    content.add(sellerPanel);
                    frame.setSize(750, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateUI();
                    frame.setVisible(true);
                } else if (e.getSource() == importBackButton) {
                    importFileText.setText("");

                    content.removeAll();
                    frame.repaint();
                    content.setLayout(new GridLayout(2, 1));
                    content.add(sellerPanel);
                    frame.setSize(750, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateUI();
                    frame.setVisible(true);
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
        customerAppointmentBackButton.addActionListener(actionListener);
        customerCancelBackButton.addActionListener(actionListener);
        customerViewCalendarsBackButton.addActionListener(actionListener);
        customerViewApprovedAppointmentsBackButton.addActionListener(actionListener);
        customerViewAppointmentsAwaitingBackButton.addActionListener(actionListener);
        customerExportFileBackButton.addActionListener(actionListener);
        customerViewStatisticsBackButton.addActionListener(actionListener);
        importFileButton.addActionListener(actionListener);
        manualButton.addActionListener(actionListener);
        importButton.addActionListener(actionListener);
        addAppointmentButton.addActionListener(actionListener);
        createAppointmentButton.addActionListener(actionListener);
        sellerProceedButton.addActionListener(actionListener);
        sellerLogoutButton.addActionListener(actionListener);
        createCalendarButton.addActionListener(actionListener);
        appointmentBackButton.addActionListener(actionListener);
        viewBackButton.addActionListener(actionListener);
        importBackButton.addActionListener(actionListener);
    }
}