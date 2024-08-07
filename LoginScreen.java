import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 50, 80, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 50, 165, 25);
        panel.add(senhaField);

        loginButton = new JButton("Entrar");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(185, 80, 90, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new LoginButtonListener());
        registerButton.addActionListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                boolean validUser = false;
                while ((line = reader.readLine()) != null) {
                    String[] userDetails = line.split(",");
                    if (userDetails[5].equals(email) && userDetails[6].equals(senha)) {
                        validUser = true;
                        if (userDetails[8].equals("Professor")) {
                            ProfessorScreen professorScreen = new ProfessorScreen();
                            professorScreen.setVisible(true);
                        } else {
                            StudentScreen studentScreen = new StudentScreen();
                            studentScreen.setVisible(true);
                        }
                        dispose(); // Fechar a tela de login
                        break;
                    }
                }
                if (!validUser) {
                    JOptionPane.showMessageDialog(null, "Login inválido!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterScreen registerScreen = new RegisterScreen();
            registerScreen.setVisible(true);
            dispose(); // Fechar a tela de login
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}
