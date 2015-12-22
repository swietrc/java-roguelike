package View;

import Utils.Utils;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
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
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        break;
                    case KeyEvent.VK_DOWN:
                        break;
                    case KeyEvent.VK_LEFT:
                        break;
                    case KeyEvent.VK_RIGHT:
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}