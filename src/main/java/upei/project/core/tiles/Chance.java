package upei.project.core.tiles;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

import java.util.List;

/**
 * Represents the "Chance" tile on the game board.
 * When a player lands on this tile, they draw a card from the Chance deck,
 * which executes its specific action and is then placed back at the bottom of the deck.
 */
public class Chance extends LuckyCardNode {

    // The deck of Chance cards associated with this tile
    private List<LuckyCard> chanceDeck;

    /**
     * Constructor for the Chance tile.
     * Initializes the tile with the given Chance card deck.
     *
     * @param chanceDeck The deck of Chance cards to be used.
     */
    public Chance(List<LuckyCard> chanceDeck) {
        super("Chance"); // Set the name of the tile as "Chance"
        this.chanceDeck = chanceDeck;
    }

    /**
     * Defines the action that occurs when a player lands on the Chance tile.
     * Draws the top card from the Chance deck, executes its action, and moves it to the bottom of the deck.
     *
     * @param player The player who landed on the tile.
     */
    @Override
    public void onLand(Player player) {
        if (!chanceDeck.isEmpty()) {
            // Draw the top card from the Chance deck
            LuckyCard card = chanceDeck.remove(0);

            // Add the drawn card back to the bottom of the deck
            chanceDeck.add(card);

            // Print the name of the drawn card and execute its action
            System.out.println("Drawing card: " + card.getName());
            card.onLand(player);
        } else {
            // Notify if the Chance deck is empty
            System.out.println("Chance deck is empty!");
        }
    }
}
