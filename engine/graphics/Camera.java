package engine.graphics;

public class Camera {
    private double x;
    private double y;
    private final int viewportWidth;
    private final int viewportHeight;

    public Camera(int viewportWidth, int viewportHeight) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.x = 0;
        this.y = 0;
    }

    public void follow(double targetX, double targetY, double lerpFactor) {
        double destX = targetX - (viewportWidth / 2.0);
        double destY = targetY - (viewportHeight / 2.0);

        this.x += (destX - this.x) * lerpFactor;
        this.y += (destY - this.y) * lerpFactor;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setPosition(double x, double y) { this.x = x; this.y = y; }
}