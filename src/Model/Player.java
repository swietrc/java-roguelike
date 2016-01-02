package Model;

import Utils.Const;

/**
 * 
 */
public class Player extends Character {

    public Player(String name, Room r, int x, int y) {
        super(name, 0, Const.DEFAULT_STRENGTH);
        this.setCurrentRoom(r);
        this.setX(x);
        this.setY(y);
    }

    public void addGold(int gold) {
        this.setGold(this.getGold() + gold);
        System.out.println("GOLD: " + this.getGold());
    }

    public void addStrength(int strength) {
        int currentStrength = this.getStrength();
        this.setStrength(currentStrength + strength);
        System.out.println("POTION VAL: " + strength);
        System.out.println("STRENGTH: " + this.getStrength());
    }

    @Override
    public void move(Direction d) {
        super.move(d);
        System.out.println("MOVE TO: " + d.name());
        System.out.println("NEW POSITION: " + this.getX() + this.getY());
    }

    @Override
    public String getSprite() {

        return "@";
    }

    @Override
    public void trigger(Character c) {

    }
}