package View.Menu;

import Controller.Game;
import Utils.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Game main menu
 */
public class MainMenu extends JPanel {

    List<JButton> buttonList = new ArrayList<>();

    public MainMenu() {
        super();
        initialize();
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Rogue");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(Const.TITLE_FONT);
        this.add(Box.createVerticalStrut(50));
        this.add(title);
        this.add(Box.createVerticalStrut(50));

        JPanel buttonMenu = new JPanel();
        buttonMenu.setLayout(new BoxLayout(buttonMenu, BoxLayout.Y_AXIS));
        initButtonList();

        for (JButton b : buttonList) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setPreferredSize(new Dimension(150, 30));
            b.setMaximumSize(new Dimension(150, 30));
            buttonMenu.add(b);
            buttonMenu.add(Box.createVerticalStrut(10));
        }

        this.add(buttonMenu);
    }

    private void initButtonList() {
        JButton newGameButton = new JButton("NEW GAME");
        JButton scoreboardButton = new JButton("SCOREBOARD");
        JButton optionsButton = new JButton("Options");
        JButton exitButton = new JButton("Exit");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().newGame();
            }
        });
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().showOptions();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().quit();
            }
        });

        buttonList.add(newGameButton);
        buttonList.add(scoreboardButton);
        buttonList.add(optionsButton);
        buttonList.add(exitButton);
    }
}
