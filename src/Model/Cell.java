package Model;

/**
 * Represents Cell in-game
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

        c.getCurrentCell().setEntity(null);
        c.setCurrentCell(this);
        this.setEntity(c);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
         if (visited) {
            if (isEmpty())
                return String.valueOf(this.sprite);

            return entity.toString();
         } else return " ";
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public boolean isVisible() {
        return visited;
    }

    public void setVisible(boolean visible) {
        this.visited = visible;
    }
}