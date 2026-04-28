package engine.scene;

import java.io.Serializable;

public class SceneState implements Serializable {
    private static final long serialVersionUID = 1L;

    private final double playerX;
    private final double playerY;
    private final int playerSize;
    private final double cameraX;
    private final double cameraY;

    public SceneState(double playerX, double playerY, int playerSize, double cameraX, double cameraY) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerSize = playerSize;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public double getPlayerX() { return playerX; }
    public double getPlayerY() { return playerY; }
    public int getPlayerSize() { return playerSize; }
    public double getCameraX() { return cameraX; }
    public double getCameraY() { return cameraY; }
}