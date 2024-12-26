package upei.project.cards.chance;


import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents a "Speed Ticket" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * pays a fine of $50 for speeding when this card is drawn.
 */
public class SpeedTicket extends LuckyCard {

    /**
     * Constructor for the "Speed Ticket" card.
     * Initializes the card with a predefined title and description.
     */
    public SpeedTicket() {
        super("Speed Ticket", "Pay a $50 fine for speeding.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Deducts $50 from the player's money to simulate a speeding fine.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player has received a speeding ticket and fine
        System.out.println(playerLanded.getName() + " sped and got fined 50 bucks.");

        // Deduct $50 from the player's money
        playerLanded.updateMoney(-50);
    }
}

