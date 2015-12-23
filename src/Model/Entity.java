package Model;

public abstract class Entity {

    private char sprite;

    /**
     * Default constructor
     */
    public Entity(char sprite) {
        this.sprite = sprite;
    }

    public Entity() {}

    public abstract void trigger(Character c);

    public String toString() {
        return this.getSprite();
    }

    public String getSprite() {
        return String.valueOf(sprite);
    }
}