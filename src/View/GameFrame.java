package View;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    JPanel mainPanel = new JPanel(new BorderLayout());
    Map p = new Map();

    public GameFrame() {
        super("Roguelike" + Utils.VERSION);
        this.setContentPane(new GamePanel());
        this.setSize(Utils.WIDTH, Utils.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainPanel.add(new JTextField(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}