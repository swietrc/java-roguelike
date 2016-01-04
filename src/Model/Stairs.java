package Model;

import Controller.Game;

import javax.swing.*;

/**
 * Transports the player to another room on another stairs at post exitPosX, exitPosY
 */
public class Stairs extends Cell {

    private Room exitRoom;
    private int exitPosX;
    private int exitPosY;
    int position;

    /**
     * Constructor for stairs
     * @param x X position of the stairs
     * @param y Y position of the stairs
     * @param exitRoom room connected to the stairs
     * @param exitPosX X position of the stairs in the exit room
     * @param exitPosY Y position of the stairs in the exit room
     * @param position toString returns < if 1 and > if -1
     */
    public Stairs(int x, int y, Room exitRoom, int exitPosX, int exitPosY, int position) {
        super(x, y);
        this.exitPosX = exitPosX;
        this.exitPosY = exitPosY;
        this.exitRoom = exitRoom;
        this.position = position;
    }

    /**
     * Moves the character C to another room (this.exitRoom)
     * @param c character to be moved
     */
    public void trigger(Character c) {
        this.setVisible(true);
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
        if (isVisible()) {
            if (!isEmpty() && (getEntity() instanceof Player))
                return super.toString();
            if (position == 1)
                return "<";
            return ">";
        } else return " ";
    }
}