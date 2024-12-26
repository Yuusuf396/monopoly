import upei.project.core.Game;
import upei.project.player.Player;
import upei.project.player.StrategyType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimulationExperiment {

    public static void main(String[] args) {
        int simulationCount = 60; // Number of games to simulate
        Map<String, Integer> winCounts = new HashMap<>(); // Track wins per strategy
        Map<String, Integer> totalMoneyCollected = new HashMap<>(); // Total money collected per strategy
        Map<String, Integer> totalPropertiesOwned = new HashMap<>(); // Total properties owned per strategy
        int totalTurns = 0; // Track total turns across simulations

        for (int i = 0; i < simulationCount; i++) {
            Game game = new Game();

            // Add players with different strategies
            game.addPlayer("Player 1", StrategyType.AGGRESSIVE);
            game.addPlayer("Player 2", StrategyType.CONSERVATIVE);
            game.addPlayer("Player 3", StrategyType.RANDOM);

            // Start the game and measure its duration
            game.start();
            totalTurns += game.getTurnCounter();

            // Record the winner's strategy
            String winnerStrategy = game.getWinnerStrategy();
            winCounts.put(winnerStrategy, winCounts.getOrDefault(winnerStrategy, 0) + 1);

            // Collect additional metrics
            for (Player player : game.getPlayers()) {
                String strategy = player.getStrategyType().name();

                // Track total money collected per strategy
                totalMoneyCollected.put(strategy, totalMoneyCollected.getOrDefault(strategy, 0) + player.getMoney());

                // Track total properties owned per strategy
                totalPropertiesOwned.put(strategy, totalPropertiesOwned.getOrDefault(strategy, 0) + player.getProperties().size());
            }
        }

        // Print simulation results
        printSimulationResults(simulationCount, totalTurns, winCounts, totalMoneyCollected, totalPropertiesOwned);
    }

    /**
     * Prints the simulation results, including win counts, average turns, and additional metrics.
     */
    private static void printSimulationResults(int simulationCount, int totalTurns, Map<String, Integer> winCounts,
                                               Map<String, Integer> totalMoneyCollected, Map<String, Integer> totalPropertiesOwned) {
        System.out.println();
        System.out.println("Simulation Results:");
        System.out.println("Total Games Simulated: " + simulationCount);
        System.out.println("Average Turns Per Game: " + (double) totalTurns / simulationCount);

        // Print win rates for each strategy
        System.out.println("Strategy CONSERVATIVE Wins: " + winCounts.getOrDefault("CONSERVATIVE", 0) + " times");
        System.out.println("Strategy AGGRESSIVE Wins: " + winCounts.getOrDefault("AGGRESSIVE", 0) + " times");
        System.out.println("Strategy RANDOM Wins: " + winCounts.getOrDefault("RANDOM", 0) + " times");
        System.out.println("It's A Draw: " + winCounts.getOrDefault("It's A Draw", 0) + " times");

        // Print additional metrics
        System.out.println("\nAdditional Metrics:");
        System.out.println("Average Money Collected (Conservative): " +
                getAverage(totalMoneyCollected.getOrDefault("CONSERVATIVE", 0), 1));
        System.out.println("Average Money Collected (Aggressive): " +
                getAverage(totalMoneyCollected.getOrDefault("AGGRESSIVE", 0), 1));
        System.out.println("Average Money Collected (Random): " +
                getAverage(totalMoneyCollected.getOrDefault("RANDOM", 0), 1));

        System.out.println("Average Properties Owned (Conservative): " +
                getAverage(totalPropertiesOwned.getOrDefault("CONSERVATIVE", 0), 1));
        System.out.println("Average Properties Owned (Aggressive): " +
                getAverage(totalPropertiesOwned.getOrDefault("AGGRESSIVE", 0), 1));
        System.out.println("Average Properties Owned (Random): " +
                getAverage(totalPropertiesOwned.getOrDefault("RANDOM", 0), 1));
    }


    /**
     * Calculates the average value by dividing the total by the simulation count.
     *
     * @param total The total value to calculate the average for.
     * @param count The number of simulations.
     * @return The average value.
     */
    private static double getAverage(int total, int count) {
        return count == 0 ? 0 : (double) total / count;
    }
}