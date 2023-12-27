import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExaminationSystem {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Timer timer;
    private int timeRemaining = 120; // 2 minutes in seconds
    private boolean examSubmitted = false;

    public OnlineExaminationSystem() {
        frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a JPanel for login components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to validate username and password
                if ("admin".equals(usernameField.getText()) && "password".equals(new String(passwordField.getPassword()))) {
                    openExamDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        frame.add(loginPanel);
        frame.setVisible(true);
    }

    private void openExamDashboard() {
        frame.getContentPane().removeAll();
        frame.setTitle("Exam Dashboard");
        frame.setLayout(new GridLayout(6, 1));

        // Display Timer
        JLabel timerLabel = new JLabel("Time Remaining: ");
        frame.add(timerLabel);

        JButton updateProfileButton = new JButton("Update Profile and Password");
        updateProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUpdateProfile();
            }
        });

        JButton startExamButton = new JButton("Start Exam");
        startExamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openExam();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLogoutConfirmation();
            }
        });

        frame.add(updateProfileButton);
        frame.add(startExamButton);
        frame.add(logoutButton);

        // Start the timer
        startTimer();

        frame.revalidate();
        frame.repaint();
    }

    private void openUpdateProfile() {
        frame.getContentPane().removeAll();
        frame.setTitle("Update Profile and Password");
        frame.setLayout(new GridLayout(5, 2));

        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(5, 2, 10, 10));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField newNameField = new JTextField();
        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the new profile information
                String newName = newNameField.getText();
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());

                // Update the profile and password (you should implement this logic)
                JOptionPane.showMessageDialog(frame, "Profile and password updated successfully!");

                // After updating, go back to the exam dashboard
                openExamDashboard();
            }
        });

        updatePanel.add(new JLabel("New Name:"));
        updatePanel.add(newNameField);
        updatePanel.add(new JLabel("New Username:"));
        updatePanel.add(newUsernameField);
        updatePanel.add(new JLabel("New Password:"));
        updatePanel.add(newPasswordField);
        updatePanel.add(new JLabel()); // Empty label for spacing
        updatePanel.add(new JLabel()); // Empty label for spacing
        updatePanel.add(new JLabel()); // Empty label for spacing
        updatePanel.add(updateButton);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openExamDashboard();
            }
        });

        frame.add(updatePanel);
        frame.add(backButton);

        frame.revalidate();
        frame.repaint();
    }

    private void openExam() {
        frame.getContentPane().removeAll();
        frame.setTitle("Online Exam");
        frame.setLayout(new GridLayout(6, 1));

        JButton submitButton = new JButton("Submit Exam");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showExamSubmissionConfirmation();
            }
        });

        // Add MCQ questions and answer options
        addMCQQuestion("Question 1: What is the capital of France?", new String[]{"A. Paris", "B. Berlin", "C. Madrid", "D. Rome"});
        addMCQQuestion("Question 2: What is the largest planet in our solar system?", new String[]{"A. Jupiter", "B. Mars", "C. Earth", "D. Venus"});
        addMCQQuestion("Question 3: Who wrote 'Romeo and Juliet'?", new String[]{"A. William Shakespeare", "B. Jane Austen", "C. Charles Dickens", "D. Mark Twain"});

        frame.add(submitButton);

        // Start the timer
        startTimer();

        frame.revalidate();
        frame.repaint();
    }

    private void addMCQQuestion(String question, String[] options) {
        JLabel questionLabel = new JLabel(question);
        frame.add(questionLabel);

        // Use ButtonGroup for exclusive selection (radio buttons)
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(1, 4)); // Display options in a single row

        // Add radio buttons for each option
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            buttonGroup.add(radioButton);
            optionsPanel.add(radioButton);
        }

        frame.add(optionsPanel);
    }

    private void showExamSubmissionConfirmation() {
        // Stop the timer
        stopTimer();

        if (!examSubmitted) {
            examSubmitted = true; // Set the flag to prevent repeated confirmation dialogs

            int result = JOptionPane.showConfirmDialog(frame, "Do you want to submit the exam?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // Logic to handle exam submission
                JOptionPane.showMessageDialog(frame, "Exam submitted successfully!");
                openExamDashboard();
            }
        }
    }

    private void showLogoutConfirmation() {
        // Stop the timer if the user logs out
        stopTimer();

        int result = JOptionPane.showConfirmDialog(frame, "Do you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            frame.getContentPane().removeAll();
            frame.setTitle("Login");
            frame.setLayout(new GridLayout(3, 2));

            // Creating a JPanel for login components
            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
            loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel usernameLabel = new JLabel("Username:");
            JLabel passwordLabel = new JLabel("Password:");
            usernameField = new JTextField();
            passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Logic to validate username and password
                    if ("admin".equals(usernameField.getText()) && "password".equals(new String(passwordField.getPassword()))) {
                        openExamDashboard();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password");
                    }
                }
            });

            loginPanel.add(usernameLabel);
            loginPanel.add(usernameField);
            loginPanel.add(passwordLabel);
            loginPanel.add(passwordField);
            loginPanel.add(new JLabel()); // Empty label for spacing
            loginPanel.add(loginButton);

            frame.add(loginPanel);
            frame.revalidate();
            frame.repaint();
        }
    }

    private void startTimer() {
        // Timer to update the time remaining every second
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    // Update the timer label or perform other actions as needed
                    frame.setTitle("Exam Dashboard - Time Remaining: " + formatTime(timeRemaining));
                } else {
                    // Time's up, auto-submit the exam
                    stopTimer();
                    showExamSubmissionConfirmation();
                }
            }
        });

        // Start the timer
        timer.start();
    }

    private void stopTimer() {
        // Stop the timer when needed 
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineExaminationSystem();
            }
        });
    }
}
