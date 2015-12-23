package Controller;

import Model.Room;

import java.util.*;

/**
 * 
 */
public class BasicDungeonGenerator implements IDungeonGenerator {

    public int MAX_POTIONS = 50;
    public int MAX_TREASURES = 50;
    public int MIN_POTIONS = 20;
    public int MIN_TREASURES = 20;

    public float COEF_MONSTER = 0.05f;
    public float COEF_POTION = 0.025f;
    public float COEF_TREASURE = 0.05f;

    public int MIN_DEPTH = 3;
    public int MAX_DEPTH = 8;

    private ArrayList<Room> rooms;

    /**
     * generates a basic dungeon
     * @return basic dungeon
     */
    public Dungeon generateDungeon() {
        // Random number generator
        Random rand = new Random();

        /*
        // Generating random depth between MIN_DEPTH included and MAX_DEPTH excluded
        int depth = rand.nextInt(MAX_DEPTH - MIN_DEPTH) + MIN_DEPTH;

        int nbRooms = rand.nextInt(Math.round(depth * 1.4f) - depth) + depth;
        */

        System.out.println(depth);
        System.out.println(nbRooms);

        Room[] rooms = new Room[5];
        return new Dungeon(rooms);
    }

    /**
     * Generates a random room
     * @return
     */
    private Room generateRoom() {

    }
}