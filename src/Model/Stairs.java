package Model;

/**
 * 
 */
public class Stairs extends Cell {

    private Room exitRoom;

    /**
     * Default constructor
     */
    public Stairs(int x, int y, Room exitRoom) {
        super(x, y);
        this.exitRoom = exitRoom;
    }

    private int direction;

    public void trigger(Character c) {
        exitRoom.getCell(this.getX(), this.getY()).setEntity(c);
        c.getCurrentRoom().getCell(c.getX(), c.getY()).setEntity(null);
        c.setCurrentRoom(r2);
    }

}