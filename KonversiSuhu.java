import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KonversiSuhu extends JFrame implements ActionListener {
    private JTextField inputSuhu, outputSuhu;
    private JComboBox<String> dariBox, keBox;
    private JButton btnKonversi;

    public KonversiSuhu() {
        setTitle("Konversi Suhu");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        // label dan input
        add(new JLabel("Masukkan Suhu:"));
        inputSuhu = new JTextField();
        add(inputSuhu);

        add(new JLabel("Dari:"));
        String[] skala = {"Celsius", "Fahrenheit", "Kelvin"};
        dariBox = new JComboBox<>(skala);
        add(dariBox);

        add(new JLabel("Ke:"));
        keBox = new JComboBox<>(skala);
        add(keBox);

        btnKonversi = new JButton("Konversi");
        btnKonversi.addActionListener(this);
        add(btnKonversi);

        outputSuhu = new JTextField();
        outputSuhu.setEditable(false);
        add(outputSuhu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double suhu = Double.parseDouble(inputSuhu.getText());
            String dari = (String) dariBox.getSelectedItem();
            String ke = (String) keBox.getSelectedItem();
            double hasil = 0;

            // ubah ke Celsius dulu
            if (dari.equals("Fahrenheit")) suhu = (suhu - 32) * 5 / 9;
            else if (dari.equals("Kelvin")) suhu = suhu - 273.15;

            // ubah dari Celsius ke tujuan
            if (ke.equals("Celsius")) hasil = suhu;
            else if (ke.equals("Fahrenheit")) hasil = (suhu * 9 / 5) + 32;
            else if (ke.equals("Kelvin")) hasil = suhu + 273.15;

            outputSuhu.setText(String.format("%.2f", hasil));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KonversiSuhu().setVisible(true);
        });
    }
}
