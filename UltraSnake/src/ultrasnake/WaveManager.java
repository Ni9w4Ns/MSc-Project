package ultrasnake;

public class WaveManager {
    
    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Snake enemyType;
    private Spawn currentWave;
    
    public WaveManager(Snake enemyType, float timeBetweenEnemies, int enemiesPerWave){
        this.enemyType = enemyType;
        this.enemiesPerWave = enemiesPerWave;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        
        newWave();
    }
    
    public void update(){
        if(!currentWave.isCompleted())
            currentWave.Update();
        else
            newWave();

    }
    
    private void newWave(){
        currentWave = new Spawn(enemyType, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave " + waveNumber);
    }
}