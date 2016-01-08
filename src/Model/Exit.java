package Model;

import Controller.Game;

/**
 * Exit of the dungeon
 */
public class Exit extends Cell {

    public Exit(int x, int y) {
        super(x, y);
    }

    /**
     * Triggers the end of the game by victory
     * @param c
     */
    @Override
    public void trigger(Character c) {
        this.setVisible(true);
        Game.getInstance().win();
    }

    public String toString() {
        if (isVisible()) return "O";
        else return " ";
    }
}
