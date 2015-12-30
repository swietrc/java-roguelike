package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the panel shown when a game has started
 */
public class GamePanel extends JPanel {
    private JLabel topLabel = new JLabel("Roguelike");
    private JLabel gameInfo = new JLabel();
    private TextMap map = new TextMap();

    public GamePanel() {
        init();
        this.add(this.topLabel, BorderLayout.NORTH);
        this.add(map, BorderLayout.CENTER);
        this.add(gameInfo, BorderLayout.SOUTH);
        setHUD(0, 0);
    }

    private void init() {
        this.topLabel.setHorizontalAlignment(JLabel.CENTER);
        this.topLabel.setFont(Utils.MENU_FONT);
        this.gameInfo.setFont(Utils.MENU_FONT);
        this.setFocusable(false);
        this.setLayout(new BorderLayout());
    }

    public void refresh(String roomStr) {
        map.drawFromString(roomStr);
    }

    public void setHUD(int gold, int strength) {
        this.gameInfo.setText("Gold: " + gold + " | Strength: " + strength);
    }
}
