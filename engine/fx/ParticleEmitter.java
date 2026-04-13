package engine.fx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleEmitter {
    private final List<Particle> particles = new ArrayList<>();
    private boolean enabled = true;

    public void emit(double x, double y, Color color, int count) {
        if (!enabled) return;
        for (int i = 0; i < count; i++) {
            double vx = (Math.random() - 0.5) * 80;
            double vy = (Math.random() - 0.5) * 80;
            particles.add(new Particle(x, y, vx, vy, color));
        }
    }

    public void update(double deltaTime) {
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            p.update(deltaTime);
            if (p.isDead()) {
                it.remove();
            }
        }
    }

    public void render(Graphics2D g) {
        for (Particle p : particles) {
            p.render(g);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void clear() {
        particles.clear();
    }
}