package Controller;

import Model.Room;

import java.util.*;

/**
 * 
 */
public class BasicDungeonGenerator implements IDungeonGenerator {

    public int MAX_POTIONS;
    public int MAX_TREASURES;
    public int MIN_POTIONS;
    public int MIN_TREASURES;
    public float COEF_ENTITY_SPAWN;
    public float COEF_MONSTER;
    public float COEF_POTION;
    public float COEF_TREASURE;

    /**
     * @param maxDepth 
     * @return
     */
    public Dungeon generateDungeon(int maxDepth) {
        Room[] rooms = new Room[5];
        return new Dungeon(rooms);
    }
}