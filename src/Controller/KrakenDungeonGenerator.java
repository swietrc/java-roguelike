package Controller;

import Model.Cell;
import Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KrakenDungeonGenerator extends BasicDungeonGenerator {

    public static final String NAME = "Kraken generator";

    public KrakenDungeonGenerator() {
        super();
    }

    public KrakenDungeonGenerator(int seed) {
        super(seed);
    }

    public Dungeon generateDungeon(int depth) {
        Dungeon d = super.generateDungeon(depth);
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
        System.out.println(additionalRooms.size());
        // generation of entities inside cells
        for (Room r : additionalRooms) {
            Cell[][] cells = r.getCells();
            for (Cell[] row : cells) {
                for (Cell c : row) {
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

    public Room[] generateBranch(Room r, int maxDepth) {
        ArrayList<Room> branch = new ArrayList<Room>();
        Room currentRoom = r;
        Room nextRoom;

        float chanceOfNextRoom = 0.5f;
        while ((currentRoom.getLevel() < maxDepth) && (getRandomGenerator().nextFloat() <= chanceOfNextRoom)) {
            nextRoom = generateRoom(currentRoom.getLevel() + 1);
            generateStairs(currentRoom, nextRoom);
            currentRoom = nextRoom;
            branch.add(currentRoom);
            if (getRandomGenerator().nextFloat() <= 0.3f && (currentRoom.getLevel() <= maxDepth)) {
                branch.addAll(Arrays.asList(generateBranch(currentRoom, maxDepth - 1)));
            }
        }

        Room[] res = new Room[branch.size()];
        res = branch.toArray(res);
        return res;
    }
}
