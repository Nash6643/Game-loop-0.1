package engine.entity;

public enum EntityState {
    IDLE,
    MOVING,
    COLLIDING,
    DISABLED;

    public boolean canMove() {
        return this == IDLE || this == MOVING;
    }
}