package View;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    public MapPanel(Dimension d) {
        super();
        this.setSize(d);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.setFont(Font.getFont(Font.MONOSPACED));
        g.drawString(".............", 25, 25);
    }
}
