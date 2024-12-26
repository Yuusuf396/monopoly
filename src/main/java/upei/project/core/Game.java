package upei.project.core;

import upei.project.cards.LuckyCard;
import upei.project.cards.chance.HospitalFees;
import upei.project.cards.chance.PayPoorTax;
import upei.project.cards.chance.SpeedTicket;
import upei.project.cards.community.*;
import upei.project.core.tiles.Node;
import upei.project.core.tiles.Property;
import upei.project.player.Player;
import upei.project.player.StrategyType;

import java.util.*;

/**
 * Represents the main game logic and flow.
 * Manages players, the game board, turns, card decks, and determines the winner.
 */
public class Game {
    private final Board board;
    private final List<Player> players;
    private final Random dice;
    private final Scanner scanner;
    private final List<LuckyCard> chanceCards;
    private final List<LuckyCard> communityChestCards;
    private final int startingMoney;
    private final int bailAmount;
    private int turnCounter;
    private final  int maxTurns=500;

    /**
     * Returns the game board.
     *
     * @return The board object.
     */
    public Board getBoard()
    {
        return board;

    }

    /**
     * Constructor for the Game class.
     * Initializes all components of the game, including players, card decks, and the board.
     */
    public Game() {

        this.players = new ArrayList<>();
        this.dice = new Random();
        this.scanner = new Scanner(System.in);
        this.chanceCards = new ArrayList<>();
        this.communityChestCards = new ArrayList<>();
        this.startingMoney = 600;
        this.bailAmount = 500;
        this.turnCounter=0;

        initializeCards();
        this.board = new Board(chanceCards,communityChestCards);
        printGameParameters();
    }

    /**
     * Initializes the Chance and Community Chest card decks with predefined cards.
     * Shuffles the decks after adding cards.
     */
    private void initializeCards() {

        // Example cards
        chanceCards.add(new SpeedTicket());
        chanceCards.add(new HospitalFees());
        chanceCards.add(new PayPoorTax());
        chanceCards.add(new SpeedTicket());


        communityChestCards.add(new AdvanceToGo());
        communityChestCards.add(new BankError());
        communityChestCards.add(new GetOutOfJail());
        communityChestCards.add(new InheritMoney());
        communityChestCards.add(new SchoolFees());


        shuffleDeck(chanceCards);
        shuffleDeck(communityChestCards);
    }

    // Shuffle the deck of cards
    private void shuffleDeck(List<LuckyCard> deck) {
        Collections.shuffle(deck);
    }

    // Print initial game parameters
    private void printGameParameters() {
        System.out.println("Starting money: $" + startingMoney);
        System.out.println("Bail amount: $" + bailAmount);
    }


    /**
     * Starts the game and runs until there is only one player left or the maximum number of turns is reached.
     */
    public void start() {
        while (players.size() > 1 && turnCounter < maxTurns) {
            executeTurnsForAllPlayers();
            removeBankruptPlayers();
            turnCounter++;
        }
        finalizeGame();
    }

    /**
     * Executes a turn for each player in the game.
     * A copy of the player list is used to avoid modification issues during iteration.
     */
    private void executeTurnsForAllPlayers() {
        for (Player player : new ArrayList<>(players)) { // Used a copy to avoid modification issues
            System.out.println("------------------------------------------------------");
            System.out.println("\nTurn " + (turnCounter + 1) + " for " + player.getName());
            takeTurn(player); // Handle the player's turn
        }
    }

    /**
     * Removes players with zero or negative money from the game and handles their bankruptcy.
     */
    private void removeBankruptPlayers() {

        for (var i : players.toArray()) {

            var player = (Player) i;
            if (player.getMoney() <= 0) {
                handleBankruptcy(player); // Handle the player's bankruptcy
                players.remove(player); // Remove the player from the game
                System.out.println(player.getName() + " has been removed from the game due to bankruptcy.");
            }
        }
    }

    /**
     * Finalizes the game by announcing the winner or determining the winner if the game ends due to max turns.
     */
    private void finalizeGame() {
        if (players.size() == 1) {
            announceWinner(); // Announce the last remaining player as the winner
        } else if (turnCounter >= maxTurns) {
            determineWinner(); // Determine the winner based on money
        }
    }


    /**
     * Determines the winner based on the player with the most money.
     * If no player is found, it announces that no winner could be determined.
     */
    private void determineWinner() {
        Player winner = players.stream()
                .max((p1, p2) -> Integer.compare(p1.getMoney(), p2.getMoney())) // Compare players by money
                .orElse(null);

        if (winner != null) {
            System.out.println("The winner is " + winner.getName() + " with $" + winner.getMoney());
        } else {
            System.out.println("No winner could be determined.");
        }
    }

    /**
     * Adds a player to the game with the specified name and strategy.
     *
     * @param name         The name of the player.
     * @param strategyType The strategy type of the player.
     */
    public void addPlayer(String name, StrategyType strategyType) {
        Player player = new Player(name, this, strategyType); // Create a new player
        player.setMoney(startingMoney); // Set the starting money
        players.add(player); // Add the player to the list
        System.out.println("Added Player: " + name + " with Strategy: " + strategyType);
    }


    /**
     * Handles the bankruptcy of a player by transferring their properties back to the bank.
     *
     * @param player The player who is bankrupt.
     */    private void handleBankruptcy(Player player) {

        // Transfer properties to the bank or creditor
        for (Property property : player.getProperties()) {
            if (property.getOwner() == player) {
                System.out.println(property.getName() + " is now unowned.");
                property.setOwner(null); // Reset ownership to bank
            }
        }

        // Remove the player
        removePlayer(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);


    }

    // Announce the winner of the game
    private void announceWinner() {

        players.stream()
                .findFirst() // Since there is only one player left
                .ifPresent(winner -> System.out.println(winner.getName() + " wins the game with $" + winner.getMoney() + "!"));
    }

    /**
     * Executes a single turn for a player, handling movement, actions, and possible house building.
     *
     * @param player The player whose turn is being executed.
     */
    private void takeTurn(Player player) {
        if (player.isInJail()) {
            System.out.println(player.getName() + " is in Jail. Handling Jail Turn...");
            board.getJail().handleJailTurn(player, bailAmount);
            return; // End turn early if in jail
        }

        int diceRoll = rollDice(); // Roll the dice
        System.out.println(player.getName() + " rolled a " + diceRoll);
        player.move(diceRoll); // Move the player based on the dice roll

        Node currentNode = player.getCurrentNode(); // Get the current node

        System.out.println(player.getName() + " landed on " + currentNode.getName() + "!");

        currentNode.onLand(player); // Handle the node's action

        if (player.getMoney() <= 0) {
            handleBankruptcy(player); // Handle bankruptcy if the player runs out of money
        }
         else {
            var possibleHouse = player.getCheapestHouseToBuild(this); // Find the cheapest house to build
            if (possibleHouse != null){
                player.buildHouseOnProperty(this,possibleHouse);// Build a house on the property
            }

        }
    }

    /**
     * Returns the list of players currently in the game.
     *
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Rolls two dice and returns their total.
     *
     * @return The total of two dice rolls.
     */
    public int rollDice() {
        return dice.nextInt(6) + 1 + dice.nextInt(6) + 1;
    }

    public int getBailAmount()
    {
        return bailAmount;
    }

    /**
     * Returns the strategy type of the winner if there is only one player left.
     * If the game ends in a draw, it returns "It's A Draw."
     *
     * @return The winner's strategy type or "It's A Draw."
     */
    public String getWinnerStrategy() {
        if (players.size() == 1) {
            return players.get(0).getStrategyType().name();
        }
        return "It's A Draw"; // If max turns are reached without a clear winner
    }

    /**
     * Returns the number of turns played in the game.
     *
     * @return The turn counter.
     */
    public int getTurnCounter() {
        return this.turnCounter;
    }


}
