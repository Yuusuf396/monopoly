package upei.project.cards.community;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "Get Out of Jail Free" lucky card in the game.
 * This class inherits from LuckyCard and allows the player to obtain
 * a "Get Out of Jail Free" card, which they can keep or sell.
 */
public class GetOutOfJail extends LuckyCard {

    /**
     * Constructor for the "Get Out of Jail Free" card.
     * Initializes the card with a predefined title and description.
     */
    public GetOutOfJail() {
        super("Get Out of Jail Free", "Keep this card until needed or sell it. This allows you to get out of jail without paying.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Adds a "Get Out of Jail Free" card to the player's inventory.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Add the "Get Out of Jail Free" card to the player's inventory
        playerLanded.addGetOutOfJailFreeCard();
    }
}

