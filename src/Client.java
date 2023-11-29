import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JComponent implements Runnable{
    JLabel welcomeMessageLabel = new JLabel("Welcome to the Hotel Manager!");
    JLabel selectOptionLabel = new JLabel("Please select an option.");
    JButton createAccountButton;
    JButton loginButton;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createAccountButton) {

            } else if (e.getSource() == loginButton) {

            }
        }
    };
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());

    }

    public void run() {
        //Creates JFrame
        JFrame frame = new JFrame("Hotel Manager");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Creates initial option panel
        welcomeMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectOptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton = new JButton("Create Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.addActionListener(actionListener);
        loginButton = new JButton("Login to an Existing Account");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(actionListener);
        JPanel initialOptionPanel = new JPanel();
        initialOptionPanel.setLayout(new BoxLayout(initialOptionPanel, BoxLayout.PAGE_AXIS));
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(welcomeMessageLabel);
        initialOptionPanel.add(selectOptionLabel);
        initialOptionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        initialOptionPanel.add(createAccountButton);
        initialOptionPanel.add(loginButton);

        //Adds initial option panel to frame
        content.add(initialOptionPanel, BorderLayout.CENTER);

        //Displays frame
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

