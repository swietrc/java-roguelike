package Controller;

import Model.Cell;
import Model.Room;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Generates a dungeon with multiple branches
 */
public class KrakenDungeonGenerator extends TowerDungeonGenerator {

    public static final String NAME = "Kraken generator";

    public KrakenDungeonGenerator() {
        super();
    }

    public KrakenDungeonGenerator(int seed) {
        super(seed);
    }

    /**
     * Takes a dungeon generated with TowerGenerator and generates additional rooms to it
     * @param depth depth of the dungeon
     * @return dungeon with multiple branches
     */
    public Dungeon generateDungeon(int depth) {
        // get dungeon generated with TowerDungeonGenerator
        Dungeon d = super.generateDungeon(depth);

        // init list of rooms of the new dungeon & add old rooms to it
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.addAll(Arrays.asList(d.getRooms()));
        ArrayList<Room> additionalRooms = new ArrayList<>();
        float chanceOfBranch;
        for (Room r : rooms) {
            chanceOfBranch = 1; // - ((float)r.getLevel() / depth);
            if (getRandomGenerator().nextFloat() <= chanceOfBranch) {
                additionalRooms.addAll(Arrays.asList(generateBranch(r, depth)));
            }
        }

        // generation of entities in new rooms
        for (Room r : additionalRooms) {
            Cell[][] cells = r.getCells();
            for (Cell[] row : cells) {
                for (Cell c : row) {
                    if (Game.getInstance().getConfiguration().isProgressive())
                        MonsterGenerator.setDifficultyFactor(r.getLevel() - 1);
                    c.setEntity(super.generateEntity());
                }
            }
        }

        rooms.addAll(additionalRooms);

        Room[] res = new Room[rooms.size()];
        res = rooms.toArray(res);

        System.out.println("---- " + rooms.size() + " ROOMS GENERATED ----");
        return new Dungeon(res);
    }

    /**
     * generates a room array linked to room passed as parameter
     * @param r root of the branch
     * @param maxDepth maximum depth of the branch
     * @return branch
     */
    public Room[] generateBranch(Room r, int maxDepth) {
        ArrayList<Room> branch = new ArrayList<Room>();
        Room currentRoom = r;
        Room nextRoom;

        // chance to generate a room
        float chanceOfNextRoom = 0.5f;

        // loops as long as the depth is inferior to maxdepth and the random generator returns a value <= 0.5
        while ((currentRoom.getLevel() < maxDepth) && (getRandomGenerator().nextFloat() <= chanceOfNextRoom)) {
            nextRoom = generateRoom(currentRoom.getLevel() + 1);
            generateStairs(currentRoom, nextRoom);
            currentRoom = nextRoom;
            branch.add(currentRoom);
            if (getRandomGenerator().nextFloat() <= 0.3f && (currentRoom.getLevel() <= maxDepth)) {
                // recursive call to generate branch for each new room
                branch.addAll(Arrays.asList(generateBranch(currentRoom, maxDepth - 1)));
            }
        }

        // converts ArrayList to array and returns it
        Room[] res = new Room[branch.size()];
        res = branch.toArray(res);
        return res;
    }
}
