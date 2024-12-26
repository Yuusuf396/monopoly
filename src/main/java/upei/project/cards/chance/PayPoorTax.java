package upei.project.cards.chance;


import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "Poor Tax" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * pays $15 as a Poor Tax when this card is drawn.
 */
public class PayPoorTax extends LuckyCard {
    /**
     * Constructor for the "Poor Tax" card.
     * Initializes the card with a predefined title and description.
     */
    public PayPoorTax() {
        super("Poor Tax", "Pay $15 as a Poor Tax.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Deducts $15 from the player's money to simulate paying the Poor Tax.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player is paying the Poor Tax
        System.out.println(playerLanded.getName() + " paid $15 in Poor Tax.");

        // Deduct $15 from the player's money
        playerLanded.updateMoney(-15);
    }
}


