package engine.graphics;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {
    public RenderPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }
}