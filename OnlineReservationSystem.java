import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineReservationSystem {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public OnlineReservationSystem() {
        frame = new JFrame("Login Form");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to validate username and password
                if ("admin".equals(usernameField.getText()) && "password".equals(new String(passwordField.getPassword()))) {
                    openReservationSystem();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        frame.setVisible(true);
    }

    private void openReservationSystem() {
        frame.getContentPane().removeAll();
        frame.setTitle("Reservation System");
        frame.setLayout(new GridLayout(8, 2));

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to insert reservation details
                JOptionPane.showMessageDialog(frame, "Reservation successfully inserted!");
            }
        });

        frame.add(new JLabel("Train Number:"));
        frame.add(new JTextField());
        frame.add(new JLabel("Train Name:"));
        frame.add(new JTextField());
        frame.add(new JLabel("Class Type:"));
        frame.add(new JTextField());
        frame.add(new JLabel("Date of Journey:"));
        frame.add(new JTextField());
        frame.add(new JLabel("From (Place):"));
        frame.add(new JTextField());
        frame.add(new JLabel("To (Destination):"));
        frame.add(new JTextField());
        frame.add(insertButton);

        JButton cancelReservationButton = new JButton("Cancel Reservation");
        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCancellationForm();
            }
        });

        frame.add(cancelReservationButton);

        frame.revalidate();
        frame.repaint();
    }

    private void openCancellationForm() {
        frame.getContentPane().removeAll();
        frame.setTitle("Cancellation Form");
        frame.setLayout(new GridLayout(3, 2));

        JTextField pnrField = new JTextField();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to fetch and display reservation details based on PNR
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to cancel this reservation?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Reservation canceled successfully!");
                }
            }
        });

        frame.add(new JLabel("Enter PNR:"));
        frame.add(pnrField);
        frame.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineReservationSystem();
            }
        });
    }
}
