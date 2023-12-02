import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JComponent implements Runnable {
    JLabel welcomeMessageLabel = new JLabel("Welcome to the Hotel Manager!");
    JLabel selectOptionLabel = new JLabel("Please select an option.");
    JButton createAccountButton;
    JButton loginButton;

    //Login info panel - used for both create account and login
    JComboBox<String> sellerOrCustomer = new JComboBox();
    JLabel nameLabel = new JLabel("Name: ");
    JTextField nameText;
    JLabel loginInfoLabel = new JLabel("Please enter your email and password.");
    JLabel emailLabel = new JLabel("Email: ");
    JTextField emailText;
    JLabel passLabel = new JLabel("Password: ");
    JTextField passText;
    JButton createEnterButton;
    JButton loginEnterButton;
    JButton createBackButton;
    JButton loginBackButton;

    //Customer options panel
    JLabel customerOptionsLabel = new JLabel("Select an option from the customer menu below");
    JComboBox<String> customerOptions = new JComboBox<>();
    JButton customerProceedButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());
    }

    //Event dispatch thread
    public void run() {
        //Creates main JFrame
        JFrame frame = new JFrame("Hotel Manager");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Creates intial option panel
        welcomeMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectOptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton = new JButton("Create Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel initialOptionPanel = new JPanel();
        initialOptionPanel.setLayout(new BoxLayout(initialOptionPanel, BoxLayout.PAGE_AXIS));
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(welcomeMessageLabel);
        initialOptionPanel.add(selectOptionLabel);
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(createAccountButton);
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        initialOptionPanel.add(loginButton);

        //Adds initial option panel to frame
        content.add(initialOptionPanel, BorderLayout.CENTER);

        //Displays main frame
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //Adding choices to the combo box
        sellerOrCustomer.addItem("Seller");
        sellerOrCustomer.addItem("Customer");

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
        nameText = new JTextField("", 20);
        nameText.setMaximumSize(new Dimension(200, 25));
        namePanel.add(nameText);

        JPanel loginInfoPanel = new JPanel();
        loginInfoPanel.add(loginInfoLabel);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.LINE_AXIS));
        emailPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        emailText = new JTextField("", 20);
        emailText.setMaximumSize(new Dimension(300, 25));
        emailPanel.add(emailText);

        JPanel passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.LINE_AXIS));
        passPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        passPanel.add(passLabel);
        passPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        passText = new JTextField("", 20);
        passText.setMaximumSize(new Dimension(200, 25));
        passPanel.add(passText);

        JPanel createEnterPanel = new JPanel();
        createEnterPanel.setLayout(new BoxLayout(createEnterPanel, BoxLayout.LINE_AXIS));
        createEnterPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        createBackButton = new JButton("Go Back");
        createEnterPanel.add(createBackButton);
        createEnterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        createEnterButton = new JButton("Enter");
        createEnterPanel.add(createEnterButton);
        createEnterPanel.add(Box.createRigidArea(new Dimension(1, 10)));

        JPanel loginEnterPanel = new JPanel();
        loginEnterPanel.setLayout(new BoxLayout(loginEnterPanel, BoxLayout.LINE_AXIS));
        loginEnterPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        loginBackButton = new JButton("Go Back");
        loginEnterPanel.add(loginBackButton);
        loginEnterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        loginEnterButton = new JButton("Enter");
        loginEnterPanel.add(loginEnterButton);
        loginEnterPanel.add(Box.createRigidArea(new Dimension(1, 10)));

        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.PAGE_AXIS));
        customerPanel.add(Box.createRigidArea(new Dimension(200, 10)));
        customerOptionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(customerOptionsLabel);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        customerOptions.setMaximumSize(new Dimension(400, 25));
        customerOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(customerOptions);
        customerPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        customerProceedButton = new JButton("Proceed");
        customerProceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(customerProceedButton);
        customerPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        createBackButton.addActionListener(e -> {
            content.removeAll();
            content.setLayout(new BorderLayout());
            content.add(initialOptionPanel);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateUI();
            frame.setVisible(true);
        });

        loginBackButton.addActionListener(e -> {
            content.removeAll();
            content.setLayout(new BorderLayout());
            content.add(initialOptionPanel);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateUI();
            frame.setVisible(true);
        });

        createAccountButton.addActionListener(e -> {
            content.removeAll();
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
            updateUI();
            frame.setVisible(true);
        });

        loginButton.addActionListener(e -> {
            content.removeAll();
            content.setLayout(new GridLayout(5, 1));
            content.add(infoPanel);
            content.add(loginInfoPanel);
            content.add(emailPanel);
            content.add(passPanel);
            content.add(loginEnterPanel);
            frame.setSize(600, 350);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateUI();
            frame.setVisible(true);
        });

        createEnterButton.addActionListener( e -> {
            //TODO: Where we do login credential stuff
        });

        loginEnterButton.addActionListener( e -> {
            //TODO: Where we do login credential stuff
            String userTypeSelection = (String) sellerOrCustomer.getSelectedItem();
            //code below is nested inside login check after verification
            if (userTypeSelection.equals("Seller")) {

            } else if (userTypeSelection.equals("Customer")) {
                content.removeAll();
                content.setLayout(new GridLayout(2, 1));
                content.add(customerPanel);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateUI();
                frame.setVisible(true);
            }
        });
    }
}
