package Controller;

import Model.Room;

import java.util.*;

/**
 * 
 */
public class ProgressiveRoomGenerator implements IDungeonGenerator {

    /**
     * Default constructor
     */
    public ProgressiveRoomGenerator() {
    }

    /**
     * @param maxDepth 
     * @return
     */
    public Dungeon generateDungeon(int maxDepth) {
        Room[] rooms = new Room[5];
        return new Dungeon(rooms);
    }

}