package Model;

/**
 * 
 */
public class Cell {

    private char sprite = '.';

    private boolean visited = false;

    private Entity entity;

    private final int x;

    private final int y;

    /**
     * Default constructor
     * @param x X Position on the map
     * @param y Y Position on the map
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Entity e) {
        this(x, y);
        this.entity = e;
    }

    /**
     * Checks whether entity is empty or not
     * @return Boolean
     */
    public Boolean isEmpty() {
        return this.entity == null;
    }

    public void trigger(Character c) {
        this.visited = true;
        if (!isEmpty())
            this.entity.trigger(c);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return String.valueOf(this.sprite);

        return entity.toString();
    }

}