package engine.tile;

import java.awt.Graphics2D;

public class TileMap {
    private final int cols;
    private final int rows;
    private final int tileSize;
    private final Tile[][] tiles;

    public TileMap(int cols, int rows, int tileSize) {
        this.cols = cols;
        this.rows = rows;
        this.tileSize = tileSize;
        this.tiles = new Tile[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                tiles[x][y] = new Tile(x, y, tileSize, TileType.EMPTY);
            }
        }
    }

    public void setTile(int x, int y, TileType type) {
        if (x >= 0 && x < cols && y >= 0 && y < rows) {
            tiles[x][y].setType(type);
        }
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < cols && y >= 0 && y < rows) {
            return tiles[x][y];
        }
        return null;
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                tiles[x][y].render(g);
            }
        }
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }
    public int getTileSize() { return tileSize; }
}