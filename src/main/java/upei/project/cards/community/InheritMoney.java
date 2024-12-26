package upei.project.cards.community;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

/**
 * Represents an "Inheritance" lucky card in the game.
 * This class inherits from LuckyCard and specifies behavior where the player
 * receives $100 as an inheritance when this card is drawn.
 */
public class InheritMoney extends LuckyCard {

    /**
     * Constructor for the "Inheritance" card.
     * Initializes the card with a predefined title and description.
     */
    public InheritMoney() {
        super("Inheritance", "You inherit $100.");
    }

    /**
     * Handles the event when a player lands on this card.
     * Adds $100 to the player's money to simulate receiving an inheritance.
     *
     * @param playerLanded The player who landed on this card.
     */
    @Override
    public void onLand(Player playerLanded) {
        // Notify that the player has received $100 as an inheritance
        System.out.println(playerLanded.getName() + " inherited $100!");

        // Add $100 to the player's money
        playerLanded.updateMoney(100);
    }
}

