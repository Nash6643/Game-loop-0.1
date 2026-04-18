package engine.physics;

public class Collider {
    private final AABB bounds;
    private String tag;
    private boolean isTrigger;

    public Collider(double x, double y, double width, double height, String tag) {
        this.bounds = new AABB(x, y, width, height);
        this.tag = tag;
        this.isTrigger = false;
    }

    public boolean checkCollision(Collider other) {
        if (other == null || other == this) return false;
        return this.bounds.intersects(other.bounds);
    }

    public void updatePosition(double x, double y) {
        this.bounds.setPosition(x, y);
    }

    public AABB getBounds() { return bounds; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public boolean isTrigger() { return isTrigger; }
    public void setTrigger(boolean isTrigger) { this.isTrigger = isTrigger; }
}