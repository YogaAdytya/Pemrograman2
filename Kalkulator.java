import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class p1 extends JFrame implements ActionListener {
    // komponen
    private JTextField layar;
    private double angka1 = 0, angka2 = 0, hasil = 0;
    private char operator;

    public p1() {
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // layar atas
        layar = new JTextField();
        layar.setHorizontalAlignment(JTextField.RIGHT);
        layar.setEditable(false);
        layar.setFont(new Font("Arial", Font.BOLD, 24));
        add(layar, BorderLayout.NORTH);

        // panel tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] tombol = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : tombol) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            layar.setText(layar.getText() + cmd);
        } else if (cmd.charAt(0) == 'C') {
            layar.setText("");
            angka1 = angka2 = hasil = 0;
        } else if (cmd.charAt(0) == '=') {
            angka2 = Double.parseDouble(layar.getText());
            switch (operator) {
                case '+':
                    hasil = angka1 + angka2;
                    break;
                case '-':
                    hasil = angka1 - angka2;
                    break;
                case '*':
                    hasil = angka1 * angka2;
                    break;
                case '/':
                    hasil = angka2 != 0 ? angka1 / angka2 : 0;
                    break;
            }
            layar.setText("" + hasil);
        } else { // operator
            angka1 = Double.parseDouble(layar.getText());
            operator = cmd.charAt(0);
            layar.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new p1().setVisible(true);
        });
    }
}
