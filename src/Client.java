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
    JLabel loginInfoLabel = new JLabel("Please enter your email and password.");
    JLabel emailLabel = new JLabel("Email: ");
    JTextField emailText;
    JLabel passLabel = new JLabel("Password: ");
    JTextField passText;
    JButton createEnterButton;
    JButton loginEnterButton;

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

        //Creates create account frame
        JFrame createAccountFrame = new JFrame("Account");
        content = createAccountFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);

        content.add(selectOptionLabel);
        content.add(sellerOrCustomer);
        layout.putConstraint(SpringLayout.EAST, selectOptionLabel, 5, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.NORTH, selectOptionLabel, 8, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, sellerOrCustomer, 5, SpringLayout.EAST, selectOptionLabel);
        layout.putConstraint(SpringLayout.NORTH, sellerOrCustomer, 5, SpringLayout.NORTH, content);

        content.add(loginInfoLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginInfoLabel, 5, SpringLayout.EAST, selectOptionLabel);
        layout.putConstraint(SpringLayout.NORTH, loginInfoLabel, 20, SpringLayout.SOUTH, selectOptionLabel);

        content.add(emailLabel);
        emailText = new JTextField("", 33);
        content.add(emailText);
        layout.putConstraint(SpringLayout.WEST, emailLabel, 20, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.WEST, emailText, 5, SpringLayout.EAST, emailLabel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 30, SpringLayout.SOUTH, loginInfoLabel);
        layout.putConstraint(SpringLayout.NORTH, emailText, 30, SpringLayout.SOUTH, loginInfoLabel);

        content.add(passLabel);
        passText = new JTextField("", 30);
        content.add(passText);
        layout.putConstraint(SpringLayout.WEST, passLabel, 20, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.WEST, passText, 5, SpringLayout.EAST, passLabel);
        layout.putConstraint(SpringLayout.NORTH, passLabel, 20, SpringLayout.SOUTH, emailLabel);
        layout.putConstraint(SpringLayout.NORTH, passText, 20, SpringLayout.SOUTH, emailLabel);

        createEnterButton = new JButton("Enter");
        content.add(createEnterButton);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createEnterButton, 5, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.NORTH, createEnterButton, 80, SpringLayout.SOUTH, emailLabel);

        createAccountFrame.setSize(600, 300);
        createAccountFrame.setLocationRelativeTo(null);
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //User chooses between two options: Create Account or Login to Existing Account
        createAccountButton.addActionListener(e -> {
            frame.dispose();
            createAccountFrame.setVisible(true);
        });

        Container finalContent = content;
        loginEnterButton = new JButton("Enter");
        loginButton.addActionListener(e -> {
            frame.dispose();
            createAccountFrame.remove(createEnterButton);
            finalContent.add(loginEnterButton);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginEnterButton, 5, SpringLayout.HORIZONTAL_CENTER, finalContent);
            layout.putConstraint(SpringLayout.NORTH, loginEnterButton, 80, SpringLayout.SOUTH, emailLabel);
            createAccountFrame.setVisible(true);
        });

        //If user clicks enter button
        createEnterButton.addActionListener( e -> {
            //TODO: Where we do login credential stuff
        });

        loginEnterButton.addActionListener( e -> {
            //TODO: Where we do login credential stuff
        });
    }
}
