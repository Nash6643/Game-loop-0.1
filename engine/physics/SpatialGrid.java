package engine.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpatialGrid {
    private final int cellSize;
    private final Map<String, List<Collider>> grid = new HashMap<>();

    public SpatialGrid(int cellSize) {
        this.cellSize = cellSize;
    }

    public void clear() {
        grid.clear();
    }

    public void insert(Collider collider) {
        AABB bounds = collider.getBounds();
        int minX = (int) (bounds.getX() / cellSize);
        int maxX = (int) ((bounds.getX() + bounds.getWidth()) / cellSize);
        int minY = (int) (bounds.getY() / cellSize);
        int maxY = (int) ((bounds.getY() + bounds.getHeight()) / cellSize);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                String key = x + ":" + y;
                grid.computeIfAbsent(key, k -> new ArrayList<>()).add(collider);
            }
        }
    }

    public List<Collider> getNearby(Collider collider) {
        List<Collider> nearby = new ArrayList<>();
        AABB bounds = collider.getBounds();
        int minX = (int) (bounds.getX() / cellSize);
        int maxX = (int) ((bounds.getX() + bounds.getWidth()) / cellSize);
        int minY = (int) (bounds.getY() / cellSize);
        int maxY = (int) ((bounds.getY() + bounds.getHeight()) / cellSize);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                String key = x + ":" + y;
                List<Collider> cell = grid.get(key);
                if (cell != null) {
                    for (Collider c : cell) {
                        if (c != collider && !nearby.contains(c)) {
                            nearby.add(c);
                        }
                    }
                }
            }
        }
        return nearby;
    }
}