package ultrasnake;

import org.lwjgl.input.Mouse;
import static helpers.Artist.*;
import org.lwjgl.input.Keyboard;

public class Player {
    
    private TileGrid grid;
    private TileType[] types;
    private int index;
    
    public Player(TileGrid grid){
        this.grid = grid;
        this.types = new TileType[2];
        this.types[0] = TileType.blue;
        this.types[1] = TileType.white;
        //this.types[2] = TileType.Water;
        this.index = 0;
    }
    
    public void SetTile(){
        grid.SetTile((int)(Math.floor(Mouse.getX()/32)),
                (int)Math.floor((HEIGHT - Mouse.getY() -1) /32), types[index]);
    }
    
    public void Update(){
        if(Mouse.isButtonDown(0)){
            SetTile();
        }
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
            MoveIndex();
        }
        }
    }
    
    private void MoveIndex(){
        index++;
        if(index > types.length - 1){
            index = 0;
        }
    }
}
