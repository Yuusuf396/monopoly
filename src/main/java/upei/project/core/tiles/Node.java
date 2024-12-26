package upei.project.core.tiles;

import upei.project.core.Game;
import upei.project.player.Player;

/**
 * Represents a generic node (tile) on the game board.
 * This is the base class for all specific types of nodes, such as properties, Jail, or lucky card nodes.
 */
public class Node {

    // The name of the node (e.g., "Go", "Jail", "Chance")
    String name;

    /**
     * Constructor for the Node class.
     * Initializes the node with the given name.
     *
     * @param name The name of the node.
     */
    public Node(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the node.
     *
     * @return The name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Finds and returns the index of this node on the game board.
     * If the node is not found on the board, it returns -1.
     *
     * @param game The current game instance, used to access the board.
     * @return The index of this node on the board, or -1 if not found.
     */
    public int getIndex(Game game) {
        var nodes = game.getBoard().getNodes(); // Get all nodes from the board
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i] == this) {
                return i; // Return the index if this node matches
            }
        }
        return -1; // Return -1 if the node is not found
    }

    /**
     * Defines the action that occurs when a player lands on this node.
     * This method is meant to be overridden by subclasses to implement specific behavior.
     *
     * @param player The player who landed on the node.
     */
    public void onLand(Player player) {
        // Default behavior: No specific action (can be overridden by subclasses)
    }
}
