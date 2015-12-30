package Model;

/**
 * 
 */
public class Potion extends Entity {

    private int strengthModifier;

    /**
     * Default constructor
     */
    public Potion(int strengthModifier) {
        super('P');
        this.strengthModifier = strengthModifier;
    }

    public void trigger(Character c) {
        if (c instanceof Player) {
            ((Player) c).addStrength(this.strengthModifier);
        }
    }

}