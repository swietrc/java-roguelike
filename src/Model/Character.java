package Model;

/**
 * 
 */
public abstract class Character extends Entity {
    // TODO: add way for character to move using a cell + a room
    private int gold;
    private int strength;

    /** Stores current state of the character (true if alive, false if dead) */
    private boolean alive;

    /** Stores name of the character */
    private String name;

    /** Stores the current room for the character */
    private Room currentRoom;

    /** Stores current X position inside the room currentRoom */
    private int x;
    /** Stores current Y position inside the room currentRoom */
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

    /**
     * Getter for the current room
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for current room
     * @param currentRoom room the character will be in
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Getter for current Cell
     * @return Cell the character is on
     */
    public Cell getCurrentCell() {
        return this.currentRoom.getCell(this.getX(), this.getY());
    }

    /**
     * Sets position of the character to this cell
     * @param c Cell
     */
    public void setCurrentCell(Cell c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    /**
     * Getter for alive
     * @return boolean
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * Setter for alive
     * @param alive boolean
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @return state of the character (true if alive, false if dead)
     */
    public boolean isAlive() {
        return this.getAlive();
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name name of the character
     */
    public void setName(String name) {
        this.name = name;
    }
}