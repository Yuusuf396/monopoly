package upei.project.cards.community;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents an "Advance to Go" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * moves directly to the "Go" space and collects $200 when this card is drawn.
 */
public class AdvanceToGo extends LuckyCard {

    /**
     * Constructor for the "Advance to Go" card.
     * Initializes the card with a predefined title and description.
     */
    public AdvanceToGo() {
        super("Advance to Go", "Move directly to Go and collect $200.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Moves the player to the "Go" space (position 0 on the board)
     * and awards them $200.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player is advancing to "Go" and collecting $200
        System.out.println(playerLanded.getName() + " advances directly to Go and collects $200.");

        // Move the player to the "Go" position (index 0 on the board)
        playerLanded.setPosition(0);

        // Award the player $200 for passing "Go"
        playerLanded.updateMoney(200);
    }
}

