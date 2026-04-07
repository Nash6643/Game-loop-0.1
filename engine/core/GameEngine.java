package engine.core;

import engine.graphics.RenderPanel;
import engine.scene.Scene;

public class GameEngine implements Runnable {
    private static final int TARGET_FPS = 60;
    private boolean running = false;
    private boolean paused = false;
    private Thread gameThread;
    
    private final RenderPanel renderPanel;
    private Scene currentScene;
    private int currentFps = 0;

    // Viewport dimensions
    private int width = 800;
    private int height = 600;

    public GameEngine(RenderPanel renderPanel) {
        this.renderPanel = renderPanel;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public boolean togglePause() {
        this.paused = !this.paused;
        return !this.paused;
    }

    public boolean isPaused() { return paused; }
    public int getCurrentFps() { return currentFps; }

    public void setScene(Scene scene) {
        if (this.currentScene != null) {
            this.currentScene.dispose();
        }
        this.currentScene = scene;
        this.currentScene.init();
        this.renderPanel.setCurrentScene(scene);
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this, "GameEngine-Loop");
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / TARGET_FPS;
        long previousTime = System.nanoTime();
        
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime) / 1_000_000_000.0;

            if (!paused && currentScene != null) {
                currentScene.update(deltaTime);
            }
            
            renderPanel.repaint();

            previousTime = currentTime;
            frames++;

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck += 1000;
                currentFps = frames;
                frames = 0;
            }

            long sleepTime = (long) ((timePerFrame - (System.nanoTime() - currentTime)) / 1_000_000);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}