package View;

import Utils.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the panel shown when a game has started
 */
public class GamePanel extends JPanel {
    /** Title bar at the top of the screen */
    private JLabel topLabel = new JLabel("Roguelike");
    /** Status HUD at the bottom of the screen */
    private JLabel gameInfo = new JLabel();
    /** Main display showing the map with the position of the player (@) */
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
        this.topLabel.setFont(Const.MENU_FONT);
        this.gameInfo.setFont(Const.MENU_FONT);
        this.setFocusable(false);
        this.setLayout(new BorderLayout());
    }

    /**
     * Redraw the map
     * @param roomStr String containing the map
     */
    protected void refresh(String roomStr) {
        map.drawFromString(roomStr);
    }

    /**
     * redraws the hud
     * @param gold amount of gold of the player
     * @param strength amount of strength of the player
     */
    protected void setHUD(int gold, int strength) {
        this.gameInfo.setText("Gold: " + gold + " | Strength: " + strength);
    }
}
