package View;

import Controller.Game;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("Roguelike v" + Utils.VERSION);
        this.setContentPane(new GamePanel());
        this.setSize(Utils.WIDTH, Utils.HEIGHT);
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
        Game.getInstance().run();
    }
}