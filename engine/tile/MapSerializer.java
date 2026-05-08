package engine.tile;

public class MapSerializer {

    public static String exportToCSV(TileMap map) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < map.getRows(); y++) {
            for (int x = 0; x < map.getCols(); x++) {
                sb.append(map.getTile(x, y).getType().getId());
                if (x < map.getCols() - 1) sb.append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void importFromCSV(TileMap map, String csvData) {
        String[] lines = csvData.split("\n");
        for (int y = 0; y < Math.min(lines.length, map.getRows()); y++) {
            String[] tokens = lines[y].trim().split(",");
            for (int x = 0; x < Math.min(tokens.length, map.getCols()); x++) {
                int id = Integer.parseInt(tokens[x]);
                map.setTile(x, y, TileType.fromId(id));
            }
        }
    }
}