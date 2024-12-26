package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents a tax tile on the game board.
 * When a player lands on this tile, they are required to pay a specified amount in taxes.
 */
public class Tax extends Node {

    // The amount of money the player must pay as taxes
    private final int taxAmount;

    /**
     * Constructor for the Tax class.
     * Initializes the tax tile with a name and a specific tax amount.
     *
     * @param name      The name of the tax tile (e.g., "Income Tax", "Luxury Tax").
     * @param taxAmount The amount of money the player must pay when landing on this tile.
     */
    public Tax(String name, int taxAmount) {
        super(name); // Set the name of the tax tile
        this.taxAmount = taxAmount;
    }

    /**
     * Defines the action that occurs when a player lands on the tax tile.
     * Deducts the specified tax amount from the player's money.
     *
     * @param player The player who landed on the tile.
     */
    @Override
    public void onLand(Player player) {
        // Notify the player about the tax payment
        System.out.println(player.getName() + " pays $" + taxAmount + " in taxes.");

        // Deduct the tax amount from the player's money
        player.updateMoney(-taxAmount);
    }
}
