package upei.project.core.tiles;

import upei.project.cards.LuckyCard;
import upei.project.player.Player;

import java.util.List;

/**
 * Represents the "Community Chest" tile on the game board.
 * When a player lands on this tile, they draw a card from the Community Chest deck,
 * which executes its specific action and is then placed back at the bottom of the deck.
 */
public class CommunityChest extends LuckyCardNode {
    private List<LuckyCard> communityChestDeck;

    /**
     * Constructor for the Community Chest tile.
     * Initializes the tile with the given Community Chest card deck.
     *
     * @param chestDeck The deck of Community Chest cards to be used.
     */
    public CommunityChest(List<LuckyCard> chestDeck) {
        super("Community Chest");
        this.communityChestDeck = chestDeck;
    }

    /**
     * Defines the action that occurs when a player lands on the Community Chest tile.
     * Draws the top card from the Community Chest deck, executes its action,
     * and returns it to the bottom of the deck.
     *
     * @param player The player who landed on the tile.
     */
    @Override
    public void onLand(Player player) {

        if (!communityChestDeck.isEmpty()) {
            // Draw the top card from the Community Chest deck
            LuckyCard card = communityChestDeck.remove(0);
            // Draw the top card
            communityChestDeck.add(card); // Return it to the bottom of the deck
            System.out.println("Drawing card: " + card.getName());
            card.onLand(player); // Execute card action
        } else {
            System.out.println("Community Chest deck is empty!");
        }
    }
}
