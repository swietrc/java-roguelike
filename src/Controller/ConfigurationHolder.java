package Controller;

import Utils.Const;

public class ConfigurationHolder {

    private int seed;
    private String generator;
    private int depth;
    private boolean progressive;

    /**
     * Constructor
     * @param seed int seed used to generate the map
     * @param generator String name of the generator
     * @param depth depth of the dungeon generated
     * @param progressive boolean is generator progressive?
     */
    public ConfigurationHolder(int seed, String generator, int depth, boolean progressive) {
        this.seed = seed;
        this.generator = generator;
        this.depth = depth;
        if (depth == 0)
            this.depth = Const.DEFAULT_DEPTH;
        else if (depth > 1000)
            this.depth = 1000;

        this.progressive = progressive;
    }

    /**
     * Accessor for seed
     * @return int seed
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Accessor for generator
     * @return IDungeonGenerator used to generate the map
     */
    public IDungeonGenerator getGenerator() {
        IDungeonGenerator res;

        if (this.generator.equals(KrakenDungeonGenerator.NAME)) {
            res = (this.seed == 0) ?  new KrakenDungeonGenerator() : new KrakenDungeonGenerator(this.seed);
        } else if (this.generator.equals(TowerDungeonGenerator.NAME)) {
            res = (this.seed == 0) ? new TowerDungeonGenerator() : new TowerDungeonGenerator(this.seed);
        } else res = null;

        return res;
    }

    /**
     * Accessor for depth
     * @return int depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Accessor for progressive
     * @return boolean whether dungeon should be generated progressively or not
     */
    public boolean isProgressive() {
        return progressive;
    }
}
