package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel topLabel = new JLabel("Roguelike");

    public GamePanel() {
        init();
        this.setLayout(new BorderLayout());
        this.add(topLabel, BorderLayout.NORTH);
        this.add(new Map());
    }

    private void init() {
        topLabel.setHorizontalAlignment(JLabel.CENTER);
    }
}
