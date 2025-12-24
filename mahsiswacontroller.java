package controller;

import model.mahasiswa;
import util.koneksidb;
import view.mahasiswaview;

import javax.swing.*;
import java.sql.*;

public class mahsiswacontroller {

    mahasiswaview view;

    public mahsiswacontroller(mahasiswaview view) {
        this.view = view;

        tampilData();

        view.btnTambah.addActionListener(e -> tambah());
        view.btnUbah.addActionListener(e -> ubah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnCari.addActionListener(e -> cari());
    }

    void tampilData() {
        view.model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa";
            Statement st = koneksidb.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                view.model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void tambah() {
        if (view.txtNama.getText().isEmpty() || view.txtNim.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        try {
            // cek NIM
            String cek = "SELECT nim FROM mahasiswa WHERE nim=?";
            PreparedStatement psCek = koneksidb.getKoneksi().prepareStatement(cek);
            psCek.setString(1, view.txtNim.getText());
            if (psCek.executeQuery().next()) {
                JOptionPane.showMessageDialog(view, "NIM sudah terdaftar!");
                return;
            }

            String sql = "INSERT INTO mahasiswa VALUES (NULL, ?, ?, ?)";
            PreparedStatement ps = koneksidb.getKoneksi().prepareStatement(sql);
            ps.setString(1, view.txtNama.getText());
            ps.setString(2, view.txtNim.getText());
            ps.setString(3, view.txtJurusan.getText());
            ps.executeUpdate();

            tampilData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void ubah() {
        int row = view.table.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(view.model.getValueAt(row,0).toString());

        try {
            String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?";
            PreparedStatement ps = koneksidb.getKoneksi().prepareStatement(sql);
            ps.setString(1, view.txtNama.getText());
            ps.setString(2, view.txtNim.getText());
            ps.setString(3, view.txtJurusan.getText());
            ps.setInt(4, id);
            ps.executeUpdate();

            tampilData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void hapus() {
        int row = view.table.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(view.model.getValueAt(row,0).toString());

        try {
            String sql = "DELETE FROM mahasiswa WHERE id=?";
            PreparedStatement ps = koneksidb.getKoneksi().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            tampilData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void cari() {
        view.model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement ps = koneksidb.getKoneksi().prepareStatement(sql);
            ps.setString(1, "%" + view.txtCari.getText() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                view.model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}