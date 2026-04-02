package engine.scene;

import java.awt.Graphics2D;

public abstract class Scene {
    public abstract void init();
    public abstract void update(double deltaTime);
    public abstract void render(Graphics2D g);
    public abstract void dispose();
}