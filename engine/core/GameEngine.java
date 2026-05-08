package engine.core;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import engine.input.InputManager;
import engine.scene.TestScene;
import engine.ui.DebugOverlay;
import engine.audio.SoundManager;
import engine.config.EngineConfig;

public class GameEngine implements Runnable {
    private final JFrame window;
    private final RenderPanel renderPanel;
    private final InputManager inputManager;
    private final TestScene currentScene;
    private final DebugOverlay debugOverlay;
    private final SoundManager soundManager;
    private final EngineConfig config;
    private final EngineMetrics metrics = new EngineMetrics();

    private boolean running = false;
    private Thread gameThread;
    
    private final int targetFps;
    private int currentFps = 0;

    public GameEngine() {
        config = new EngineConfig();
        this.targetFps = config.getInt("targetFps", 60);
        int width = config.getInt("screenWidth", 800);
        int height = config.getInt("screenHeight", 600);

        window = new JFrame("Java 2D Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        inputManager = new InputManager();
        currentScene = new TestScene(inputManager);
        soundManager = new SoundManager();

        renderPanel = new RenderPanel(width, height, currentScene);
        renderPanel.addKeyListener(inputManager);
        renderPanel.addMouseListener(inputManager);

        debugOverlay = new DebugOverlay(this, currentScene, soundManager);

        window.setLayout(new BorderLayout());
        window.add(debugOverlay, BorderLayout.NORTH);
        window.add(renderPanel, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    public void start() {
        if (running) return;
        running = true;
        window.setVisible(true);
        renderPanel.requestFocusInWindow();
        gameThread = new Thread(this, "GameLoopThread");
        gameThread.start();
    }

    public void stop() {
        running = false;
        try {
            if (gameThread != null) gameThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean togglePause() {
        renderPanel.setPaused(!renderPanel.isPaused());
        return !renderPanel.isPaused();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerUpdate = 1_000_000_000.0 / targetFps;
        
        long fpsTimer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            metrics.recordFrame(deltaTime);

            if (!renderPanel.isPaused()) {
                currentScene.update(deltaTime);
            }

            renderPanel.repaint();
            frames++;

            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                currentFps = frames;
                frames = 0;
                fpsTimer += 1000;
                debugOverlay.updateMetrics(currentFps);
            }

            try {
                long sleepTime = (long) ((lastTime + nsPerUpdate - System.nanoTime()) / 1_000_000);
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }
}