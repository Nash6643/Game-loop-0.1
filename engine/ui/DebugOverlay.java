package engine.ui;

import engine.core.GameEngine;
import engine.scene.TestScene;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class DebugOverlay extends JPanel {
    private final GameEngine engine;
    private final JLabel fpsLabel;
    private final JButton pauseButton;
    private final JSlider speedSlider;

    public DebugOverlay(GameEngine engine, TestScene scene) {
        this.engine = engine;
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        setBackground(new Color(30, 30, 30, 240));
        
        fpsLabel = new JLabel("Engine Ready");
        fpsLabel.setForeground(Color.GREEN);
        
        pauseButton = new JButton("Pause Engine");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> {
            boolean running = engine.togglePause();
            pauseButton.setText(running ? "Pause Engine" : "Resume Engine");
        });

        // Speed Slider (100 to 600 px/sec)
        JLabel sliderLabel = new JLabel("Speed:");
        sliderLabel.setForeground(Color.WHITE);
        
        speedSlider = new JSlider(100, 600, (int) scene.getSpeed());
        speedSlider.setFocusable(false);
        speedSlider.setBackground(new Color(30, 30, 30));
        speedSlider.addChangeListener(e -> scene.setSpeed(speedSlider.getValue()));

        add(fpsLabel);
        add(pauseButton);
        add(sliderLabel);
        add(speedSlider);
    }

    public void updateMetrics(int currentFps) {
        fpsLabel.setText(String.format("FPS: %d", currentFps));
    }
}