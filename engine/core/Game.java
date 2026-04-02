package engine.core;

import javax.swing.JFrame;
import engine.graphics.RenderPanel;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Engine Core v1.0");
        RenderPanel panel = new RenderPanel(800, 600);
        GameEngine engine = new GameEngine();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        engine.start();
    }
}