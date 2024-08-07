import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class StudentScreen extends JFrame {
    private JList<String> scheduleList;

    public StudentScreen() {
        setTitle("Tela do Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Bem-vindo Aluno!");
        panel.add(label, BorderLayout.NORTH);

        scheduleList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(scheduleList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Selecionar Aula");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = scheduleList.getSelectedValue();
                if (selectedClass != null && !selectedClass.isEmpty()) {
                    String[] classInfo = selectedClass.split(" - ");
                    String subject = classInfo[0];
                    String time = classInfo[1];
                    String studentName = JOptionPane.showInputDialog("Digite seu nome:");
                    String studentPhoneNumber = JOptionPane.showInputDialog("Digite seu número de telefone:");

                    saveRequest("solicitacoes.txt", studentName, studentPhoneNumber, subject, time);
                    JOptionPane.showMessageDialog(null, "Solicitação enviada ao professor.");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma aula.");
                }
            }
        });
        panel.add(selectButton, BorderLayout.SOUTH);

        add(panel);

        loadSchedule("horarios.txt");
    }

    private void loadSchedule(String fileName) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                listModel.addElement(line);
            }
            scheduleList.setModel(listModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRequest(String fileName, String studentName, String studentPhoneNumber, String subject, String time) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("Aluno: " + studentName + " | Telefone: " + studentPhoneNumber + " | Aula: " + subject + " - " + time);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentScreen().setVisible(true);
            }
        });
    }
}
