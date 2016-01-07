package View.Menu;

import Controller.BasicDungeonGenerator;
import Controller.ConfigurationHolder;
import Controller.Game;
import Controller.KrakenDungeonGenerator;
import Utils.Const;
import Utils.Toolbox;

import javax.security.auth.login.Configuration;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JPanel {

    private String[] generatorList = new String[] {BasicDungeonGenerator.NAME, KrakenDungeonGenerator.NAME};

    private JComboBox generatorCombo = new JComboBox(generatorList);
    private JTextField depthField = new JTextField();
    private JTextField seedField = new JTextField();

    public OptionsMenu() {
        super();
        initialize();
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel menuTitle = new JLabel("Options");
        menuTitle.setFont(Const.MENU_TITLE_FONT);
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(10));
        this.add(menuTitle);
        this.add(Box.createVerticalStrut(10));
        this.setBackground(Color.LIGHT_GRAY);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        optionsPanel.setAlignmentX(Container.CENTER_ALIGNMENT);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 10;
        optionsPanel.add(new JLabel("Generator"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        optionsPanel.add(generatorCombo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        optionsPanel.add(new JLabel("Depth of dungeon"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        optionsPanel.add(depthField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        optionsPanel.add(new JLabel("Seed"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        optionsPanel.add(seedField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().showTitleScreen();
            }
        });
        optionsPanel.add(backButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        JButton validateButton = new JButton("Validate");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurationHolder c = new ConfigurationHolder(getSeed(), getGenerator(), getDepth());
                Game.getInstance().setConfig(c);
                Game.getInstance().showTitleScreen();
            }
        });

        optionsPanel.add(validateButton, c);
        this.add(optionsPanel);
    }

    public String getGenerator() {
        return (String) this.generatorCombo.getSelectedItem();
    }

    public int getDepth() {
        if(Toolbox.isInteger(this.depthField.getText()))
            return Math.abs(Integer.parseInt(this.depthField.getText()));
        else
            return 0;
    }

    public int getSeed() {
        if (Toolbox.isInteger(this.seedField.getText()))
            return Integer.parseInt(this.seedField.getText());
        else
            return 0;
    }
}
