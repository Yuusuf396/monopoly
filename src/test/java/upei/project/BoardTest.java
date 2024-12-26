package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upei.project.cards.LuckyCard;
import upei.project.core.Board;
import upei.project.core.Colour;
import upei.project.core.tiles.*;
import upei.project.player.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private List<LuckyCard> chanceDeck;
    private List<LuckyCard> communityChestDeck;

    @BeforeEach
    void setUp() {
        // Mocking a simple set of cards for testing
        chanceDeck = new ArrayList<>();
        communityChestDeck = new ArrayList<>();

        chanceDeck.add(new LuckyCard("Chance Card 1", "Test chance card description.") {
            @Override
            public void onLand(Player player) {
                player.updateMoney(50);
            }
        });

        communityChestDeck.add(new LuckyCard("Community Chest Card 1", "Test community chest card description.") {
            @Override
            public void onLand(Player player) {
                player.updateMoney(100);
            }
        });

        board = new Board(chanceDeck, communityChestDeck);
    }

    /**
     * Tests that the board initializes properly with the correct number of nodes.
     * Ensures the nodes array is not null and contains exactly 40 nodes.
     */
    @Test
    void testBoardInitialization() {
        // Test if nodes are properly initialized
        assertNotNull(board.getNodes(), "Nodes array should not be null.");
        assertEquals(40, board.getNodesCount(), "Board should have exactly 40 nodes.");
    }

    /**
     * Verifies that the Jail node is properly initialized and located at position 10.
     */
    @Test
    void testJailNode() {
        // Verify the Jail node
        Node[] nodes = board.getNodes();
        assertNotNull(board.getJail(), "Jail node should not be null.");
        assertTrue(nodes[10] instanceof Jail, "Jail should be at position 10.");
    }

    /**
     * Validates the initialization of the Chance deck and ensures the Chance node is correctly placed at position 7.
     */
    @Test
    void testChanceDeckInitialization() {
        // Verify the Chance deck and nodes
        Node chanceNode = board.getNodes()[7];
        assertTrue(chanceNode instanceof Chance, "Node 7 should be a Chance node.");
        assertFalse(chanceDeck.isEmpty(), "Chance deck should not be empty.");
    }

    /**
     * Validates the initialization of the Community Chest deck and ensures the Community Chest node is correctly placed at position 2.
     */
    @Test
    void testCommunityChestDeckInitialization() {
        // Verify the Community Chest deck and nodes
        Node communityChestNode = board.getNodes()[2];
        assertTrue(communityChestNode instanceof CommunityChest, "Node 2 should be a Community Chest node.");
        assertFalse(communityChestDeck.isEmpty(), "Community Chest deck should not be empty.");
    }

    /**
     * Tests retrieval of a specific node by index and verifies the node's name.
     */
    @Test
    void testNodeRetrievalByIndex() {
        // Verify specific nodes by index
        Node nodeAt5 = board.getNodes()[5];
        assertNotNull(nodeAt5, "Node at index 5 should not be null.");
        assertEquals("Reading Railroad", nodeAt5.getName(), "Node at index 5 should be 'Reading Railroad'.");
    }

    @Test
    void testNodeTypes() {
        // Verify the type of specific nodes
        Node[] nodes = board.getNodes();

        assertTrue(nodes[0] instanceof Go, "Node 0 should be the 'Go' node.");
        assertTrue(nodes[30] instanceof GoToJail, "Node 30 should be the 'Go To Jail' node.");
        assertTrue(nodes[39] instanceof HousableProperty, "Node 39 should be a property node.");
    }

    /**
     * Tests the type of specific nodes to ensure proper initialization of key game features like Go, GoToJail, and properties.
     */
    @Test
    void testNodeConnections() {
        // Verify specific node connections
        Node[] nodes = board.getNodes();

        assertEquals("Mediterranean Avenue", nodes[1].getName(), "Node 1 should be 'Mediterranean Avenue'.");
        assertEquals("Community Chest", nodes[2].getName(), "Node 2 should be a Community Chest node.");
        assertEquals("Baltic Avenue", nodes[3].getName(), "Node 3 should be 'Baltic Avenue'.");
    }

    /**
     * Tests the count of properties for specific colours to ensure correct mapping of colours to properties.
     */
    @Test
    void testColourCount() {
        // Test the count of properties for a specific colour
        assertEquals(2, board.getColourCount(Colour.Brown), "There should be 2 properties of Brown colour.");
        assertEquals(3, board.getColourCount(Colour.LightBlue), "There should be 3 properties of LightBlue colour.");
        assertEquals(2, board.getColourCount(Colour.Blue), "There should be 2 properties of Blue colour.");
    }

    /**
     * Verifies the initialization details of specific nodes, including names, prices, and colours.
     */
    @Test
    void testNodeInitializationDetails() {
        // Verify specific nodes are properly initialized
        Node[] nodes = board.getNodes();

        HousableProperty medAve = (HousableProperty) nodes[1];
        assertEquals("Mediterranean Avenue", medAve.getName(), "Node 1 should be Mediterranean Avenue.");
        assertEquals(150, medAve.getPrice(), "Mediterranean Avenue should cost $150.");
        assertEquals(Colour.Brown, medAve.getColour(), "Mediterranean Avenue should be Brown.");

        RailRoad readingRailroad = (RailRoad) nodes[5];
        assertEquals("Reading Railroad", readingRailroad.getName(), "Node 5 should be Reading Railroad.");
        assertEquals(200, readingRailroad.getPrice(), "Reading Railroad should cost $200.");
    }

    /**
     * Verifies that the Go To Jail node is correctly initialized and located at position 30.
     */
    @Test
    void testGoToJailNode() {
        // Verify the Go To Jail node
        Node goToJailNode = board.getNodes()[30];
        assertTrue(goToJailNode instanceof GoToJail, "Node 30 should be a GoToJail node.");
    }
}
