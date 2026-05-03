package engine.graphics;

import engine.scene.Scene;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {
    private Scene currentScene;

    public RenderPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (currentScene != null) {
            currentScene.render(g2);
        }
        g2.dispose();
    }
}