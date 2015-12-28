package Controller;

import Model.Cell;
import Model.Room;
import Model.Stairs;

import java.util.*;

/**
 * 
 */
public class BasicDungeonGenerator implements IDungeonGenerator {

    private static final int MAX_DEPTH = 10;
    private static final int MIN_DEPTH = 6;
    public int MAX_POTIONS = 50;
    public int MAX_TREASURES = 50;
    public int MIN_POTIONS = 20;
    public int MIN_TREASURES = 20;

    public float COEF_MONSTER = 0.05f;
    public float COEF_POTION = 0.025f;
    public float COEF_TREASURE = 0.05f;

    public int MAX_ROOM_PER_LEVEL = 5;
    public int MIN_ROOM_PER_LEVEL = 1;

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

        // stores the room being generated
        Room currentRoom;
        // stores random position to put stairs
        int currentRoomStairsX, currentRoomStairsY;
        Room nextRoom;
        int nextRoomStairsX, nextRoomStairsY;

        // Generating random depth between MIN_DEPTH included and MAX_DEPTH excluded
        int depth = randomGenerator.nextInt(MAX_DEPTH - MIN_DEPTH) + MIN_DEPTH;

        // first generate as many rooms as the depth of the dungeon (path to exit)
        for (int i = 0 ; i < depth ; i++)
            rooms.add(generateRoom(i+1));

        // generate stairs between each generated room
        // stops before the last room => last room has the exit
        for (int i = 0 ; i < rooms.size() - 1 ; i++) {
            currentRoom = rooms.get(i);
            nextRoom = rooms.get(i+1);

            currentRoomStairsX = randomGenerator.nextInt(currentRoom.getWidth() - 1);
            currentRoomStairsY = randomGenerator.nextInt(currentRoom.getHeight()- 1);

            nextRoomStairsX = randomGenerator.nextInt(nextRoom.getWidth() - 1);
            nextRoomStairsY = randomGenerator.nextInt(nextRoom.getHeight()- 1);

            // generate stairs to go to the next room
            currentRoom.getCells()[currentRoomStairsY][currentRoomStairsX] = new Stairs(currentRoomStairsX,
                    currentRoomStairsY, nextRoom, nextRoomStairsX, nextRoomStairsY);

            // generate stairs to come back to the current room
            nextRoom.getCells()[nextRoomStairsY][nextRoomStairsX] = new Stairs(nextRoomStairsX, nextRoomStairsY, currentRoom,
                    currentRoomStairsX, currentRoomStairsY);

        }

        Room[] res = new Room[this.rooms.size()];
        this.rooms.toArray(res);

        return new Dungeon(res);
    }

    /**
     * Generates a random room
     * @return
     */
    /*
    private Room generateRoom(int level) {

        int chanceOfStairs;
        int stairs_x;
        int stairs_y;

        int width = randomGenerator.nextInt(Room.MAX_WIDTH - Room.MIN_HEIGHT) + Room.MIN_HEIGHT;
        int height = randomGenerator.nextInt(Room.MAX_HEIGHT - Room.MIN_WIDTH) + Room.MIN_WIDTH;
        Room r = new Room(width, height, level);
        if (level == 1) {
            stairs_x = randomGenerator.nextInt(r.getWidth() - 1);
            System.out.println(stairs_x);
            stairs_y = randomGenerator.nextInt(r.getHeight()- 1);
            System.out.println(stairs_y);
            System.out.println("width: " + r.getWidth());
            System.out.println("height: " + r.getHeight());
            r.getCells()[stairs_y][stairs_x] = new Stairs(stairs_x, stairs_y, null, 0, 0);
        } else if (randomGenerator.nextFloat() > (0.25 * level)) {
            stairs_x = randomGenerator.nextInt(r.getWidth() - 1);
            stairs_y = randomGenerator.nextInt(r.getHeight()- 1);
            Cell c = r.getCell(stairs_x, stairs_y);
            if (!(c instanceof Stairs)) {
                r.getCells()[stairs_y][stairs_x] = new Stairs(stairs_x, stairs_y, null, 0, 0);
            }
        }
        return r;
    }
    */

    private Room generateRoom(int level) {
        int width = randomGenerator.nextInt(Room.MAX_WIDTH - Room.MIN_HEIGHT) + Room.MIN_HEIGHT;
        int height = randomGenerator.nextInt(Room.MAX_HEIGHT - Room.MIN_WIDTH) + Room.MIN_WIDTH;
        Room r = new Room(width, height, level);
        return r;
    }
}