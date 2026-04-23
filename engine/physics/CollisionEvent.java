package engine.physics;

public class CollisionEvent {
    private final Collider source;
    private final Collider target;
    private final double timestamp;

    public CollisionEvent(Collider source, Collider target, double timestamp) {
        this.source = source;
        this.target = target;
        this.timestamp = timestamp;
    }

    public Collider getSource() {
        return source;
    }

    public Collider getTarget() {
        return target;
    }

    public double getTimestamp() {
        return timestamp;
    }
}