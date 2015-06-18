package ultrasnake;

import java.util.ArrayList;
import static helpers.Clock.*;

public class Spawn {

    private float timeSinceLastSpawn, spawnTime;
    private Snake enemyType;
    private ArrayList<Snake> enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;

    public Spawn(Snake enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<Snake>();
        this.waveCompleted = false;

        Spawn();
    }

    public void Update() {
        boolean allEnemiesDead = true;
        if (enemyList.size() < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Snake e : enemyList) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                e.Update();
                e.Draw();
            }
        }
        if(allEnemiesDead)
            waveCompleted = true;
    }

    private void Spawn() {
        enemyList.add(new Snake(enemyType.getTexture(), enemyType.getStartTile(),
                enemyType.getTileGrid(), 32, 32, enemyType.getSpeed()));
    }

    public boolean isCompleted() {
        return waveCompleted;
    }
}
