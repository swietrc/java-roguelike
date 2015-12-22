package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextMap extends JPanel implements IMap {

    private JTextArea display = new JTextArea();
    public TextMap() {
        this.setBackground(Color.BLACK);
        this.add(display);
        init();

        // Display test to be removed
        String str = "###########\n#..@......#\n#...>.....#\n#......$..#\n###########";
        drawFromString(str);
    }

    private void init() {
        display.setFont(Utils.MAP_FONT);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.LIGHT_GRAY);
    }

    @Override
    public void drawFromString(String s) {
        // Splits the string every \n to get the width of the room.
        String res = s.split("[\r\n]", -1)[0];
        display.setColumns(res.length());
        display.setText(s);
    }
}
