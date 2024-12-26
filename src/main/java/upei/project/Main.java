package upei.project;

import upei.project.core.Game;
import upei.project.player.Player;
import upei.project.player.StrategyType;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


/**
 * The entry point for the game application.
 * This class initializes the game, adds players with different strategies,
 * and starts the gameplay.
 */
public class Main {

    /**
     * The main method, serving as the starting point of the application.
     * Sets up the game environment, adds players, and initiates the game.
     * It also handles any exceptions that occur during the game setup or execution.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            // Notify the user that a new game is starting
            System.out.println("New");

            // Create a new game instance
            Game game = new Game();

            // Add players to the game with their names and strategies
            game.addPlayer("Joel", StrategyType.AGGRESSIVE); // Aggressive strategy player
            game.addPlayer("Yuusuf", StrategyType.CONSERVATIVE); // Conservative strategy player
            game.addPlayer("Priestly", StrategyType.RANDOM); // Random strategy player

            // Start the game
            game.start();
        } catch (Exception e) {
            // Handle and display any errors that occur during the game
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
