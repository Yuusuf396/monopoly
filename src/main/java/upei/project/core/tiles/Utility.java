package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents a utility property on the game board.
 * Utilities are a specific type of property where rent is calculated dynamically
 * based on a dice roll and a base rent multiplier.
 */
public class Utility extends Property {

    // The purchase price of the utility
    private int price;

    // The base rent multiplier for the utility
    private int baseRent;

    // The initial rent value (derived from price)
    private int rent;

    /**
     * Constructor for the Utility class.
     * Initializes the utility with a name, purchase price, and calculates the initial rent.
     *
     * @param name  The name of the utility (e.g., "Electric Company").
     * @param price The purchase price of the utility.
     */
    public Utility(String name, int price) {
        super(name, price); // Call the constructor of the Property class
        this.rent = price / 10; // Set the initial rent as 10% of the price
        this.baseRent = rent; // Base rent is initially set to the calculated rent
    }

    /**
     * Returns the purchase price of the utility.
     *
     * @return The utility's price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Calculates the rent based on the dice roll.
     * Rent is determined by multiplying the base rent with the dice roll.
     *
     * @param diceRoll The result of the dice roll used to calculate rent.
     * @return The calculated rent amount.
     */
    public int calculateRent(int diceRoll) {
        return baseRent * diceRoll;
    }
}
