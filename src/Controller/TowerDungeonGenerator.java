package Controller;

import Model.*;

import java.util.*;

/**
 * Generates a tower dungeon
 */
public class TowerDungeonGenerator implements IDungeonGenerator {


    public static final String NAME = "Tower generator";

    public int MAX_POTIONS = 50;
    public int MAX_TREASURES = 50;
    public int MIN_POTIONS = -20;
    public int MIN_TREASURES = 20;

    public float COEF_MONSTER = 0.095f;
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
    public TowerDungeonGenerator(int seed) {
        randomGenerator = new Random(seed);
    }

    /**
     * Generates a random dungeon using a random seed
     */
    public TowerDungeonGenerator() {
        randomGenerator = new Random();
    }

    /**
     * generates a basic dungeon
     * @return basic dungeon
     */
    public Dungeon generateDungeon(int depth) {

        // stores the room being generated
        Room currentRoom;
        // stores random position to put stairs
        int currentRoomStairsX, currentRoomStairsY;
        Room nextRoom;
        int nextRoomStairsX, nextRoomStairsY;

        // Generating random depth between MIN_DEPTH included and MAX_DEPTH excluded
        // depth = randomGenerator.nextInt(MAX_DEPTH - MIN_DEPTH) + MIN_DEPTH;

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
                    currentRoomStairsY, nextRoom, nextRoomStairsX, nextRoomStairsY, 1);

            // generate stairs to come back to the current room
            nextRoom.getCells()[nextRoomStairsY][nextRoomStairsX] = new Stairs(nextRoomStairsX, nextRoomStairsY, currentRoom,
                    currentRoomStairsX, currentRoomStairsY, -1);

            if (i == rooms.size() - 2) {
                Cell exitCell;
                int exitX, exitY;
                do {
                    exitX = randomGenerator.nextInt(nextRoom.getWidth() - 1);
                    exitY = randomGenerator.nextInt(nextRoom.getHeight() - 1);

                    exitCell = nextRoom.getCells()[exitY][exitX];
                } while (exitCell instanceof Stairs);
                nextRoom.getCells()[exitY][exitX] = new Exit(exitX, exitY);
            }
        }

        // generation of entities inside cells
        for (Room r : this.rooms) {
            Cell[][] cells = r.getCells();
            for (Cell[] row : cells) {
                for (Cell c : row) {
                    if (Game.getInstance().getConfiguration().isProgressive())
                        MonsterGenerator.setDifficultyFactor(r.getLevel() - 1);
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
    protected Entity generateEntity() {
        // choose what entity to return
        float entitySelector = ((int) (randomGenerator.nextFloat() * 1000)) / 1000f;
        if (entitySelector <= COEF_TREASURE)
            return new Treasure(randomGenerator.nextInt(MAX_TREASURES - MIN_TREASURES) + MIN_TREASURES);
        else if (entitySelector > COEF_TREASURE && entitySelector < (COEF_MONSTER + COEF_TREASURE))
            return monsterGenerator.getRandomMonster();
        else if (entitySelector > (COEF_MONSTER + COEF_TREASURE) && entitySelector < (COEF_MONSTER + COEF_TREASURE + COEF_POTION))
            return new Potion(randomGenerator.nextInt(MAX_POTIONS - MIN_POTIONS) + MIN_POTIONS);
        else {
            return null; }
    }

    /**
     * Generates a room of random height and width
     * @return
     */
    protected Room generateRoom(int level) {
        int width = randomGenerator.nextInt(Room.MAX_WIDTH - Room.MIN_HEIGHT) + Room.MIN_HEIGHT;
        int height = randomGenerator.nextInt(Room.MAX_HEIGHT - Room.MIN_WIDTH) + Room.MIN_WIDTH;
        Room r = new Room(width, height, level);
        return r;
    }

    /**
     * Generates stairs between rooms r1 and r2
     * Stairs in r1 to link to r2
     * Stairs in r2 to link to r1
     * @param r1 entry point of the stairs
     * @param r2 entry point of the stairs
     */
    protected void generateStairs(Room r1, Room r2) {
        // X,Y Coordinates of stairs in r1
        int r1StairsX, r1StairsY;
        // X,Y Coordinates of stairs in r2
        int r2StairsX, r2StairsY;

        // get random coordinates for stairs in r1
        do {
            r1StairsX = randomGenerator.nextInt(r1.getWidth());
            r1StairsY = randomGenerator.nextInt(r1.getHeight());
        } while (r1.getCell(r1StairsX, r1StairsY) instanceof Stairs);

        // get random coordinates for stairs in r2
        do {
            r2StairsX = randomGenerator.nextInt(r2.getWidth());
            r2StairsY = randomGenerator.nextInt(r2.getHeight());
        } while (r2.getCell(r2StairsX, r2StairsY) instanceof Stairs);

        // generate stairs to go to r2
        r1.getCells()[r1StairsY][r1StairsX] = new Stairs(r1StairsX,
                r1StairsY, r2, r2StairsX, r2StairsY, 1);

        // generate stairs to go to r1
        r2.getCells()[r2StairsY][r2StairsX] = new Stairs(r2StairsX,
                r2StairsY, r1, r1StairsX, r1StairsY, -1);
    }

    /**
     * Accessor for randomGenerator
     * @return randomGenerator
     */
    protected Random getRandomGenerator() {
        return randomGenerator;
    }
}