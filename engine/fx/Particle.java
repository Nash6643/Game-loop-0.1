package engine.fx;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {
    private double x, y;
    private double vx, vy;
    private float alpha = 1.0f;
    private final float fadeSpeed;
    private final Color color;

    public Particle(double x, double y, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.fadeSpeed = 1.5f + (float) Math.random() * 2.0f;
    }

    public void update(double deltaTime) {
        x += vx * deltaTime;
        y += vy * deltaTime;
        alpha -= fadeSpeed * deltaTime;
        if (alpha < 0) alpha = 0;
    }

    public void render(Graphics2D g) {
        if (alpha <= 0) return;
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (alpha * 255)));
        g.fillRect((int) x - 2, (int) y - 2, 5, 5);
    }

    public boolean isDead() {
        return alpha <= 0;
    }
}