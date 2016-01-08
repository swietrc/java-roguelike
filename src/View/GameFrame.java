package View;

import Controller.Game;
import Utils.Const;
import View.Menu.MainMenu;
import View.Menu.OptionsMenu;
import View.Menu.ScoreboardMenu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Main Window of the game
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel = new GamePanel();
    private MainMenu menuPanel = new MainMenu();
    private OptionsMenu optionsMenu = new OptionsMenu();
    // private ScoreboardMenu scoreboardMenu = new ScoreboardMenu();

    public GameFrame() {
        super("Roguelike v" + Const.VERSION);
        this.setSize(Const.WIDTH, Const.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Game.getInstance().keyPressed(e);
            }
        });
    }

    public static void main(String[] args) {
        Game.getInstance().showTitleScreen();
    }

    public void showMenu() {
        this.setContentPane(menuPanel);
        // this.menuPanel.revalidate();
        // this.menuPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    public void showGame() {
        this.setContentPane(gamePanel);
        this.revalidate();
        this.repaint();
    }

    public void showOptions() {
        this.setContentPane(optionsMenu);
        this.revalidate();
        this.repaint();
    }

    public void showScoreboard() {
        // this.scoreboardMenu.updateBoard();
        this.setContentPane(new ScoreboardMenu());
        this.revalidate();
        this.repaint();
    }

    public void refresh(String map) {
        gamePanel.refresh(map);
    }

    public void setHUD(int gold, int strength, int floor) {
        gamePanel.setHUD(gold, strength, floor);
    }

    public void setNotification(String s) {
        gamePanel.setNotification(s);
    }
}