package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Map extends JTextArea {

    public Map() {
        super();
        this.setSize(Utils.WIDTH, Utils.HEIGHT);
        this.setBackground(Color.BLACK);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        System.out.println(this.getRows());
        System.out.println(this.getColumns());
    }
}
