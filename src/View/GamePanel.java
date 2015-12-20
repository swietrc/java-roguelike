package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel topLabel = new JLabel("Roguelike");

    public GamePanel() {
        init();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.topLabel);
        this.add(new Map());
    }

    private void init() {
        this.topLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.topLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, Utils.TEXTSIZE));
    }
}
