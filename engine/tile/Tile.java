package engine.tile;

import java.awt.Graphics2D;

public class Tile {
    private final int gridX;
    private final int gridY;
    private final int size;
    private TileType type;

    public Tile(int gridX, int gridY, int size, TileType type) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.size = size;
        this.type = type;
    }

    public void render(Graphics2D g) {
        if (type == TileType.EMPTY) return;
        g.setColor(type.getDefaultColor());
        g.fillRect(gridX * size, gridY * size, size, size);
        g.setColor(g.getColor().darker());
        g.drawRect(gridX * size, gridY * size, size, size);
    }

    public TileType getType() { return type; }
    public void setType(TileType type) { this.type = type; }
    public boolean isSolid() { return type.isSolid(); }
    public int getGridX() { return gridX; }
    public int getGridY() { return gridY; }
}