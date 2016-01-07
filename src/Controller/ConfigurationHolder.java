package Controller;

import Utils.Const;

public class ConfigurationHolder {

    private int seed;
    private String generator;
    private int depth;

    public ConfigurationHolder(int seed, String generator, int depth) {
        this.seed = seed;
        this.generator = generator;
        this.depth = depth;
        if (depth == 0)
            this.depth = Const.DEFAULT_DEPTH;
        else if (depth > 1000)
            this.depth = 1000;
    }

    public int getSeed() {
        return seed;
    }

    /**
     * Returns a dungeon generator
     * @return
     */
    public IDungeonGenerator getGenerator() {
        IDungeonGenerator res;

        if (this.generator.equals(KrakenDungeonGenerator.NAME)) {
            res = (this.seed == 0) ?  new KrakenDungeonGenerator() : new KrakenDungeonGenerator(this.seed);
        } else if (this.generator.equals(BasicDungeonGenerator.NAME)) {
            res = (this.seed == 0) ? new BasicDungeonGenerator() : new BasicDungeonGenerator(this.seed);
        } else res = null;

        return res;
    }

    public int getDepth() {
        return depth;
    }
}
