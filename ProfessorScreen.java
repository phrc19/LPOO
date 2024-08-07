import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ProfessorScreen extends JFrame {
    private JTextArea sche duledClassesTextArea;

    public ProfessorScreen() {
        setTitle("Tela do Professor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo Professor!");
        panel.add(welcomeLabel, BorderLayout.NORTH);

        scheduledClassesTextArea = new JTextArea();
        scheduledClassesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduledClassesTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton registerButton = new JButton("Cadastrar Aulas");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para cadastrar aulas
                JOptionPane.showMessageDialog(null, "Ação ainda não implementada: Cadastrar Aulas");
            }
        });
        panel.add(registerButton, BorderLayout.WEST);

        JButton viewButton = new JButton("Visualizar Aulas Marcadas pelos Alunos");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para visualizar aulas marcadas pelos alunos
                refreshScheduledClasses();
            }
        });
        panel.add(viewButton, BorderLayout.EAST);

        add(panel);
    }

    private void refreshScheduledClasses() {
        scheduledClassesTextArea.setText("");
        ArrayList<String> scheduledClasses = loadScheduledClasses("aulas_marcadas.txt");
        for (String scheduledClass : scheduledClasses) {
            scheduledClassesTextArea.append(scheduledClass + "\n");
        }
    }

    private ArrayList<String> loadScheduledClasses(String fileName) {
        ArrayList<String> scheduledClasses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                scheduledClasses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduledClasses;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfessorScreen().setVisible(true);
            }
        });
    }
}
