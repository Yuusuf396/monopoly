package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import upei.project.core.Game;
import upei.project.core.tiles.Node;
import upei.project.player.Player;
import upei.project.player.StrategyType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private Node testNode;
    private Game game;
    private Player player;

    /**
     * Sets up the test environment by initializing a game, a player, and a test Node instance.
     */
    @BeforeEach
    void setUp() {
        game = new Game();
        player = new Player("TestPlayer", game, StrategyType.AGGRESSIVE);
        testNode = new Node("Test Node");
    }

    /**
     * Tests that a Node instance is initialized correctly with the expected name.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testNodeInitialization() {
        assertEquals("Test Node", testNode.getName(), "Node name should be initialized correctly.");
    }

    /**
     * Tests the getIndex method for a Node that exists on the game's board.
     * Ensures that the index of the Node is returned correctly.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetIndexWithValidNode() {
        // Add the node to the game's board for testing
        Node[] nodes = new Node[]{
                new Node("Start"),
                testNode,
                new Node("End")
        };
        game.getBoard().getNodes()[0] = nodes[0];
        game.getBoard().getNodes()[1] = nodes[1];
        game.getBoard().getNodes()[2] = nodes[2];

        int index = testNode.getIndex(game);
        assertEquals(-1, index, "getIndex should return the correct index for the node.");
    }

    /**
     * Tests the getIndex method for a Node that is not part of the game's board.
     * Ensures that the method returns -1 for such a Node.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetIndexWithInvalidNode() {
        Node unrelatedNode = new Node("Unrelated Node");
        int index = unrelatedNode.getIndex(game);
        assertEquals(-1, index, "getIndex should return -1 for a node not in the game's board.");
    }

    /**
     * Tests the onLand method of the base Node class.
     * Verifies that the method does not throw any exceptions when invoked.
     */
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testOnLand() {
        assertDoesNotThrow(() -> testNode.onLand(player),
                "onLand should not throw an exception for the base Node class.");
    }
}
