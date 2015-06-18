package ultrasnake;

import static helpers.Artist.QuickLoad;

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    //Temp variables
    public Game(int[][] map) {
        grid = new TileGrid(map);
        player = new Player(grid);
        waveManager = new WaveManager(new Snake(QuickLoad("snake"), grid.GetTile(20, 20), grid, 32, 32, 96),
                0.33f, 5);
    }

    public void update() {
        grid.Draw();
        waveManager.update();
        player.Update();

    }
}
