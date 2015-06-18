package ultrasnake;

public enum TileType {

    blue("blue", true), white("white", false), NULL("blue", true);

    String textureName;
    boolean physical;

    TileType(String textureName, boolean physical) {
        this.textureName = textureName;
        this.physical = physical;

    }
}
