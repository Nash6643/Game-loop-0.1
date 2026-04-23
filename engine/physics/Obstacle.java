package engine.physics;

import java.awt.Color;
import java.awt.Graphics2D;

public class Obstacle {
    private final Collider collider;
    private final Color color;

    public Obstacle(double x, double y, double width, double height, Color color) {
        this.collider = new Collider(x, y, width, height, "Obstacle");
        this.color = color;
    }

    public void render(Graphics2D g) {
        AABB bounds = collider.getBounds();
        g.setColor(color);
        g.fillRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
    }

    public Collider getCollider() {
        return collider;
    }
}