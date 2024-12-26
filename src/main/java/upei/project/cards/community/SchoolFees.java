package upei.project.cards.community;


import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "School Fees" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * pays $150 as school fees when this card is drawn.
 */
public class SchoolFees extends LuckyCard {

    /**
     * Constructor for the "School Fees" card.
     * Initializes the card with a predefined title and description.
     */
    public SchoolFees() {
        super("School Fees", "Pay $150 in school fees.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Deducts $150 from the player's money to simulate paying school fees.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player is paying $150 in school fees
        System.out.println(playerLanded.getName() + " paid $150 in school fees.");

        // Deduct $150 from the player's money
        playerLanded.updateMoney(-150);
    }
}

