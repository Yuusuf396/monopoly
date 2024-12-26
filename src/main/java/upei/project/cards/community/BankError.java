package upei.project.cards.community;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "Bank Error In Your Favor" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * receives $200 due to a bank error when this card is drawn.
 */
public class BankError extends LuckyCard {

    /**
     * Constructor for the "Bank Error In Your Favor" card.
     * Initializes the card with a predefined title and description.
     */
    public BankError() {
        super("Bank Error In Your Favor", "Collect $200 due to a bank error in your favor.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Adds $200 to the player's money to simulate receiving money from a bank error.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player is receiving $200 due to a bank error
        System.out.println(playerLanded.getName() + " received $200 due to a bank error in their favor.");

        // Add $200 to the player's account
        playerLanded.updateMoney(200);
    }
}
