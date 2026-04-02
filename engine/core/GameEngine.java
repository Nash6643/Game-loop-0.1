package engine.core;

public class GameEngine implements Runnable {
    private static final int TARGET_FPS = 60;
    private boolean running = false;
    private Thread gameThread;

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
        double deltaF = 0;

        while (running) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                // Trigger tick and render
                deltaF--;
            }
        }
    }
}