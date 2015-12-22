package Model;

import java.util.*;

import static Model.Direction.*;

/**
 * 
 */
public abstract class Character extends Entity {

    private int gold;
    private int strength;

    private int x;
    private int y;

    /**
     * Default constructor
     */
    public Character() {
        super();
    }

    public void move(Direction d) {
        switch (d) {
            case NORTH:

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
}