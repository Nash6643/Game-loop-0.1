package engine.scene;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestScene extends Scene {
    private double xPos = 50;
    private double speed = 200.0;

    @Override
    public void init() {}

    @Override
    public void update(double deltaTime) {
        xPos += speed * deltaTime;
        if (xPos > 700 || xPos < 50) speed = -speed;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.CYAN);
        g.fillOval((int) xPos, 250, 40, 40);
    }

    @Override
    public void dispose() {}
}