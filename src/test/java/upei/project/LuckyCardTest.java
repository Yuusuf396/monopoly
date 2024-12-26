package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import upei.project.cards.LuckyCard;
import upei.project.core.Game;
import upei.project.player.Player;
import upei.project.player.StrategyType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LuckyCardTest {

    private LuckyCard testCard;
    private Player player;
    private Game game;

    /**
     * A concrete subclass of LuckyCard for testing purposes.
     */
    static class TestLuckyCard extends LuckyCard {
        public TestLuckyCard(String name, String description) {
            super(name, description);
        }

        @Override
        public void onLand(Player playerLanded) {
            // Example effect: Add $100 to the player's balance
            playerLanded.updateMoney(100);
        }
    }

    /**
     * Sets up the test environment by initializing the game, player, and a test LuckyCard instance.
     */
    @BeforeEach
    void setUp() {
        game = new Game(); // Initialize the game
        player = new Player("TestPlayer", game, StrategyType.AGGRESSIVE); // Create a player
        player.setMoney(500); // Set initial money

        // Initialize the test card
        testCard = new TestLuckyCard("Test Card", "This is a test lucky card effect.");
    }

    /**
     * Tests that the LuckyCard's name is correctly retrieved.
     * Verifies that the name matches the value provided during initialization.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetName() {
        // Assert that the name of the lucky card is correct
        assertEquals("Test Card", testCard.getName(), "The lucky card name should be 'Test Card'");
    }

    /**
     * Tests that the LuckyCard's description is correctly retrieved.
     * Verifies that the description matches the value provided during initialization.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetDescription() {
        // Assert that the description of the lucky card is correct
        assertEquals("This is a test lucky card effect.", testCard.getDescription(),
                "The lucky card description should match the initialized value");
    }

    /**
     * Tests the effect of the LuckyCard when applied to a player.
     * Verifies that the player's money increases by the expected amount.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testOnLandEffect() {
        // Act: Apply the card's effect to the player
        testCard.onLand(player);

        // Assert: Verify that the player's money increased as expected
        assertEquals(600, player.getMoney(), "Player's money should increase by $100 after using the lucky card");
    }

    /**
     * Tests the LuckyCard effect when applied multiple times.
     * Verifies that the effect stacks and the player's money increases cumulatively.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testMultipleOnLandEffects() {
        // Act: Apply the card's effect multiple times
        testCard.onLand(player);
        testCard.onLand(player);

        // Assert: Verify that the effect stacks correctly
        assertEquals(700, player.getMoney(), "Player's money should increase correctly after multiple card effects");
    }

    /**
     * Tests the LuckyCard effect on a bankrupt player (money is zero).
     * Verifies that the card effect correctly increases the player's money, bringing them out of bankruptcy.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testEffectOnBankruptPlayer() {
        // Arrange: Set the player to a bankrupt state
        player.setMoney(0);

        // Act: Apply the card's effect
        testCard.onLand(player);

        // Assert: Verify the player is no longer bankrupt after the card effect
        assertEquals(100, player.getMoney(), "Player's money should increase from $0 after the lucky card effect");
    }
}
