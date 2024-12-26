package upei.project.cards.chance;

import upei.project.cards.LuckyCard;
import upei.project.core.tiles.Jail;
import upei.project.player.Player;

/**
 * Represents a "Go to Jail" lucky card in the game.
 * When drawn, this card sends the player directly to jail.
 */
public class GoToJail extends LuckyCard {

    /**
     * Constructor for the "Go to Jail" card.
     * Initializes the card with a predefined title and description.
     */
    public GoToJail() {
        super("Go to Jail", "Go directly to jail. Do not pass Go. Do not collect $200.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Moves the player directly to jail and updates their status in the game.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        System.out.println(playerLanded.getName() + " drew a 'Go to Jail' card!");

        // Retrieve the Jail node from the game board
        Jail jailNode = (Jail) playerLanded.getGame().getBoard().getJail();

        // Validate that the Jail node exists
        if (jailNode == null) {
            System.out.println("Error: Jail node not found on the board!");
            return;
        }

        // Move the player to the jail node's position on the board
        playerLanded.setPosition(jailNode.getIndex(playerLanded.getGame()));

        // Mark the player as being in jail
        playerLanded.setInJail(true);

        // Reset the player's jail turn counter
        playerLanded.setJailTurns(0);

        // Print confirmation of the player's new status
        System.out.println(playerLanded.getName() + " is now in Jail at position "
                + jailNode.getIndex(playerLanded.getGame()) + "!");
    }
}

