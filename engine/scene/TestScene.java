package engine.scene;

import engine.entity.EntityState;
import engine.fx.ParticleEmitter;
import engine.graphics.Camera;
import engine.input.InputManager;
import engine.physics.AABB;
import engine.physics.Collider;
import engine.physics.Obstacle;
import engine.physics.TriggerZone;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class TestScene extends Scene {
    private double xPos = 400;
    private double yPos = 300;
    private double speed = 250.0;
    private int entitySize = 40;
    private Color entityColor = Color.CYAN;
    
    private EntityState state = EntityState.IDLE;
    private boolean debugDrawColliders = true;
    private final InputManager input;
    private final ParticleEmitter emitter = new ParticleEmitter();
    private final Collider playerCollider;
    private final Camera camera = new Camera(800, 600);

    private final List<Obstacle> obstacles = new ArrayList<>();
    private final List<TriggerZone> triggerZones = new ArrayList<>();

    public TestScene(InputManager input) {
        this.input = input;
        int halfSize = entitySize / 2;
        this.playerCollider = new Collider(xPos - halfSize, yPos - halfSize, entitySize, entitySize, "Player");

        // Populate test obstacles and triggers
        obstacles.add(new Obstacle(200, 150, 100, 100, new Color(100, 100, 100)));
        obstacles.add(new Obstacle(500, 350, 120, 80, new Color(100, 100, 100)));
        triggerZones.add(new TriggerZone(350, 100, 80, 80, "Checkpoint", Color.YELLOW));
    }

    @Override
    public void init() {}

    @Override
    public void update(double deltaTime) {
        state = EntityState.IDLE;
        boolean moving = false;
        double oldX = xPos;
        double oldY = yPos;

        if (input != null && state.canMove()) {
            if (input.isKeyPressed(KeyEvent.VK_W) || input.isKeyPressed(KeyEvent.VK_UP)) { yPos -= speed * deltaTime; moving = true; }
            if (input.isKeyPressed(KeyEvent.VK_S) || input.isKeyPressed(KeyEvent.VK_DOWN)) { yPos += speed * deltaTime; moving = true; }
            if (input.isKeyPressed(KeyEvent.VK_A) || input.isKeyPressed(KeyEvent.VK_LEFT)) { xPos -= speed * deltaTime; moving = true; }
            if (input.isKeyPressed(KeyEvent.VK_D) || input.isKeyPressed(KeyEvent.VK_RIGHT)) { xPos += speed * deltaTime; moving = true; }
        }

        if (moving) state = EntityState.MOVING;

        // Screen Boundary Clamping
        int halfSize = entitySize / 2;
        if (xPos - halfSize < 0) { xPos = halfSize; state = EntityState.COLLIDING; }
        if (xPos + halfSize > 800) { xPos = 800 - halfSize; state = EntityState.COLLIDING; }
        if (yPos - halfSize < 0) { yPos = halfSize; state = EntityState.COLLIDING; }
        if (yPos + halfSize > 600) { yPos = 600 - halfSize; state = EntityState.COLLIDING; }

        playerCollider.updatePosition(xPos - halfSize, yPos - halfSize);
        playerCollider.getBounds().setSize(entitySize, entitySize);

        // Obstacle Collision Checks
        for (Obstacle obs : obstacles) {
            if (playerCollider.checkCollision(obs.getCollider())) {
                state = EntityState.COLLIDING;
                xPos = oldX;
                yPos = oldY;
                playerCollider.updatePosition(xPos - halfSize, yPos - halfSize);
                break;
            }
        }

        // Trigger Checks
        for (TriggerZone zone : triggerZones) {
            if (zone.checkTrigger(playerCollider)) {
                emitter.emit(xPos, yPos, Color.YELLOW, 1);
            }
        }

        if (moving && state != EntityState.COLLIDING) {
            emitter.emit(xPos, yPos, entityColor, 2);
        }

        // Update Camera Viewport Target
        camera.follow(xPos, yPos, 0.1);

        emitter.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, 800, 600);

        AffineTransform originalTransform = g.getTransform();
        g.translate(-camera.getX(), -camera.getY());

        // Render world objects with camera transform
        for (TriggerZone zone : triggerZones) zone.render(g);
        for (Obstacle obs : obstacles) obs.render(g);

        emitter.render(g);

        int halfSize = entitySize / 2;
        g.setColor(state == EntityState.COLLIDING ? Color.RED : entityColor);
        g.fillOval((int) xPos - halfSize, (int) yPos - halfSize, entitySize, entitySize);

        if (debugDrawColliders) {
            AABB bounds = playerCollider.getBounds();
            g.setColor(Color.GREEN);
            g.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
        }

        g.setTransform(originalTransform);
    }

    @Override
    public void dispose() {
        emitter.clear();
    }

    // Getters and Setters
    public void setSpeed(double speed) { this.speed = speed; }
    public double getSpeed() { return speed; }
    public void setEntitySize(int size) { this.entitySize = size; }
    public int getEntitySize() { return entitySize; }
    public void setEntityColor(Color color) { this.entityColor = color; }
    public ParticleEmitter getEmitter() { return emitter; }
    public Collider getPlayerCollider() { return playerCollider; }
    public EntityState getState() { return state; }
    public Camera getCamera() { return camera; }
    public void setDebugDrawColliders(boolean debugDrawColliders) { this.debugDrawColliders = debugDrawColliders; }
}