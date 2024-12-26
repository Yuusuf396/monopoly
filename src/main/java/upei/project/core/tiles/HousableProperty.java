package upei.project.core.tiles;

import upei.project.core.Colour;
import upei.project.core.Game;
import upei.project.player.Player;
import upei.project.player.StrategyType;

/**
 * Represents a property on the game board that can have houses built on it.
 * A housable property is associated with a specific color group and supports building houses,
 * which increase the rent players must pay when they land on it.
 */
public class HousableProperty extends Property {
    private final Colour colour;
    private int houseCount;

    /**
     * Returns the color group of this property.
     *
     * @return The color group of the property.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Constructor for a housable property.
     * Initializes the property with a name, price, and color group.
     *
     * @param name  The name of the property (e.g., "Baltic Avenue").
     * @param price The purchase price of the property.
     * @param color The color group of the property.
     */
    public HousableProperty(String name, int price, Colour color) {
        super(name, price);
        this.houseCount = 0;
        this.colour = color;
    }

    @Override
    public int getRent() {
        int baseRent = getPrice() / 5;

        // Rent increases by 1.5x for each house
        return (int) (baseRent * 1.5 * houseCount);

    }

    /**
     * Allows a player to build houses on this property if they own it.
     * The player's strategy determines how many houses they attempt to buy.
     * If the player has sufficient money, the houses are built, and their money is deducted.
     *
     * @param game   The game instance.
     * @param player The player attempting to build houses.
     */
    public void buildHouse(Game game, Player player) {


        var owner = getOwner();
        if (owner == null || owner != player) {
            System.out.println(player.getName() + " cannot build a house on " + getName() + " because they do not own it");
            return;
        }
        // Get the player's strategy
        StrategyType strategy = player.getStrategyType();

        // Determine how many houses the player wants to buy
        int housesToBuy = strategy.decideToBuyHouse(player, this);

        if (housesToBuy > 0) {
            // Calculate the total cost of the houses
            int totalCost = housesToBuy * this.getPrice();

            // Check if the player has enough money
            if (player.getMoney() >= totalCost) {
                houseCount += housesToBuy; // Add the houses to the property
                player.updateMoney(-totalCost); // Deduct the cost from the player's money
                System.out.println(player.getName() + " bought " + housesToBuy + " house(s) on " + getName() + ". Total houses: " + houseCount);
            } else {
                // Notify if the player does not have enough money
                System.out.println(player.getName() + " does not have enough money to buy " + housesToBuy + " house(s) on " + getName() + ".");
            }
        } else {
            // Notify if the player decides not to build any houses
            System.out.println(player.getName() + " decided not to build any houses on " + getName() + ".");
        }

    }


}



