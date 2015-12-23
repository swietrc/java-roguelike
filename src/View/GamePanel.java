package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private JLabel topLabel = new JLabel("Roguelike");
    private JLabel gameInfo = new JLabel();
    private TextMap map = new TextMap();

    public GamePanel() {
        init();
        this.setFocusable(false);
        this.setLayout(new BorderLayout());
        this.add(this.topLabel, BorderLayout.NORTH);
        this.add(map, BorderLayout.CENTER);
        this.add(gameInfo, BorderLayout.SOUTH);
        updateInfo();
    }

    private void init() {
        this.topLabel.setHorizontalAlignment(JLabel.CENTER);
        this.topLabel.setFont(Utils.MENU_FONT);
        this.gameInfo.setFont(Utils.MENU_FONT);
    }

    public void refresh(String roomStr) {
        map.drawFromString(roomStr);
    }

    private void updateInfo() {
        this.gameInfo.setText("Gold: 0 | Monsters: 50 | Strength: 50");
    }
}
