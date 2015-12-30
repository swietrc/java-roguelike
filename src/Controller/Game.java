package Controller;

import Model.*;
import Model.Character;
import View.GameFrame;
import View.GamePanel;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * 
 */
public class Game {

    private static Game instance;
    private boolean DEAD;

    private Player player;
    private GamePanel display;

    private IDungeonGenerator dungeonGenerator;

    /**
     * Default constructor
     */
    public Game() {
        this.dungeonGenerator = new BasicDungeonGenerator();
        Dungeon d = dungeonGenerator.generateDungeon();
        Room[] rooms = d.getRooms();
        this.player = new Player("Simon", rooms[0], 0, 0);
        rooms[0].getCell(0, 0).setEntity(player);
    }

    /**
     * Main Game Loop
     */
    public void run() {
        GameFrame gameFrame = new View.GameFrame();
        display = (GamePanel) gameFrame.getContentPane();
        display.refresh(player.getCurrentRoom().toString());
    }

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
        display.refresh(player.getCurrentRoom().toString());
        display.setHUD(player.getGold(), player.getStrength());
    }

    private void moveCharacter(Character c, Direction d) {

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
    }

    public static Game getInstance() {
        if (Game.instance == null)
            instance = new Game();
        return instance;
    }
}