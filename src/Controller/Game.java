package Controller;

import Model.*;
import Model.Character;
import Utils.Const;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * 
 */
public class Game {

    private static String[][] scores;
    private static GameFrame frame;
    private static Game instance;
    private ConfigurationHolder cfg = new ConfigurationHolder(0, Const.DEFAULT_GENERATOR, Const.DEFAULT_DEPTH, false);

    private boolean win;

    private Player player;
    private IDungeonGenerator dungeonGenerator;

    /**
     * Default constructor
     */
    public Game() {
        frame = new GameFrame();
        updateScores();
        showTitleScreen();
    }

    /**
     * Initializes a new game
     */
    public void newGame() {
        // this.dungeonGenerator = new TowerDungeonGenerator();
        this.dungeonGenerator = cfg.getGenerator();

        Dungeon d = dungeonGenerator.generateDungeon(cfg.getDepth());
        Room[] rooms = d.getRooms();

        String playername = JOptionPane.showInputDialog(null, "What's your name ?");
        if (playername == null) playername = "anon";
        System.out.println(playername);

        this.player = new Player(playername, rooms[0], 0, 0);
        player.getCurrentCell().setVisible(true);
        rooms[0].getCell(0, 0).setEntity(player);

        frame.showGame();
        frame.refresh(player.getCurrentRoom().toString());
        frame.setHUD(player.getGold(), player.getStrength(), player.getCurrentRoom().getLevel());
    }

    /** Displays title screen in the main frame */
    public void showTitleScreen() {
        frame.showMenu();
    }

    public void showScoreboard() {
        frame.showScoreboard();
    }

    /** Gets executed after every key press */
    public void keyPressed(KeyEvent e) {
        setNotification("");
        // handle key pressed event
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveCharacter(player, Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                moveCharacter(player, Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                moveCharacter(player, Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                moveCharacter(player, Direction.EAST);
                break;
        }

        // update display
        frame.refresh(player.getCurrentRoom().toString());
        frame.setHUD(player.getGold(), player.getStrength(), player.getCurrentRoom().getLevel());
    }

    /**
     * Moves a character in a certain direction if possible
     * @param c Character
     * @param d Direction
     */
    private void moveCharacter(Character c, Direction d) {
        // Checks if player has died
        if (c.isAlive()) {
            int newX = c.getX();
            int newY = c.getY();

            switch (d) {
                case NORTH:
                    if (newY > 0) newY--;
                    break;
                case EAST:
                    if (newX < c.getCurrentRoom().getWidth() - 1) newX++;
                    break;
                case SOUTH:
                    if (newY < c.getCurrentRoom().getHeight() - 1) newY++;
                    break;
                case WEST:
                    if (newX > 0) newX--;
                    break;
            }

            Cell currentCell = c.getCurrentCell();
            Cell newCell = c.getCurrentRoom().getCell(newX, newY);

            if (newCell != currentCell) {
                newCell.trigger(c);

                // clear fog of war
                Room currentCharacterRoom = c.getCurrentRoom();
                Cell visibleCell;

                for (int i = c.getY() - 1 ; i <= c.getY() + 1 ; i++) {
                    for (int j = c.getX() - 1 ; j <= c.getX() + 1 ; j++) {
                        visibleCell = currentCharacterRoom.getCell(j, i);
                        if (visibleCell != null)
                            visibleCell.setVisible(true);
                    }
                }
            }

            if (!c.isAlive()) {
                JOptionPane.showConfirmDialog(null, "You died! You had " + this.player.getGold() + " gold!", "GAME OVER!", JOptionPane.OK_OPTION);
                this.showTitleScreen();
            }
        }
    }

    public static Game getInstance() {
        if (Game.instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * Shows options of the game
     */
    public void showOptions() {
        frame.showOptions();
    }

    /**
     * quits the game
     */
    public void quit() {
        frame.dispose();
        System.exit(0);
    }

    /**
     * Sets configuration of the game
     * @param c
     */
    public void setConfig(ConfigurationHolder c) {
        this.cfg = c;
    }

    /**
     * sets notification on the screen
     * @param notif
     */
    public void setNotification(String notif) {
        frame.setNotification(notif);
    }

    /**
     * saves the score of the player and gets him back to the menu
     */
    public void win() {
        JOptionPane.showConfirmDialog(null, "You won! You got out with " + this.player.getGold() + " gold!", "VICTORY!", JOptionPane.OK_OPTION);
        this.saveScore();
        this.updateScores();
        this.showTitleScreen();
    }

    /**
     * Saves the current score at the end of a file
     */
    private void saveScore() {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(Const.SCORE_FILE), true));
            writer.println(this.player.getName() + ":" + this.player.getGold() + ":" + this.player.getStrength());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates this.scores by reading the file where score is stored
     */
    public void updateScores() {
        ArrayList<String> temp = new ArrayList<>();
        File f = new File(Const.SCORE_FILE);
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        FileReader reader = null;
        try {
            reader = new FileReader(Const.SCORE_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                temp.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] res = new String[temp.size()][];

        for (int i = 0; i < temp.size() ; i++) {
            res[i] = temp.get(i).split(":");
        }
        try {
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scores = res;
    }

    /**
     * Accessor for Game.scores
     * @return String[][] Game.scores
     */
    public String[][] getScores() {
        return Game.scores;
    }

    /**
     * Accessor for the configuration of the current game
     * @return ConfigurationHolder configuration
     */
    public ConfigurationHolder getConfiguration() {
        return this.cfg;
    }
}