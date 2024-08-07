import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterScreen extends JFrame {
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JTextField dobField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JPasswordField confirmarSenhaField;
    private JComboBox<String> nivelEscolaridadeField;
    private JComboBox<String> tipoUsuarioField;
    private JButton registerButton;
    private JButton backButton;

    public RegisterScreen() {
        setTitle("Registrar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 120, 25);
        panel.add(nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBounds(150, 20, 200, 25);
        panel.add(nomeField);

        JLabel sobrenomeLabel = new JLabel("Sobrenome:");
        sobrenomeLabel.setBounds(10, 50, 120, 25);
        panel.add(sobrenomeLabel);

        sobrenomeField = new JTextField(20);
        sobrenomeField.setBounds(150, 50, 200, 25);
        panel.add(sobrenomeField);

        JLabel dobLabel = new JLabel("Data de Nascimento:");
        dobLabel.setBounds(10, 80, 120, 25);
        panel.add(dobLabel);

        dobField = new JTextField(20);
        dobField.setBounds(150, 80, 200, 25);
        panel.add(dobField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 110, 120, 25);
        panel.add(cpfLabel);

        cpfField = new JTextField(20);
        cpfField.setBounds(150, 110, 200, 25);
        panel.add(cpfField);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 140, 120, 25);
        panel.add(telefoneLabel);

        telefoneField = new JTextField(20);
        telefoneField.setBounds(150, 140, 200, 25);
        panel.add(telefoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 170, 120, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(150, 170, 200, 25);
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 200, 120, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(150, 200, 200, 25);
        panel.add(senhaField);

        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha:");
        confirmarSenhaLabel.setBounds(10, 230, 120, 25);
        panel.add(confirmarSenhaLabel);

        confirmarSenhaField = new JPasswordField(20);
        confirmarSenhaField.setBounds(150, 230, 200, 25);
        panel.add(confirmarSenhaField);

        JLabel nivelEscolaridadeLabel = new JLabel("Nível de Escolaridade:");
        nivelEscolaridadeLabel.setBounds(10, 260, 120, 25);
        panel.add(nivelEscolaridadeLabel);

        String[] niveisEscolaridade = {"Ensino fundamental", "Ensino Médio", "Faculdade", "Mestrado", "Doutorado"};
        nivelEscolaridadeField = new JComboBox<>(niveisEscolaridade);
        nivelEscolaridadeField.setBounds(150, 260, 200, 25);
        panel.add(nivelEscolaridadeField);

        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuário:");
        tipoUsuarioLabel.setBounds(10, 290, 120, 25);
        panel.add(tipoUsuarioLabel);

        String[] tiposUsuario = {"Professor", "Aluno"};
        tipoUsuarioField = new JComboBox<>(tiposUsuario);
        tipoUsuarioField.setBounds(150, 290, 200, 25);
        panel.add(tipoUsuarioField);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 320, 120, 25);
        panel.add(registerButton);

        backButton = new JButton("Voltar");
        backButton.setBounds(230, 320, 120, 25);
        panel.add(backButton);

        registerButton.addActionListener(new RegisterButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            String dob = dobField.getText();
            String cpf = cpfField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());
            String confirmarSenha = new String(confirmarSenhaField.getPassword());
            String nivelEscolaridade = (String) nivelEscolaridadeField.getSelectedItem();
            String tipoUsuario = (String) tipoUsuarioField.getSelectedItem();

            if (!senha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(null, "Senhas não coincidem!");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(nome + "," + sobrenome + "," + dob + "," + cpf + "," + telefone + "," + email + "," + senha + "," + nivelEscolaridade + "," + tipoUsuario);
                writer.newLine();
                writer.flush();
                JOptionPane.showMessageDialog(null, "Usuário " + nome + " registrado com sucesso!");

                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose(); // Fechar a tela de registro
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            dispose(); // Fechar a tela de registro
        }
    }
}