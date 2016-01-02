package Controller;

public class ConfigurationHolder {

    private final int seed;
    private final String generator;
    private final int depth;

    public ConfigurationHolder(int seed, String generator, int depth) {
        this.seed = seed;
        this.generator = generator;
        this.depth = depth;
    }

    public int getSeed() {
        return seed;
    }

    public String getGenerator() {
        return generator;
    }

    public int getDepth() {
        return depth;
    }
}
