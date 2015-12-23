package View;

import Controller.Game;
import Model.Room;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

    GamePanel p = new GamePanel();

    public GameFrame() {
        super("Roguelike v" + Utils.VERSION);
        this.setContentPane(p);
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

    public void refresh(String roomStr) {

    }

    public static void main(String[] args) {
        Game.getInstance().run();
    }
}