package engine.scene;

import engine.input.InputManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TestScene extends Scene {
    private double xPos = 400;
    private double yPos = 300;
    private double speed = 250.0;
    private int entitySize = 40;
    private Color entityColor = Color.CYAN;

    private final InputManager input;

    public TestScene(InputManager input) {
        this.input = input;
    }

    @Override
    public void init() {}

    @Override
    public void update(double deltaTime) {
        if (input != null) {
            if (input.isKeyPressed(KeyEvent.VK_W) || input.isKeyPressed(KeyEvent.VK_UP)) { yPos -= speed * deltaTime; }
            if (input.isKeyPressed(KeyEvent.VK_S) || input.isKeyPressed(KeyEvent.VK_DOWN)) { yPos += speed * deltaTime; }
            if (input.isKeyPressed(KeyEvent.VK_A) || input.isKeyPressed(KeyEvent.VK_LEFT)) { xPos -= speed * deltaTime; }
            if (input.isKeyPressed(KeyEvent.VK_D) || input.isKeyPressed(KeyEvent.VK_RIGHT)) { xPos += speed * deltaTime; }
        }

        // Screen Boundary Clamping
        int halfSize = entitySize / 2;
        if (xPos - halfSize < 0) xPos = halfSize;
        if (xPos + halfSize > 800) xPos = 800 - halfSize;
        if (yPos - halfSize < 0) yPos = halfSize;
        if (yPos + halfSize > 600) yPos = 600 - halfSize;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, 800, 600);

        int halfSize = entitySize / 2;
        g.setColor(entityColor);
        g.fillOval((int) xPos - halfSize, (int) yPos - halfSize, entitySize, entitySize);
    }

    @Override
    public void dispose() {}

    public void setSpeed(double speed) { this.speed = speed; }
    public double getSpeed() { return speed; }
}