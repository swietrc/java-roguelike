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

    public int MAX_ROOM_PER_LEVEL = 5;
    public int MIN_ROOM_PER_LEVEL = 1;

    public int MIN_DEPTH = 3;
    public int MAX_DEPTH = 8;

    private ArrayList<Room> rooms = new ArrayList<>();

    private Random randomGenerator;

    public BasicDungeonGenerator(int seed) {
        randomGenerator = new Random(seed);
    }

    public BasicDungeonGenerator() {
        randomGenerator = new Random();
    }

    /**
     * generates a basic dungeon
     * @return basic dungeon
     */
    public Dungeon generateDungeon() {

        // Generating random depth between MIN_DEPTH included and MAX_DEPTH excluded
        int depth = randomGenerator.nextInt(MAX_DEPTH - MIN_DEPTH) + MIN_DEPTH;

        for (int i = 0 ; i < depth ; i++) {

        }

        Room[] rooms = new Room[5];
        return new Dungeon(rooms);
    }

    /**
     * Generates a random room
     * @return
     */
    private Room generateRoom() {
        return null;
    }
}