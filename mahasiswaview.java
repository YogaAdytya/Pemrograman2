package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class mahasiswaview extends JFrame {

    public JTextField txtNama = new JTextField();
    public JTextField txtNim = new JTextField();
    public JTextField txtJurusan = new JTextField();
    public JTextField txtCari = new JTextField();

    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUbah = new JButton("Ubah");
    public JButton btnHapus = new JButton("Hapus");
    public JButton btnCari = new JButton("Cari");

    public JTable table = new JTable();
    public DefaultTableModel model;

    public mahasiswaview() {
        setTitle("Data Mahasiswa");
        setSize(650, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // ===== LABEL =====
        JLabel lblNama = new JLabel("Nama");
        JLabel lblNim = new JLabel("NIM");
        JLabel lblJurusan = new JLabel("Jurusan");

        lblNama.setBounds(20, 20, 80, 25);
        lblNim.setBounds(20, 50, 80, 25);
        lblJurusan.setBounds(20, 80, 80, 25);

        // ===== TEXTFIELD =====
        txtNama.setBounds(100, 20, 200, 25);
        txtNim.setBounds(100, 50, 200, 25);
        txtJurusan.setBounds(100, 80, 200, 25);

        // ===== BUTTON CRUD (HORIZONTAL SESUAI MODUL) =====
        btnTambah.setBounds(20, 120, 100, 25);
        btnUbah.setBounds(130, 120, 100, 25);
        btnHapus.setBounds(240, 120, 100, 25);

        // ===== CARI =====
        txtCari.setBounds(360, 120, 180, 25);
        btnCari.setBounds(550, 120, 70, 25);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"ID", "Nama", "NIM", "Jurusan"}, 0);
        table.setModel(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 160, 600, 200);

        // ===== ADD =====
        add(lblNama);
        add(lblNim);
        add(lblJurusan);

        add(txtNama);
        add(txtNim);
        add(txtJurusan);

        add(btnTambah);
        add(btnUbah);
        add(btnHapus);

        add(txtCari);
        add(btnCari);

        add(sp);
    }
}