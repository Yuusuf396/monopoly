package upei.project.cards;
import upei.project.player.Player;


/**
 * Represents a generic "Lucky Card" in the game.
 * This is an abstract base class for all specific types of lucky cards.
 * Each lucky card has a name and a description, and it defines an abstract method
 * that subclasses must implement to specify their unique behavior.
 */
public abstract class LuckyCard {
    // The name of the lucky card
    String name;

    // The description of the lucky card
    String description;

    /**
     * Abstract method to define the action that occurs when a player lands on this card.
     * Subclasses must implement this method to specify their unique behavior.
     *
     * @param playerLanded The player who landed on or drew this lucky card.
     */
    public abstract void onLand(Player playerLanded);

    /**
     * Constructor for the LuckyCard class.
     * Initializes the lucky card with a name and description.
     *
     * @param name        The name of the lucky card (e.g., "Get Out of Jail Free").
     * @param description A short description of the lucky card's effect.
     */
    public LuckyCard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for the name of the lucky card.
     *
     * @return The name of the lucky card.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of the lucky card.
     *
     * @return The description of the lucky card.
     */
    public String getDescription() {
        return description;
    }
}

