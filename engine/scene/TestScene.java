package engine.scene;

import engine.input.InputManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TestScene extends Scene {
    private double xPos = 400;
    private double yPos = 300;
    private double speed = 250.0;
    private Color entityColor = Color.CYAN;
    private final InputManager input;

    public TestScene(InputManager input) {
        this.input = input;
    }

    @Override
    public void init() {}

    @Override
    public void update(double deltaTime) {
        if (input == null) return;

        if (input.isKeyPressed(KeyEvent.VK_W) || input.isKeyPressed(KeyEvent.VK_UP)) {
            yPos -= speed * deltaTime;
        }
        if (input.isKeyPressed(KeyEvent.VK_S) || input.isKeyPressed(KeyEvent.VK_DOWN)) {
            yPos += speed * deltaTime;
        }
        if (input.isKeyPressed(KeyEvent.VK_A) || input.isKeyPressed(KeyEvent.VK_LEFT)) {
            xPos -= speed * deltaTime;
        }
        if (input.isKeyPressed(KeyEvent.VK_D) || input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            xPos += speed * deltaTime;
        }

        // Clamp inside window boundaries (800x600 canvas)
        xPos = Math.max(20, Math.min(780, xPos));
        yPos = Math.max(20, Math.min(580, yPos));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(25, 25, 25));
        g.fillRect(0, 0, 800, 600);

        // Draw Player Entity
        g.setColor(entityColor);
        g.fillOval((int) xPos - 20, (int) yPos - 20, 40, 40);
    }

    @Override
    public void dispose() {}

    // GUI Tweak Methods
    public void setSpeed(double speed) { this.speed = speed; }
    public double getSpeed() { return speed; }
    public void setEntityColor(Color color) { this.entityColor = color; }
}