package Controller;

import Model.*;

import java.util.*;

/**
 * 
 */
public class BasicDungeonGenerator implements IDungeonGenerator {


    public static final String NAME = "Tower generator";
    private static final int MAX_DEPTH = 10;
    private static final int MIN_DEPTH = 6;

    public int MAX_POTIONS = 50;
    public int MAX_TREASURES = 50;
    public int MIN_POTIONS = 20;
    public int MIN_TREASURES = 20;

    public float COEF_MONSTER = 0.015f;
    public float COEF_POTION = 0.025f;
    public float COEF_TREASURE = 0.015f;

    public int MAX_ROOM_PER_LEVEL = 5;
    public int MIN_ROOM_PER_LEVEL = 1;

    private ArrayList<Room> rooms = new ArrayList<>();

    private Random randomGenerator;

    private MonsterGenerator monsterGenerator = MonsterGenerator.getInstance();

    /**
     * Generates a random dungeon using a seed
     * @param seed
     */
    public BasicDungeonGenerator(int seed) {
        randomGenerator = new Random(seed);
    }

    /**
     * Generates a random dungeon using a random seed
     */
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

        // generation of entities inside cells
        for (Room r : this.rooms) {
            System.out.println(r.getHeight() * r.getWidth());
            Cell[][] cells = r.getCells();
            for (Cell[] row : cells) {
                for (Cell c : row) {
                    c.setEntity(generateEntity());
                }
            }
        }


        Room[] res = new Room[this.rooms.size()];
        this.rooms.toArray(res);

        return new Dungeon(res);
    }

    /**
     * Generates a random entity using the constants defined in this class
     * @return random entity
     */
    private Entity generateEntity() {
        // choose what entity to return
        float entitySelector = ((int) (randomGenerator.nextFloat() * 1000)) / 1000f;
        if (entitySelector <= COEF_TREASURE)
            return new Treasure(randomGenerator.nextInt(MAX_TREASURES - MIN_TREASURES) + MIN_TREASURES);
        else if (entitySelector > COEF_TREASURE && entitySelector < (COEF_MONSTER + COEF_TREASURE))
            return monsterGenerator.getRandomMonster();
        else if (entitySelector > (COEF_MONSTER + COEF_TREASURE) && entitySelector < (COEF_MONSTER + COEF_TREASURE + COEF_POTION))
            return new Potion(randomGenerator.nextInt(MAX_POTIONS - MIN_POTIONS) + MIN_POTIONS);
        else
            return null;
    }

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

    /**
     * Generates a room of random height and width
     * @return
     */
    private Room generateRoom(int level) {
        int width = randomGenerator.nextInt(Room.MAX_WIDTH - Room.MIN_HEIGHT) + Room.MIN_HEIGHT;
        int height = randomGenerator.nextInt(Room.MAX_HEIGHT - Room.MIN_WIDTH) + Room.MIN_WIDTH;
        Room r = new Room(width, height, level);
        return r;
    }
}