package Model;

public abstract class Entity {

    private char sprite;

    private boolean alive;

    /**
     * Default constructor
     */
    public Entity(char sprite) {
        super();
        this.sprite = sprite;
    }

    public Entity() {
        this.sprite = '.';
        alive = true;
    }

    public abstract void trigger(Character c);

    public String toString() {
        return this.getSprite();
    }

    public String getSprite() {
        return String.valueOf(sprite);
    }
}