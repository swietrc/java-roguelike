package Model;

/**
 * 
 */
public abstract class Character extends Entity {
    // TODO: add way for character to move using a cell + a room
    private int gold;
    private int strength;

    private boolean alive;

    private String name;

    private Room currentRoom;

    private int x;
    private int y;

    /**
     * Default constructor
     */
    public Character(String name, int gold, int strength) {
        super();
        this.name = name;
        this.gold = gold;
        this.strength = strength;
    }

    @Deprecated
    public void move(Direction d) {
        switch (d) {
            case NORTH:
                if (y > 0) y--;
                break;
            case EAST:
                if (x < this.getCurrentRoom().getWidth() - 1) x++;
                break;
            case SOUTH:
                if (y < this.getCurrentRoom().getHeight() - 1) y++;
                break;
            case WEST:
                if (x > 0) x--;
                break;
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Cell getCurrentCell() {
        return this.currentRoom.getCell(this.getX(), this.getY());
    }

    public void setCurrentCell(Cell c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.getAlive();
    }
}