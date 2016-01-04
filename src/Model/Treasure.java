package Model;

import Controller.Game;

/**
 * Gives gold to the player who triggers it
 */
public class Treasure extends Entity {
    /** amount of gold stored by the treasure */
    private int gold;

    public Treasure(int gold) {
        super('$');
        this.gold = gold;
    }

    /**
     * gives gold to the player who walks on it
     * @param c
     */
    public void trigger(Character c) {
        if (c instanceof Player) {
            ((Player) c).addGold(this.gold);
            Game.getInstance().setNotification("You picked up " + this.gold + " gold!");
            System.out.println(" has " + this.gold + " strength");
        }
    }

}