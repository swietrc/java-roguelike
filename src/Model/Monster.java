package Model;

import Controller.Game;

/**
 * 
 */
public class Monster extends Character {

    /**
     * Default constructor
     */
    public Monster(String name, int strength, int gold) {
        super(name, gold, strength);
    }

    /**
     * Triggers a combat between a character and the current monster.
     * @param c Character with whom to fight
     */
    public void trigger(Character c) {
        double probWin = ((double) c.getStrength() / ((double)this.getStrength() + (double)c.getStrength()));
        double combatResult = Math.random();
        if (combatResult > probWin) { // Character dies
            Game.getInstance().setNotification("You were killed by a " + getName() + " with " + getStrength() + " strength.");
            c.setAlive(false);
        } else { // Monster dies
            this.setAlive(false);
            if (c instanceof  Player) ((Player)c).addGold(this.getGold());
            Game.getInstance().setNotification("You killed a " + getName() + " with " + getStrength() + " strength. he dropped " + getGold() + "Gold");
        }
    }

    public String getSprite () {
        return "E";
    }
}