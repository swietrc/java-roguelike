package Model;

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
        double probWin = c.getStrength() / (this.getStrength() + c.getStrength());
        double combatResult = Math.random();
        if (combatResult < probWin) { // Character dies
            System.out.println("Monster has " + this.getStrength() + " strength");
            System.out.println(this.getName() + " killed you");
            c.setAlive(false);
        } else { // Monster dies
            this.setAlive(false);
            System.out.println("Monster has " + this.getStrength() + " strength");
            System.out.println("You killed a " + this.getName());
        }
    }

    public String getSprite () {
        //TODO: REMOVE THIS
        return "E";
    }
}