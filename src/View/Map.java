package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Map extends JTextArea {

    public Map() {
        super(20, 20);
        init();
        this.setBackground(Color.BLACK);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        this.setForeground(Color.WHITE);
    }

    private void init() {
        String str = "";
        //this.setEditable(false);
        this.setSize(50, 50);
        for (int i = 0; i < (80 / 2) - 20; i++) {
            str += " ";
        }
        for (int i = 0; i < 10; i++) {
            str += ".";
        }
        str += "@";
        for (int i = 0; i < 9; i++) {
            str += ".";
        }
        this.setText(str);
    }
}
