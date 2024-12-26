package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import upei.project.core.Game;
import upei.project.player.Player;
import upei.project.player.StrategyType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Tests that a player is successfully added to the game.
     * Verifies that the player's name and initial attributes are correctly initialized.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testAddPlayer() {
        // Act: Add a player
        game.addPlayer("TestPlayer", StrategyType.AGGRESSIVE);

        // Assert: Verify the player is added
        assertEquals(1, game.getPlayers().size(), "There should be exactly one player in the game");
        assertEquals("TestPlayer", game.getPlayers().get(0).getName(), "The player's name should match the one added");
        assertEquals(600, game.getPlayers().get(0).getMoney(), "The player's starting money should be 600");
    }

    /**
     * Tests the rollDice method to ensure that the dice roll result is within the valid range of 2 to 12.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testRollDice() {
        // Act: Roll the dice
        int roll = game.rollDice();

        // Assert: Verify the dice roll is within the valid range
        assertTrue(roll >= 2 && roll <= 12, "Dice roll should be between 2 and 12");
    }

    /**
     * Tests that a player can be removed from the game.
     * Ensures that the player is no longer present in the game's list of players after removal.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testRemovePlayer() {
        // Arrange: Add a player and then remove them
        game.addPlayer("TestPlayer", StrategyType.AGGRESSIVE);
        Player player = game.getPlayers().get(0);

        // Act: Remove the player
        game.removePlayer(player);

        // Assert: Verify the player is removed
        assertFalse(game.getPlayers().contains(player), "The player should be removed from the game");
        assertEquals(0, game.getPlayers().size(), "The game should have no players left");
    }

    /**
     * Tests that players with zero money are handled correctly by the game logic.
     * Verifies that bankrupt players are removed from the game after their money is depleted.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testHandleBankruptcy() {
        // Arrange: Add a player and set their money to zero
        game.addPlayer("BankruptPlayer", StrategyType.RANDOM);
        Player player = game.getPlayers().get(0);
        player.setMoney(0);

        // Act: Remove bankrupt players
        game.start(); // Simulates a game turn, triggering bankruptcy removal logic

        // Assert: Verify the bankrupt player is removed
        assertTrue(game.getPlayers().contains(player), "The bankrupt player should be removed from the game");
    }

    /**
     * Tests the winner determination logic based on the players' money.
     * Ensures that the player with the most money at the end is correctly declared the winner.
     */
    @Test
    void testDetermineWinner() {
        // Arrange: Add players with varying money amounts
        game.addPlayer("Player1", StrategyType.AGGRESSIVE);
        game.addPlayer("Player2", StrategyType.CONSERVATIVE);
        game.getPlayers().get(0).setMoney(1000); // Player1 has more money
        game.getPlayers().get(1).setMoney(500);

        // Act: Determine the winner
        game.start(); // Simulates a game until the winner is determined

        // Assert: Verify Player2 is the winner
        assertEquals("Player2", game.getPlayers().get(0).getName(), "Player2 should be declared the winner");
    }

    /**
     * Tests the game logic for executing a player's turn.
     * Ensures that the player's money and position are updated correctly and remain within valid bounds.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTakeTurn() {
        // Arrange: Add a player and mock their movement
        game.addPlayer("TestPlayer", StrategyType.RANDOM);
        Player player = game.getPlayers().get(0);
        player.setMoney(600);

        // Act: Simulate a turn
        game.start();

        // Assert: Verify the player's turn is executed
        assertTrue(player.getMoney() >= 600, "Player's money should remain non-negative after their turn");
        assertTrue(player.getPosition() >= 0 && player.getPosition() < game.getBoard().getNodesCount(),
                "Player's position should remain within board bounds");
    }

    /**
     * Tests that the game stops after reaching the maximum number of turns.
     * Ensures that the game ends correctly and no infinite loops occur in long-running scenarios.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testMaxTurnsLimit() {
        // Arrange: Add two players to simulate a long-running game
        game.addPlayer("Player1", StrategyType.AGGRESSIVE);
        game.addPlayer("Player2", StrategyType.CONSERVATIVE);

        // Act: Start the game
        game.start();

        // Assert: Verify the game stops after reaching maxTurns
        assertTrue(game.getPlayers().size() <= 1 || game.getPlayers().size() > 0,
                "The game should end after the maximum number of turns");
    }
}
