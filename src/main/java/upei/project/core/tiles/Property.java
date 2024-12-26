package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents a property tile on the game board.
 * Properties can be purchased by players, and rent is paid to the owner when another player lands on them.
 * The class also tracks ownership and the property's price.
 */
public class Property extends Node {

    // The purchase price of the property
    private int price;

    // The owner of the property (null if unowned)
    private Player owner;

    /**
     * Constructor for the Property class.
     * Initializes the property with a name and price.
     *
     * @param name  The name of the property (e.g., "Baltic Avenue").
     * @param price The purchase price of the property.
     */
    public Property(String name, int price) {
        super(name); // Set the name of the property
        this.price = price;
    }

    /**
     * Returns the rent for the property.
     * Rent is calculated as 20% of the property's price.
     *
     * @return The rent amount for the property.
     */
    public int getRent() {
        return price / 5; // Rent is 20% of the property price
    }

    /**
     * Defines the action that occurs when a player lands on the property.
     * - If the property is unowned, the player can choose to purchase it.
     * - If the property is owned by another player, rent is paid to the owner.
     * - If the property is owned by the player, no action is taken.
     *
     * @param player The player who landed on the property.
     */
    @Override
    public void onLand(Player player) {
        if (owner == null) {
            // Property is unowned
            System.out.println(getName() + " is unowned and costs $" + getPrice());
            if (player.getMoney() >= price && player.getStrategyType().decideToBuyProperty(player, this, 500)) {
                // Player chooses to buy the property
                player.addProperty(this);
                player.updateMoney(-price); // Deduct the property's price from the player's money
                setOwner(player); // Set the player as the owner
            } else {
                // Player chooses not to buy the property
                System.out.println(player.getName() + " chose not to purchase " + getName());
            }
        } else if (owner != player) {
            // Property is owned by another player
            System.out.println(getName() + " is owned by " + owner.getName());
            System.out.println(player.getName() + " paid $" + getRent() + " in rent to " + owner.getName());
            player.updateMoney(-getRent()); // Deduct rent from the landing player's money
            owner.updateMoney(getRent()); // Add rent to the owner's money
        } else {
            // Property is owned by the player
            System.out.println(player.getName() + " owns " + getName());
        }
    }

    /**
     * Returns the purchase price of the property.
     *
     * @return The property's price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the owner of the property.
     *
     * @param owner The player who owns the property.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns the owner of the property.
     *
     * @return The player who owns the property, or null if unowned.
     */
    public Player getOwner() {
        return owner;
    }
}
