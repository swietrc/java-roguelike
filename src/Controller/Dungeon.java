package Controller;

import Model.Room;

import java.util.*;

/**
 * 
 */
public class Dungeon {

    private Room[] rooms;

    public Dungeon(Room[] rooms) {
        this.rooms = rooms;
    }

    public Room[] getRooms() {
        return rooms;
    }
}