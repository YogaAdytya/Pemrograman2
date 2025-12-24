package main;

import controller.mahsiswacontroller;
import view.mahasiswaview;

public class main {
    public static void main(String[] args) {
        mahasiswaview view = new mahasiswaview();
        new mahsiswacontroller(view);
        view.setVisible(true);
    }
}
