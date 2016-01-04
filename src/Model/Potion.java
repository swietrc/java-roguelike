package Model;

import Controller.Game;

/**
 * represents a potion in the game
 */
public class Potion extends Entity {

    private int strengthModifier;

    /**
     * Default constructor
     * @param strengthModifier value of the potion
     */
    public Potion(int strengthModifier) {
        super('P');
        this.strengthModifier = strengthModifier;
    }

    /**
     * gives strengthmodifier amount of strength to the player
     * @param c
     */
    public void trigger(Character c) {
        if (c instanceof Player) {
            ((Player) c).addStrength(this.strengthModifier);
            Game.getInstance().setNotification("You have gained " + this.strengthModifier + " strength by picking up a potion");
        }
    }

}