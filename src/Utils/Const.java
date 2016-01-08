package Utils;

import Controller.TowerDungeonGenerator;

import java.awt.*;

public class Const {
    public static final String VERSION = "1.0";
    public static final int MAP_TEXTSIZE = 16;
    public static final int TEXTSIZE = 14;
    public static final int TITLESIZE = 35;
    public static final int MENUTITLESIZE = 25;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static final Font MAP_FONT = new Font(Font.MONOSPACED, Font.BOLD, Const.MAP_TEXTSIZE);
    public static final Font MENU_FONT = new Font(Font.MONOSPACED, Font.BOLD, Const.TEXTSIZE);
    public static final Font TITLE_FONT = new Font(Font.SERIF, Font.BOLD, Const.TITLESIZE);
    public static final Font MENU_TITLE_FONT = new Font(Font.SERIF, Font.BOLD, Const.MENUTITLESIZE);

    public static final int DEFAULT_STRENGTH = 80;
    public static final String DEFAULT_GENERATOR = TowerDungeonGenerator.NAME;
    public static final int DEFAULT_DEPTH = 5;

    public static String SCORE_FILE = "./scoreboard.txt";
}
