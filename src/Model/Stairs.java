package Model;

import Controller.Game;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class Stairs extends Cell {

    private Room exitRoom;
    private int exitPosX;
    private int exitPosY;

    /**
     * Default constructor
     */
    public Stairs(int x, int y, Room exitRoom, int exitPosX, int exitPosY) {
        super(x, y);
        this.exitPosX = exitPosX;
        this.exitPosY = exitPosY;
        this.exitRoom = exitRoom;
    }

    private int direction;

    public void trigger(Character c) {
        if (JOptionPane.showConfirmDialog(null, "Do you really want to use the stairs ?", "warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            Cell exitCell = exitRoom.getCell(exitPosX, exitPosY);
            exitCell.setEntity(c);

            c.getCurrentCell().setEntity(null);
            c.setCurrentRoom(exitRoom);
            c.setCurrentCell(exitCell);
            Game.getInstance().setNotification("You took the stairs to level " + exitRoom.getLevel());
        }
    }

    public String toString() {
        return ">";
    }

}