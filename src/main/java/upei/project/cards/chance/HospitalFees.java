package upei.project.cards.chance;


import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "Hospital Fees" lucky card in the game.
 * When drawn, this card requires the player to pay $100 in hospital fees.
 */
public class HospitalFees extends LuckyCard {

    /**
     * Constructor for the "Hospital Fees" card.
     * Initializes the card with a predefined title and description.
     */
    public HospitalFees() {
        super("Hospital Fees", "Pay $100 in hospital fees.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Deducts $100 from the player's money to simulate paying hospital fees.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player is paying hospital fees
        System.out.println(playerLanded.getName() + " paid $100 in hospital fees.");

        // Deduct $100 from the player's money
        playerLanded.updateMoney(-100);
    }
}

