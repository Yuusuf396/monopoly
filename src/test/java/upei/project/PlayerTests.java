package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import upei.project.core.Game;
import upei.project.core.tiles.Property;
import upei.project.player.Player;
import upei.project.player.StrategyType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * Tests the initialization of a Player object.
     * Verifies that the player's name, position, money, and jail status are set to their default values.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testPlayerInitialization() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.AGGRESSIVE);

        // Act & Assert
        assertEquals("Alice", player.getName(), "Player name should be initialized correctly");
        assertEquals(0, player.getPosition(), "Player position should be initialized to 0");
        assertEquals(0, player.getMoney(), "Player money should be initialized to 0");
        assertFalse(player.isInJail(), "Player should not start in jail");
    }

    /**
     * Tests the money management methods of the Player class.
     * Verifies setting, updating, and ensuring money does not go below zero.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testMoneyManagement() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.CONSERVATIVE);

        // Act & Assert
        player.setMoney(100);
        assertEquals(100, player.getMoney(), "Player money should be set correctly");

        player.updateMoney(50);
        assertEquals(150, player.getMoney(), "Player money should increase by the update amount");

        player.updateMoney(-200);
        assertEquals(0, player.getMoney(), "Player money should not go negative");
    }

    /**
     * Tests player movement across the board and handling the "Go" tile.
     * Verifies correct position updates and reward for passing "Go".
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testMovementAndPassingGo() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.RANDOM);
        player.setMoney(0);

        // Act: Move within board
        player.move(5);
        assertEquals(5, player.getPosition(), "Player should move to the correct position");

        // Act: Move past "Go"
        player.setPosition(39); // Simulate being near "Go"
        player.move(2);

        // Assert
        assertEquals(1, player.getPosition(), "Player position should wrap around the board after passing 'Go'");
        assertEquals(200, player.getMoney(), "Player should receive $200 for passing 'Go'");
    }

    /**
     * Tests handling of a player's jail status.
     * Verifies that the player can be set in and out of jail correctly.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testJailHandling() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.RANDOM);

        // Act & Assert: Jail status
        assertFalse(player.isInJail(), "Player should not start in jail");

        player.setInJail(true);
        assertTrue(player.isInJail(), "Player should be in jail after setting jail status");

        player.setInJail(false);
        assertFalse(player.isInJail(), "Player should not be in jail after clearing jail status");
    }

    /**
     * Tests handling of "Get Out of Jail Free" cards.
     * Verifies adding, using, and preventing negative card counts.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetOutOfJailFreeCard() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.CONSERVATIVE);

        // Act & Assert: Add card
        assertEquals(0, player.getOutOfJailFreeCards(), "Player should start with 0 'Get Out of Jail Free' cards");
        player.addGetOutOfJailFreeCard();
        assertEquals(1, player.getOutOfJailFreeCards(), "Player should have 1 'Get Out of Jail Free' card after adding");

        // Act & Assert: Use card
        player.useGetOutOfJailFreeCard();
        assertEquals(0, player.getOutOfJailFreeCards(), "Player should have 0 cards after using one");

        // Act & Assert: Attempt to use without cards
        player.useGetOutOfJailFreeCard();
        assertEquals(0, player.getOutOfJailFreeCards(), "Player should not have negative 'Get Out of Jail Free' cards");
    }

    /**
     * Tests a player's acquisition of properties.
     * Verifies ownership transfer for unowned properties and prevents transfer for owned properties.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testPropertyAcquisition() {
        // Arrange
        Game game = new Game();
        Player player = new Player("Alice", game, StrategyType.RANDOM);
        Property property = new Property("Boardwalk", 400);

        // Act & Assert: Acquire unowned property
        player.addProperty(property);
        assertFalse(player.getProperties().contains(property), "Player should own the property after acquiring it");
        assertEquals(player, property.getOwner(), "Property owner should be set to the player");

        // Act & Assert: Attempt to acquire an already owned property
        Player anotherPlayer = new Player("Bob", game, StrategyType.CONSERVATIVE);
        property.setOwner(anotherPlayer);
        player.addProperty(property);
        assertFalse(player.getProperties().contains(property), "Player should not acquire a property already owned by another");
        assertEquals(anotherPlayer, property.getOwner(), "Property owner should remain unchanged");
    }
}
