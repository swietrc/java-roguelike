package Model;

/**
 * represents a room in the game
 */
public class Room {

    public static final int MAX_WIDTH = 15;
    public static final int MIN_WIDTH = 4;
    public static final int MAX_HEIGHT= 15;
    public static final int MIN_HEIGHT = 4;

    Cell[][] cells;

    private final int width;  /** Room width  */
    private final int height; /** Room height */
    private final int level;  /** Level of the room inside the dungeon */

    /**
     * Default constructor
     * @param height int room height
     * @param width int room width
     * @param level int level in the dungeon
     * @param cells list of cells in the room
     */
    public Room(int width, int height, int level, Cell[][] cells) {
        this(width, height, level);
        this.cells = cells;

        if (this.cells == null || (this.cells.length != width && this.cells[0].length != height))
            throw new IllegalArgumentException();
    }

    public Room(int width, int height, int level) {
        // Check against max size
        if (width > MAX_WIDTH || height > MAX_HEIGHT || level <= 0)
            throw new IllegalArgumentException(""+width+" "+height+" "+level);

        this.width = width;
        this.height = height;
        this.level = level;

        Cell[][] c = new Cell[height][width];
        for (int i = 0 ; i < c.length ; i++) {
            for (int j = 0 ; j < c[0].length ; j++) {
                c[i][j] = new Cell(j, i);
            }
        }

        this.cells = c;
    }

    public Cell getCell(int xPos, int yPos) {
        try {
            return cells[yPos][xPos];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public String toString() {
        String res = "";
        for (int i = 0 ; i < this.width + 2 ; i++)
            res += "#";

        res += "\n";

        for (Cell[] cArray : this.cells) {
            res += "#";
            for (Cell c : cArray) {
                res += c.toString();
            }
            res += "#\n";
        }

        for (int i = 0 ; i < this.width + 2 ; i++)
            res += "#";

        return res;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }
}