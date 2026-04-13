package engine.core;

import engine.graphics.RenderPanel;
import engine.input.InputManager;
import engine.scene.TestScene;
import engine.ui.DebugOverlay;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Engine Core v1.2 - Interactive Sandbox");
        RenderPanel panel = new RenderPanel(800, 600);
        
        InputManager input = new InputManager();
        panel.addKeyListener(input);

        GameEngine engine = new GameEngine(panel);
        TestScene testScene = new TestScene(input);
        engine.setScene(testScene);

        DebugOverlay debugOverlay = new DebugOverlay(engine, testScene);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        
        window.add(debugOverlay, BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.requestFocusInWindow();
        engine.start();
    }
}