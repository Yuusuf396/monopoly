package upei.project.core;

import upei.project.cards.LuckyCard;
import upei.project.core.tiles.*;
import upei.project.core.tiles.GoToJail;

import java.util.List;

/**
 * Represents the game board containing all nodes (tiles) in the game.
 * The board includes properties, special nodes (like Jail), and card decks for Chance and Community Chest.
 */
public class Board {

    // Array of all nodes (tiles) on the board
    private Node[] nodes;

    // The Jail node on the board
    private Jail jail;

    // Deck of Chance cards
    private List<LuckyCard> chanceDeck;

    // Deck of Community Chest cards
    private List<LuckyCard> communityChestDeck;

    /**
     * Returns a copy of the nodes on the board to maintain immutability.
     *
     * @return A cloned array of nodes on the board.
     */
    public Node[] getNodes() {
        return nodes.clone();
    }

    /**
     * Returns the total number of nodes on the board.
     *
     * @return The number of nodes on the board.
     */
    public int getNodesCount() {
        return nodes.length;
    }

    /**
     * Returns the Jail node on the board.
     *
     * @return The Jail node.
     */
    public Jail getJail() {
        return jail;
    }

    /**
     * Counts the number of properties on the board that belong to a specific color group.
     *
     * @param colour The color group to count (e.g., Blue, Red, etc.).
     * @return The number of properties in the specified color group.
     */
    public int getColourCount(Colour colour) {
        var colorCount = 0;

        // Iterate through all nodes to count properties of the specified color
        for (var i : getNodes()) {
            if (i instanceof HousableProperty property) {
                if (property.getColour() == colour) {
                    colorCount++;
                }
            }
        }
        return colorCount;
    }

    /**
     * Constructor for the Board class.
     * Initializes the board with the given Chance and Community Chest card decks
     * and sets up all nodes on the board.
     *
     * @param chanceDeck        The deck of Chance cards to use on the board.
     * @param communityChestDeck The deck of Community Chest cards to use on the board.
     */
    public Board(List<LuckyCard> chanceDeck, List<LuckyCard> communityChestDeck) {
        this.chanceDeck = chanceDeck;
        this.communityChestDeck = communityChestDeck;
        initializeBoard(); // Set up all nodes and components of the board
    }

    /**
     * Initializes the board by creating all nodes (tiles) and assigning them in order.
     * This includes special tiles like "Jail" and card draw tiles like "Chance."
     */
    private void initializeBoard() {
        jail = new Jail(); // Create the Jail node


        nodes = new Node[]{
                new Go(),
                new HousableProperty("Mediterranean Avenue", 150, Colour.Brown),
                new CommunityChest( communityChestDeck),
                new HousableProperty("Baltic Avenue", 60, Colour.Brown),
                new Tax("Income Tax",35),
                new RailRoad("Reading Railroad",  200),
                new HousableProperty("Oriental Avenue", 100, Colour.LightBlue),
                new Chance(chanceDeck),
                new HousableProperty("Vermont Avenue", 100, Colour.LightBlue),
                new HousableProperty("Connecticut Avenue", 120, Colour.LightBlue),
                jail,// Assign the Jail node
                new HousableProperty("St. Charles Place", 150, Colour.Purple),
                new Utility("Electric Company-Plain works",   150),
                new HousableProperty("States Avenue", 140, Colour.Purple),
                new HousableProperty("Virginia Avenue", 160, Colour.Purple),
                new RailRoad("Pennsylvania Railroad", 200),
                new HousableProperty("St. James Place", 180, Colour.Orange),
                new CommunityChest( communityChestDeck),
                new HousableProperty("Tennessee Avenue", 180, Colour.Orange),
                new HousableProperty("New York Avenue", 200, Colour.Orange),
                new Node("Free Parking"),
                new HousableProperty("Kentucky Avenue", 220, Colour.Red),
                new Chance(chanceDeck),
                new HousableProperty("Indiana Avenue", 220, Colour.Red),
                new HousableProperty("Illinois Avenue", 240, Colour.Red),
                new RailRoad("B&O Railroad",80),
                new HousableProperty("Atlantic Avenue", 260, Colour.Yellow),
                new HousableProperty("Ventnor Avenue", 260, Colour.Yellow),
                new Utility("Water Works",120),
                new HousableProperty("Marvin Gardens", 280, Colour.Yellow),
                new GoToJail(),
                new HousableProperty("Pacific Avenue", 300, Colour.Green),
                new HousableProperty("North Carolina Avenue", 300, Colour.Green),
                new CommunityChest( communityChestDeck),
                new HousableProperty("Pennsylvania Avenue", 320, Colour.Green),
                new RailRoad("Short Line Railroad", 200),
                new Chance(chanceDeck ),
                new HousableProperty("Park Place",  350,  Colour.Blue),
                new Tax("Luxury Tax",100 ),
                new HousableProperty("Boardwalk", 400, Colour.Blue)
        };
    }





        /**
        go = new Go();
        Node mediterraneanAvenue = new HousableProperty("Mediterranean Avenue",150, Colour.Green);
        Node communityChest1 = new Node("Community Chest", "Community Chest", 0, 0, "No colour");
        Node balticAvenue = new HousableProperty("Baltic Avenue",60, Colour.Brown);
        Node incomeTax = new Node("Income Tax");
        Node readingRailroad = new Node("Reading Railroad", "Railroad", 200, 25, "Plain rails");
        Node orientalAvenue = new HousableProperty("Oriental Avenue",  100, Colour.LightBlue);
        Node chance1 = new Node("Chance", "Chance", 0, 0, "No colour");
        Node vermountAvenue = new HousableProperty("Vermount Avenue", 100, Colour.LightBlue);
        Node connecticutAvenue = new HousableProperty("Connecticut Avenue",12 ,Colour.LightBlue);
        jail = new Node("Jail");
        stcharlesplace = new Node("St.Charles Place","Property", 140, 28, "Purple");
        Node electricCompany = new Node("Electric Company", "Property", 150, 30, "Plain works");
        Node statesAvenue = new HousableProperty("States Avenue", 140, Colour.Purple);
        Node virginiaAvenue = new HousableProperty("Virginia Avenue",  160, Colour.Purple);
        Node pennysvlaniaRailroad = new Node("Pennysylvania Railroad", "Railroad", 200, 25, "Plain rails");
        Node stjamesplace = new HousableProperty("St.James Place", 180, Colour.Orange);
        Node communityChest2 = new Node("Community Chest" );
        Node tennesseeAvenue = new HousableProperty("Tennessee Avenue",  180, Colour.Orange);
        Node newYorkAvenue = new HousableProperty("New York Avenue",   200, Colour.Orange);
        Node freeParking = new Node("Free Parking");
        Node kentuckyAvenue = new HousableProperty("Kentucky Avenue", 220, Colour.Red);
        Node chance2 = new Node("Chance", "Chance", 0, 0, "No colour");
        Node indianaAvenue = new HousableProperty("Indiana Avenue",  220, Colour.Red);
        illinoisAvenue = new HousableProperty("Illinois Avenue",  240, Colour.Red);
        Node bAndORailroad = new Node("B&O Railroad" );
        Node atlanticAvenue = new HousableProperty("Atlantic Avenue",  260, Colour.Yellow);
        Node ventnorAvenue = new HousableProperty("Ventnor Avenue",  260, Colour.Yellow);
        Node waterWorks = new Node("Water Works",);
        Node marvinGardens = new HousableProperty("Marvin Gardens",  280, Colour.Yellow);
        goToJail = new Node("Go to Jail" );
        Node pacificAvenue = new HousableProperty("Pacific Avenue", 300,   Colour.Green);
        Node northCarolinaAvenue = new HousableProperty("North Carolina Avenue", 300, 50, Colour.Green);
        Node communityChest3 = new Node("Community Chest", "Community Chest", 0, 0, "No colour");
        Node pennsylvaniaAvenue = new HousableProperty("Pennsylvania Avenue",  320,  Colour.Green);
        Node shortLineRailroad = new Node("Short Line Railroad", "Railroad", 200, 25, "Plain rails");
        Node chance3 = new Node("Chance", "Chance", 0, 0, "No colour");
        Node parkPlace = new Node("Park Place", "Property", 350, 52, "Blue");
        Node luxuryTax = new Node("Luxury Tax", "Tax", 0, 100, "No colour");
        boardwalk = new HousableProperty("Boardwalk",  400, Colour.Blue);
        */


        /**
        go.next = mediterraneanAvenue;
        mediterraneanAvenue.next = communityChest1;
        communityChest1.next = balticAvenue;
        balticAvenue.next = incomeTax;
        incomeTax.next = readingRailroad;
        readingRailroad.next = orientalAvenue;
        orientalAvenue.next = chance1;
        chance1.next = vermountAvenue;
        vermountAvenue.next = connecticutAvenue;
        connecticutAvenue.next = jail;
        jail.next = stcharlesplace;
        stcharlesplace.next = electricCompany;
        electricCompany.next = statesAvenue;
        statesAvenue.next = virginiaAvenue;
        virginiaAvenue.next = pennysvlaniaRailroad;
        pennysvlaniaRailroad.next = stjamesplace;
        stjamesplace.next = communityChest2;
        communityChest2.next = tennesseeAvenue;
        tennesseeAvenue.next = newYorkAvenue;
        newYorkAvenue.next = freeParking;

        freeParking.next = kentuckyAvenue;
        kentuckyAvenue.next = chance2;
        chance2.next = indianaAvenue;
        indianaAvenue.next = illinoisAvenue;
        illinoisAvenue.next = bAndORailroad;
        bAndORailroad.next = atlanticAvenue;
        atlanticAvenue.next = ventnorAvenue;
        ventnorAvenue.next = waterWorks;
        waterWorks.next = marvinGardens;
        marvinGardens.next = goToJail;
        goToJail.next = pacificAvenue;
        pacificAvenue.next = northCarolinaAvenue;
        northCarolinaAvenue.next = communityChest3;
        communityChest3.next = pennsylvaniaAvenue;
        pennsylvaniaAvenue.next = shortLineRailroad;
        shortLineRailroad.next = chance3;
        chance3.next = parkPlace;
        parkPlace.next = luxuryTax;
        luxuryTax.next = boardwalk;

        // Closing the circular loop
        boardwalk.next = go;
         */
    }



