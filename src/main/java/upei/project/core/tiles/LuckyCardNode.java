package upei.project.core.tiles;

/**
 * Represents a type of node on the game board that involves a lucky card interaction.
 * This class extends the generic Node class and includes an optional price attribute.
 */
public class LuckyCardNode extends Node {

    // The price associated with this LuckyCardNode (if applicable)
    int price;

    /**
     * Constructor for the LuckyCardNode class.
     * Initializes the node with a name and an optional price.
     *
     * @param name The name of the node (e.g., "Chance" or "Community Chest").
     */
    public LuckyCardNode(String name) {
        super(name); // Set the name of the node
        this.price = price; // Initialize the price (optional)
    }

    /**
     * Returns the price associated with this LuckyCardNode.
     *
     * @return The price of the node.
     */
    public int getPrice() {
        return price;
    }
}
