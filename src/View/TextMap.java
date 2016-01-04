package View;

import Utils.Const;

import javax.swing.*;
import java.awt.*;

public class TextMap extends JPanel implements IMap {

    private JTextArea display = new JTextArea();
    public TextMap() {
        this.setFocusable(false);
        this.setBackground(Color.BLACK);
        this.add(display);
        init();
    }

    private void init() {
        display.setFont(Const.MAP_FONT);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.LIGHT_GRAY);
        display.setFocusable(false);
    }

    @Override
    public void drawFromString(String s) {
        // Splits the string every \n to get the width of the room.
        String res = s.split("[\r\n]", -1)[0];
        display.setColumns(res.length()+1);
        display.setText(s);
    }
}
