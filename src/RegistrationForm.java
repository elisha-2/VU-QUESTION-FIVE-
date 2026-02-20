import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistrationForm extends JFrame {

    private JTextField txtRegID, txtName, txtFaculty, txtProject, txtContact, txtEmail;
    private JButton btnRegister, btnClear, btnExit;

    public RegistrationForm() {
        setTitle("VUE Exhibition Registration");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Registration ID:"));
        txtRegID = new JTextField();
        panel.add(txtRegID);

        panel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Faculty:"));
        txtFaculty = new JTextField();
        panel.add(txtFaculty);

        panel.add(new JLabel("Project Title:"));
        txtProject = new JTextField();
        panel.add(txtProject);

        panel.add(new JLabel("Contact Number:"));
        txtContact = new JTextField();
        panel.add(txtContact);

        panel.add(new JLabel("Email Address:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        btnRegister = new JButton("Register");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        panel.add(btnRegister);
        panel.add(btnClear);
        panel.add(btnExit);

        add(panel, BorderLayout.CENTER);

        // Register Button Action
        btnRegister.addActionListener(e -> registerStudent());

        // Clear Button Action
        btnClear.addActionListener(e -> clearFields());

        // Exit Button Action
        btnExit.addActionListener(e -> System.exit(0));
    }

    // Database Connection Method
private Connection connect() {
    try {
        String url = "jdbc:ucanaccess://C:\\Users\\Elisha\\Desktop\\VU-OOP-Exams\\PerfectNumberApp\\VUE_Exhibition.accdb";
        return DriverManager.getConnection(url);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Database Connection Failed:\n" + e.getMessage(), 
            "Connection Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null;
    }
}

    // Insert Data Method
    private void registerStudent() {
        try (Connection conn = connect()) {

            if (conn == null) return;

            String sql = "INSERT INTO Participants "
                    + "(RegistrationID, StudentName, Faculty, ProjectTitle, ContactNumber, EmailAddress) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtRegID.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3, txtFaculty.getText());
            pst.setString(4, txtProject.getText());
            pst.setString(5, txtContact.getText());
            pst.setString(6, txtEmail.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registration Successful");
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Saving Data");
            e.printStackTrace();
        }
    }

    // Clear Fields Method
    private void clearFields() {
        txtRegID.setText("");
        txtName.setText("");
        txtFaculty.setText("");
        txtProject.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationForm().setVisible(true);
        });
    }
}
