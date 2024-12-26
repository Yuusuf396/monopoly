package upei.project.player;

import upei.project.core.Game;
import upei.project.core.tiles.HousableProperty;
import upei.project.core.tiles.Node;
import upei.project.core.tiles.Property;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a player in the game.
 * Tracks the player's position, money, properties, jail status, and strategy type.
 * Manages interactions with the game, such as movement, property acquisition, and building houses.
 */
public class Player {
    private Game game;
    private String name;
    private int position;
    private int money;
    private boolean inJail = false;
    private int jailTurns = 0;
    private int getOutOfJailFreeCards = 0;
  // List to track owned properties
    private StrategyType strategyType;
    private List<String> targetColors; // Track targeted color sets for conservative strategy

    /**
     * Constructor for the Player class.
     * Initializes the player's name, game instance, strategy type, and position.
     *
     * @param name         The player's name.
     * @param game         The game instance the player is part of.
     * @param strategyType The player's strategy type.
     */
    public Player(String name,  Game game, StrategyType strategyType) {
        this.name = name;
        this.position = 0;
        this.game = game;
        this.strategyType = strategyType;
       
        this.targetColors = new ArrayList<>();
//        System.out.println(name + "'s gaame piece is " + gamePiece.getPieceName() + " with strategy " + strategyType);
    }

    // ----------------------- Money Management -----------------------

    /**
     * Updates the player's money by the specified amount.
     * If the player's money falls below zero, they are considered bankrupt and removed from the game.
     *
     * @param amount The amount to add (positive) or deduct (negative) from the player's balance.
     */
    public void updateMoney(int amount) {
        this.money += amount;
        System.out.println(name + " now has $" + this.money);
        if (this.money < 0) {
            this.money = 0; // Prevent negative balance
            System.out.println(name + " is bankrupt! they have lost!");
            game.removePlayer(this); // Trigger loss condition
        }

    }
    /**
     * Returns the player's current money balance.
     *
     * @return The player's money balance.
     */
    public int getMoney()
    {
        return this.money;
    }

    /**
     * Sets the player's money to a specific amount.
     *
     * @param money The new money balance for the player.
     */
    public void setMoney(int money)
    {
        this.money = money;
    }

// ----------------------- Jail Handling -----------------------

    /**
     * Checks whether the player is in jail.
     *
     * @return True if the player is in jail, false otherwise.
     */
    public boolean isInJail()
    {
        return this.inJail;
    }

    /**
     * Sets the player's jail status.
     *
     * @param inJail True to mark the player as in jail, false otherwise.
     */
    public void setInJail(boolean inJail)
    {
        this.inJail = inJail;
    }

    /**
     * Returns the number of turns the player has spent in jail.
     *
     * @return The number of jail turns.
     */
    public int getJailTurns()
    {
        return this.jailTurns;
    }

    /**
     * Sets the number of turns the player has spent in jail.
     *
     * @param turns The number of jail turns to set.
     */
    public void setJailTurns(int turns)
    {
        this.jailTurns = turns;
    }

    /**
     * Returns the number of "Get Out of Jail Free" cards the player possesses.
     *
     * @return The number of cards.
     */
    public int getOutOfJailFreeCards()
    {
        return this.getOutOfJailFreeCards;
    }

    /**
     * Adds a "Get Out of Jail Free" card to the player's inventory.
     */
    public void addGetOutOfJailFreeCard() {
        this.getOutOfJailFreeCards++;
        System.out.println(name + " received a 'Get Out of Jail Free' card. Total cards: " + this.getOutOfJailFreeCards);
    }

    /**
     * Uses a "Get Out of Jail Free" card, if available.
     * Reduces the number of cards by one and notifies the player.
     */
    public void useGetOutOfJailFreeCard() {
        if (this.getOutOfJailFreeCards > 0) {
            this.getOutOfJailFreeCards--;
            System.out.println(name + " used a 'Get Out of Jail Free' card. Remaining cards: " + this.getOutOfJailFreeCards);
        } else {
            System.out.println(name + " has no 'Get Out of Jail Free' cards to use.");
        }
    }


    // ----------------------- Movement -----------------------

    /**
     * Handles the player's reward for passing "Go."
     * Adds $200 to the player's balance.
     */
    public void onGoPassed(){
        System.out.println(getName() +" got 200 dollars for passing go");
        updateMoney(200);
    }


    public Node getCurrentNode(){
        for(var i : game.getBoard().getNodes()){
            if(i.getIndex(game) == position){
                return i;
            }
        }
        return null;
    }

    // /**
    //     * Moves the player forward by the specified number of steps.
    //     * If the player passes "Go," they receive $200.
    //     *
    //     * @param steps The number of steps to move.
    //     */
    public void move(int steps) {

        for (int i = 0; i < steps; i++) {
            this.position++;
            if(this.position >= getGame().getBoard().getNodesCount()){
                this.position = 0;

                onGoPassed();

            }
        }

    }

    /**
     * Sets the player's position on the board.
     * If the new position crosses "Go," the player receives $200.
     *
     * @param position The new position to set.
     */
    public void setPosition(int position) {
        // Handle "passing Go" logic
        if (position < this.position) {
            onGoPassed(); // Player passed Go when moving backward
        }
        this.position = position % getGame().getBoard().getNodesCount(); // Ensure position wraps around the board
        System.out.println(this.name + " is now at position " + position + " (" + getCurrentNode().getName() + ")");
    }

    public int getPosition() {
        return this.position;
    }


    // Getters and Setters
    public Game getGame() {
        return this.game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return this.name;
    }


    // ----------------------- Property Management -----------------------

    /**
     * Returns the list of properties owned by the player.
     *
     * @return A list of properties owned by the player.
     */
    public List<Property> getProperties(){
        List<Property> properties = new ArrayList<>();
        for(var i : Arrays.stream(game.getBoard().getNodes())
                .filter(i -> i instanceof Property property && property.getOwner() == this ).toArray()){
            properties.add((Property) i);
        }
        return properties;
    }
    public StrategyType getStrategyType()
    {
        return this.strategyType;
    }

    /**
     * Returns the cheapest housable property owned by the player for building houses.
     *
     * @param game The game instance.
     * @return The cheapest housable property, or null if none exist.
     */
    public HousableProperty getCheapestHouseToBuild(Game game) {
        HousableProperty cheapestProperty = null;
        int cheapestPrice = Integer.MAX_VALUE;

        // Iterate through all owned properties
        for (Property property : getProperties()) {

            if (property instanceof HousableProperty housableProperty) {
                // Find the property with the lowest price
                if (housableProperty.getPrice() < cheapestPrice) {
                    cheapestPrice = housableProperty.getPrice();
                    cheapestProperty = housableProperty;
                }
            }
        }

        return cheapestProperty;
    }

    /**
     * Acquires ownership of a property if it is unowned.
     *
     * @param property The property to acquire.
     */
    public void addProperty(Property property) {
            // Check if the property is already owned
            if (property.getOwner() != null) {
                System.out.println(name + " cannot acquire " + property.getName() +
                        " because it is already owned by " + property.getOwner().getName());
                return; // Exit the method without adding the property
            }

            // Set the current player as the owner
            property.setOwner(this);
            System.out.println(name + " acquired " + property.getName() + " (Price: $" + property.getPrice() + ")");

    }

    /**
     * Builds a house on a property owned by the player.
     *
     * @param game     The game instance.
     * @param property The property to build a house on.
     */
    public void buildHouseOnProperty(Game game, HousableProperty property) {
        // Check if the property belongs to the player
        if (!getProperties().contains(property)) {
            System.out.println(name + " cannot build a house on " + property.getName() + " because they do not own it.");
            return;
        }

        // Call the property's buildHouse method
        property.buildHouse(game, this);
    }



}