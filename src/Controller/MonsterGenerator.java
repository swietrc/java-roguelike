package Controller;

import Model.Monster;

import java.util.*;

/**
 * 
 */
public class MonsterGenerator {

    /** Random number generator */
    private Random randomGenerator;
    /** Stores all the types of monster to generate */
    private ArrayList<MonsterType> monsters;
    /** Unique instance of MonsterFactory */
    private static MonsterGenerator instance;


    class MonsterType {

        /** Minimum strength of the monster */
        int minStrength;
        /** Maximum strength of the monster */
        int maxStrength;

        /** Minimum gold owned by the monster. */
        int minGold;
        /** Maximum gold owned by the monster. */
        int maxGold;

        /** Name of the monster */
        String name;

        /**
         * Constructor
         * @param name Name of the monster.
         * @param minStrength Minimum strength of the monster
         * @param maxStrength Maximum strength of the monster
         * @param minGold Minimum gold carried by the monster
         * @param maxGold Maximum gold carried by the monster
         */
        public MonsterType(String name, int minStrength, int maxStrength, int minGold, int maxGold) {
            this.name = name;

            this.minStrength = minStrength;
            this.maxStrength = maxStrength;

            this.minGold = minGold;
            this.maxGold = maxGold;
        }

        private Monster buildMonster() {
            int strength = randomGenerator.nextInt(this.maxStrength - this.minStrength) + this.minStrength;
            System.out.println("Build monster with strength " + strength);
            int gold = randomGenerator.nextInt(this.maxGold - this.minGold) + this.minGold;

            return new Monster(this.name, strength, gold);
        }
    }

    public MonsterGenerator() {
        this.randomGenerator = new Random();
        this.monsters = new ArrayList<>();
        addType("Bat", 1, 5, 2, 5);
        addType("Centaur", 15, 60, 25, 100);
        addType("Dracaufeu", 250, 450, 25, 100);
    }

    public void setRandomGenerator(Random r) {
        this.randomGenerator = r;
    }

    private void addType(String name, int minStrength, int maxStrength, int minGold, int maxGold) {
        MonsterType mType = new MonsterType(name, minStrength, maxStrength, minGold, maxGold);
        this.monsters.add(mType);
    }

    /**
     * Generates a random monster
     * @return random Monster
     */
    public Monster getRandomMonster() {
        int r = randomGenerator.nextInt(this.monsters.size());
        return this.monsters.get(r).buildMonster();
    }

    public static MonsterGenerator getInstance() {
        if (instance == null) {
            instance = new MonsterGenerator();
        }
        return instance;
    }

}