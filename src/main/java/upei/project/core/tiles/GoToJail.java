package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents the "Go to Jail" tile on the game board.
 * When a player lands on this tile, they are sent directly to Jail, bypassing other actions.
 */
public class GoToJail extends Node {

    /**
     * Constructor for the "Go to Jail" tile.
     * Initializes the tile with the name "Go to Jail".
     */
    public GoToJail() {
        super("Go to Jail");
    }

    /**
     * Defines the action that occurs when a player lands on the "Go to Jail" tile.
     * The player is sent directly to the Jail node on the board, and the Jail's behavior is triggered.
     *
     * @param player The player who landed on the tile.
     */
    @Override
    public void onLand(Player player) {
        // Notify that the player is being sent to Jail
        System.out.println(player.getName() + " is sent to Jail!");

        // Move the player directly to the Jail node
        player.setPosition(player.getGame().getBoard().getJail().getIndex(player.getGame()));

        // Trigger the Jail node's behavior for the player
        player.getGame().getBoard().getJail().onLand(player);
    }
}
