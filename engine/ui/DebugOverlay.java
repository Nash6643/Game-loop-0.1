package engine.ui;

import engine.core.GameEngine;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DebugOverlay extends JPanel {
    private final GameEngine engine;
    private final JLabel fpsLabel;
    private final JButton pauseButton;

    public DebugOverlay(GameEngine engine) {
        this.engine = engine;
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(30, 30, 30, 220));
        
        fpsLabel = new JLabel("FPS: -- | Delta: 0.000s");
        fpsLabel.setForeground(Color.GREEN);
        
        pauseButton = new JButton("Pause Engine");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> {
            boolean running = engine.togglePause();
            pauseButton.setText(running ? "Pause Engine" : "Resume Engine");
        });

        add(fpsLabel);
        add(pauseButton);
    }

    public void updateMetrics(int currentFps, double deltaTime) {
        fpsLabel.setText(String.format("FPS: %d | Delta: %.4fs", currentFps, deltaTime));
    }
}