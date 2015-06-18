package ultrasnake;

import static helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import java.util.ArrayList;

public class Snake {

    private int width, height, health, currentCheckpoint;
    private float speed, x, y;
    private Texture texture;
    private Tile startTile;
    private boolean first = true, alive = true, flip = true;
    private TileGrid grid;
    private ArrayList<Checkpoint> checkpoints;
    private int[] directions;

    public Snake(Texture texture, Tile startTile, TileGrid grid, int width, int height,
            float speed) {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.grid = grid;
        this.checkpoints = new ArrayList<Checkpoint>();
        this.directions = new int[2];
        //X direction
        this.directions[0] = 0;
        //Y direction
        this.directions[1] = 0;
        directions = FindNextD(startTile);
        this.currentCheckpoint = 0;
        PopulateCheckpointList();
    }

    public void Update() {
        if (isFirst()) {
            setFirst(false);
        } else {
            if (CheckpointReached()) {
                if (currentCheckpoint +1 == checkpoints.size())
                    Die();
                else
                currentCheckpoint++;
            } else {
                x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
            }
        }
    }

    private boolean CheckpointReached() {
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        //Check if position reached tile within variance of 3 (arb value)
        if (x > t.getX() - 3
                && x < t.getX() + 3
                && y > t.getY() - 3
                && y < t.getY() + 3) {
            reached = true;
            x = t.getX();
            y = t.getY();

        }

        return reached;
    }

    private void PopulateCheckpointList() {
        checkpoints.add(FindNextC(startTile, directions = FindNextD(startTile)));

        int counter = 0;
        boolean cont = true;
        while (cont) {
            int[] currentD = FindNextD(checkpoints.get(counter).getTile());
            //Check if a next direction or checkpoint exists, end after 40 checks
            if (currentD[0] == 2 || counter == 40) {
                cont = false;
            } else {
                checkpoints.add(FindNextC(checkpoints.get(counter).getTile(),
                        directions = FindNextD(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private Checkpoint FindNextC(Tile s, int[] dir) {
        Tile next = null;
        Checkpoint c = null;

        boolean found = false;
        int counter = 1;

        while (!found) {
            if (s.getXPlace() + dir[0] * counter == grid.getTilesWide() || 
                    s.getYPlace() + dir[1] * counter == grid.getTilesHigh() ||
                    s.getType() != 
                    grid.GetTile(s.getXPlace() + dir[0] * counter,
                        s.getYPlace() + dir[1] * counter).getType()) {
                found = true;
                counter -= 1;
                next = grid.GetTile(s.getXPlace() + dir[0] * counter,
                        s.getYPlace() + dir[1] * counter);
            }
            counter++;
        }
        c = new Checkpoint(next, dir[0], dir[1]);
        return c;
    }

    private int[] FindNextD(Tile s) {
        int[] dir = new int[2];
        Tile u = grid.GetTile(s.getXPlace(), s.getYPlace() - 1);
        Tile r = grid.GetTile(s.getXPlace() + 1, s.getYPlace());
        Tile d = grid.GetTile(s.getXPlace(), s.getYPlace() + 1);
        Tile l = grid.GetTile(s.getXPlace() - 1, s.getYPlace());

        if (s.getType() == u.getType() && directions[1]!= 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (s.getType() == r.getType() && directions[0]!= -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (s.getType() == d.getType() && directions[1]!= -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (s.getType() == l.getType() && directions[0]!= 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }
        return dir;
        }

    private void Die(){
        alive = false;
    }
    
    public void Draw() {
        DrawQuadTex(getTexture(), getX(), getY(), getWidth(), getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public TileGrid getTileGrid() {
        return grid;
    }
    
    public boolean isAlive(){
        return alive;
    }

}
