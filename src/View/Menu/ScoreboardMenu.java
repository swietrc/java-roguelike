package View.Menu;

import Controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardMenu extends JPanel {

    JTable table;
    String[] tableHeader = new String[]{"Name", "Gold", "Strength"};
    Object[][] scores = new String[][]{{"test", "test", "test"}};
    JScrollPane scrollPane;

    public ScoreboardMenu() {
        super();
        initialize();
    }

    private void initialize() {
        this.setLayout(new BorderLayout());

        table = new JTable(Game.getInstance().getScores(), new String[] {"Name","Gold","Strength"});
        scrollPane = new JScrollPane(table);

        JPanel p = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Game.getInstance().showTitleScreen();
            }
        });

        p.add(backButton);
        this.add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(p, BorderLayout.PAGE_END);
    }
}
