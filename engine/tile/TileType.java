package engine.tile;

import java.awt.Color;

public enum TileType {
    EMPTY(0, false, Color.BLACK),
    GRASS(1, false, new Color(34, 139, 34)),
    DIRT(2, false, new Color(139, 69, 19)),
    STONE_WALL(3, true, new Color(105, 105, 105)),
    WATER(4, true, new Color(30, 144, 255));

    private final int id;
    private final boolean solid;
    private final Color defaultColor;

    TileType(int id, boolean solid, Color defaultColor) {
        this.id = id;
        this.solid = solid;
        this.defaultColor = defaultColor;
    }

    public int getId() { return id; }
    public boolean isSolid() { return solid; }
    public Color getDefaultColor() { return defaultColor; }

    public static TileType fromId(int id) {
        for (TileType type : values()) {
            if (type.id == id) return type;
        }
        return EMPTY;
    }
}