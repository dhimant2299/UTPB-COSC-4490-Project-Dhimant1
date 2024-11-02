public class MapLoader {

    private int[][] map;

    public MapLoader(int rows, int cols) {
        this.map = new int[rows][cols];

        // Fill the map with empty spaces (0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1) {
                    // Single layer of floor at top and bottom
                    map[i][j] = 1;  
                } else if (j == 0 || j == cols - 1) {
                    // Single layer of walls at left and right
                    map[i][j] = 2;  
                } else {
                    // Empty space inside
                    map[i][j] = 0;
                }
            }
        }
    }

    public int[][] getMap() {
        return this.map;
    }

    public int getTile(int row, int col) {
        return this.map[row][col];
    }
}
