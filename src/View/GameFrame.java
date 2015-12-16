package View;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    JPanel mainPanel = new JPanel(new BorderLayout());
    MapPanel p = new MapPanel(new Dimension(WIDTH - 10, HEIGHT - 10));

    public GameFrame() {
        super("Roguelike");
        this.setContentPane(this.mainPanel);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("RogueLike");
        lbl.setHorizontalAlignment(JLabel.CENTER);
        this.mainPanel.add(lbl, BorderLayout.NORTH);
        JTextArea test = new JTextArea();
        test.setText(".....@.....");
        this.mainPanel.add(new JTextField(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }


}