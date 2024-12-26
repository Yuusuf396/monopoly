package upei.project.core.tiles;

/**
 * Represents the "Go" tile on the game board.
 * This tile is typically the starting point of the board where players begin the game.
 * Landing or passing this tile may trigger specific game mechanics such as collecting money.
 */
public class Go extends Node {

    /**
     * Constructor for the "Go" tile.
     * Initializes the tile with the name "Go".
     */
    public Go() {
        super("Go"); // Set the name of the tile as "Go"
    }
}
