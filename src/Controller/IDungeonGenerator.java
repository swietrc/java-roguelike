package Controller;

import java.util.*;

/**
 * 
 */
public interface IDungeonGenerator {

    /**
     * @param maxDepth 
     * @return
     */
    public Dungeon generateDungeon(int maxDepth);

}