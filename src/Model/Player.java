package Model;

import Utils.Utils;

/**
 * 
 */
public class Player extends Character {

    public Player(String name, Room r, int x, int y) {
        super(name, 0, Utils.DEFAULT_STRENGTH);
        this.setCurrentRoom(r);
        this.setX(x);
        this.setY(y);
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