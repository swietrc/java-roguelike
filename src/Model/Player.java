package Model;

/**
 * 
 */
public class Player extends Character {


    private String name;

    public Player(String name, Room r, int x, int y) {
        this.name = name;
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