package engine.ui;

import engine.audio.SoundManager;
import engine.core.GameEngine;
import engine.scene.TestScene;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class DebugOverlay extends JPanel {
    private final JLabel fpsLabel;
    private final JButton pauseButton;
    private final JSlider speedSlider;
    private final JSlider sizeSlider;
    private final JCheckBox particleToggle;
    private final JCheckBox colliderToggle;
    private final JCheckBox audioToggle;

    public DebugOverlay(GameEngine engine, TestScene scene, SoundManager soundManager) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(new Color(30, 30, 30, 240));
        
        fpsLabel = new JLabel("FPS: --");
        fpsLabel.setForeground(Color.GREEN);
        
        pauseButton = new JButton("Pause");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> {
            boolean running = engine.togglePause();
            pauseButton.setText(running ? "Pause" : "Resume");
        });

        // Velocity Speed Control
        JLabel speedLbl = new JLabel("Speed:");
        speedLbl.setForeground(Color.WHITE);
        speedSlider = new JSlider(100, 600, (int) scene.getSpeed());
        speedSlider.setFocusable(false);
        speedSlider.addChangeListener(e -> scene.setSpeed(speedSlider.getValue()));

        // Entity Radius Control
        JLabel sizeLbl = new JLabel("Size:");
        sizeLbl.setForeground(Color.WHITE);
        sizeSlider = new JSlider(10, 100, scene.getEntitySize());
        sizeSlider.setFocusable(false);
        sizeSlider.addChangeListener(e -> scene.setEntitySize(sizeSlider.getValue()));

        // Particle Trail Toggle
        particleToggle = new JCheckBox("Particles", true);
        particleToggle.setFocusable(false);
        particleToggle.setForeground(Color.WHITE);
        particleToggle.setOpaque(false);
        particleToggle.addActionListener(e -> scene.getEmitter().setEnabled(particleToggle.isSelected()));

        // Wireframe Colliders Debug Toggle
        colliderToggle = new JCheckBox("Show Bounds", true);
        colliderToggle.setFocusable(false);
        colliderToggle.setForeground(Color.WHITE);
        colliderToggle.setOpaque(false);
        colliderToggle.addActionListener(e -> scene.setDebugDrawColliders(colliderToggle.isSelected()));

        // Audio Toggle
        audioToggle = new JCheckBox("Audio", true);
        audioToggle.setFocusable(false);
        audioToggle.setForeground(Color.WHITE);
        audioToggle.setOpaque(false);
        audioToggle.addActionListener(e -> {
            if (soundManager != null) {
                soundManager.setSoundEnabled(audioToggle.isSelected());
            }
        });

        add(fpsLabel);
        add(pauseButton);
        add(speedLbl);
        add(speedSlider);
        add(sizeLbl);
        add(sizeSlider);
        add(particleToggle);
        add(colliderToggle);
        add(audioToggle);
    }

    public void updateMetrics(int currentFps) {
        fpsLabel.setText(String.format("FPS: %d", currentFps));
    }
}