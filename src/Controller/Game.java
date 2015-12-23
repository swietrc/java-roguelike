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
    private boolean running;

    private Player player;
    private GamePanel display;

    private IDungeonGenerator dungeonGenerator = new ProgressiveRoomGenerator();

    /**
     * Default constructor
     */
    public Game() {
        Room r = new Room(5, 5, -1);
        this.player = new Player("Simon", r, 2, 2);
        r.getCell(2, 2).setEntity(player);

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
        display.refresh(player.getCurrentRoom().toString());
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

        System.out.println("X: "+newX+"\nY: "+newY);

        Cell currentCell = c.getCurrentRoom().getCell(c.getX(), c.getY());
        Cell newCell = c.getCurrentRoom().getCell(newX, newY);

        if (newCell != currentCell) {
            newCell.trigger(c);
            newCell.setEntity(c);
            currentCell.setEntity(null);
            c.move(d);
        }
    }

    public static Game getInstance() {
        if (Game.instance == null)
            instance = new Game();
        return instance;
    }
}