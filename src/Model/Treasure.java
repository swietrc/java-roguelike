package Model;

/**
 * 
 */
public class Treasure extends Entity {

    private int gold;

    public Treasure(int gold) {
        super('$');
        this.gold = gold;
    }

    public void trigger(Character c) {
        if (c instanceof Player) {
            ((Player) c).addGold(this.gold);
        }
    }

}