package Controller;

import Model.*;
import Model.Character;
import Utils.Const;
import View.GameFrame;
import View.GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * 
 */
public class Game {

    private static GameFrame frame;
    private static Game instance;
    private ConfigurationHolder cfg = new ConfigurationHolder(0, Const.DEFAULT_GENERATOR, Const.DEFAULT_DEPTH);

    private boolean DEAD;

    private Player player;
    private IDungeonGenerator dungeonGenerator;

    /**
     * Default constructor
     */
    public Game() {
        frame = new GameFrame();
        showTitleScreen();
    }

    public void newGame() {
        this.dungeonGenerator = new BasicDungeonGenerator();
        Dungeon d = dungeonGenerator.generateDungeon();
        Room[] rooms = d.getRooms();
        this.player = new Player("Simon", rooms[0], 0, 0);
        rooms[0].getCell(0, 0).setEntity(player);

        frame.showGame();
        frame.refresh(player.getCurrentRoom().toString());
    }

    public void showTitleScreen() {
        frame.showMenu();
    }

    /**
     * Main Game Loop
     */
    /*
    public void run() {
        System.out.println(player.getCurrentRoom().toString());
        frame.showGame();
    }
    */

    public void keyPressed(KeyEvent e) {

        // handle key pressed event
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveCharacter(player, Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                moveCharacter(player, Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                moveCharacter(player, Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                moveCharacter(player, Direction.EAST);
                break;
        }

        // update display
        frame.refresh(player.getCurrentRoom().toString());
        frame.setHUD(player.getGold(), player.getStrength());
    }

    private void moveCharacter(Character c, Direction d) {
        // Checks if player has died
        if (!DEAD) {
            int newX = c.getX();
            int newY = c.getY();

            switch (d) {
                case NORTH:
                    if (newY > 0) newY--;
                    break;
                case EAST:
                    if (newX < c.getCurrentRoom().getWidth() - 1) newX++;
                    break;
                case SOUTH:
                    if (newY < c.getCurrentRoom().getHeight() - 1) newY++;
                    break;
                case WEST:
                    if (newX > 0) newX--;
                    break;
            }

            Cell currentCell = c.getCurrentCell();
            Cell newCell = c.getCurrentRoom().getCell(newX, newY);

            if (newCell != currentCell) {
                newCell.trigger(c);
            }

            DEAD = c.isAlive();
            if (DEAD) {
                JOptionPane.showConfirmDialog(null, "You died! You had " + this.player.getGold() + " gold!", "warning", JOptionPane.OK_OPTION);
                this.showTitleScreen();
            }
        }
    }

    public static Game getInstance() {
        if (Game.instance == null)
            instance = new Game();
        return instance;
    }

    public void showOptions() {
        frame.showOptions();
    }

    public void quit() {
        frame.dispose();
        System.exit(0);
    }

    public void setConfig(ConfigurationHolder c) {
        this.cfg = c;
    }
}