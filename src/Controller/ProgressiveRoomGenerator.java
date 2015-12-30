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
     * @return
     */
    public Dungeon generateDungeon() {
        Room[] rooms = new Room[5];
        return new Dungeon(rooms);
    }

}