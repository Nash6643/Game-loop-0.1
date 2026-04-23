package engine.physics;

import java.awt.Color;
import java.awt.Graphics2D;

public class TriggerZone {
    private final Collider collider;
    private final Color zoneColor;
    private boolean active = true;

    public TriggerZone(double x, double y, double width, double height, String tag, Color color) {
        this.collider = new Collider(x, y, width, height, tag);
        this.collider.setTrigger(true);
        this.zoneColor = color;
    }

    public boolean checkTrigger(Collider target) {
        if (!active) return false;
        return collider.checkCollision(target);
    }

    public void render(Graphics2D g) {
        if (!active) return;
        AABB b = collider.getBounds();
        g.setColor(new Color(zoneColor.getRed(), zoneColor.getGreen(), zoneColor.getBlue(), 80));
        g.fillRect((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
        
        g.setColor(zoneColor);
        g.drawRect((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
    }

    public void setActive(boolean active) { this.active = active; }
    public boolean isActive() { return active; }
    public Collider getCollider() { return collider; }
}