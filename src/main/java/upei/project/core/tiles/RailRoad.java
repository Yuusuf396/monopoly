package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents a railroad property on the game board.
 * Railroads are a specific type of property that can be owned by players
 * and generate rent income based on game-specific rules.
 */
public class RailRoad extends Property {

    /**
     * Constructor for the RailRoad class.
     * Initializes the railroad with a name and a purchase price.
     *
     * @param name  The name of the railroad (e.g., "Reading Railroad").
     * @param price The purchase price of the railroad.
     */
    public RailRoad(String name, int price) {
        super(name, price); // Call the constructor of the Property class
    }
}
