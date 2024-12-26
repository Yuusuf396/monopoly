//package upei.project.core;
//
//import upei.project.player.Player;
//
//public class Jail extends Node {
//
//    public Jail() {
//        super("Jail");
//    }
//
//    @Override
//    public void onLand(Player player) {
//        System.out.println(player.getName() + " is now in Jail!");
//        player.setInJail(true);
//        player.setJailTurns(0); // Reset the number of turns in jail
//    }
//
//    public void handleJailTurn(Player player, int bailAmount) {
//        System.out.println(player.getName() + " is in Jail.");
//
//        // Option 1: Use a "Get Out of Jail Free" card
//        if (player.getOutOfJailFreeCards() > 0) {
//            System.out.println(player.getName() + " has a 'Get Out of Jail Free' card.");
//            player.useGetOutOfJailFreeCard();
//            player.setInJail(false);
//            return;
//        }
//
//        // Option 2: Pay bail
//        if (player.getMoney() >= bailAmount) {
//            System.out.println(player.getName() + " can afford bail ($" + bailAmount + ").");
//            player.updateMoney(-bailAmount);
//            player.setInJail(false);
//            return;
//        }
//
//        // Option 3: Attempt to roll doubles
//        int die1 = rollDie();
//        int die2 = rollDie();
//
//        System.out.println(player.getName() + " rolls " + die1 + " and " + die2 + ".");
//
//        if (die1 == die2) {
//            System.out.println(player.getName() + " rolled doubles and is free from Jail!");
//            player.setInJail(false);
//            player.setJailTurns(0);
//            player.move(die1 + die2); // Move according to the dice roll
//        } else {
//            player.setJailTurns(player.getJailTurns() + 1);
//            System.out.println(player.getName() + " did not roll doubles. Turns in Jail: " + player.getJailTurns());
//
//            // After 3 turns, force the player to pay bail if they can
//            if (player.getJailTurns() >= 3) {
//                System.out.println(player.getName() + " has been in Jail for 3 turns.");
//                if (player.getMoney() >= bailAmount) {
//                    System.out.println(player.getName() + " pays bail and is released from Jail.");
//                    player.updateMoney(-bailAmount);
//                    player.setInJail(false);
//                } else {
//                    System.out.println(player.getName() + " cannot afford bail and remains in Jail.");
//                }
//            }
//        }
//    }
//
//    private int rollDie() {
//        return (int) (Math.random() * 6) + 1;
//    }
//}

package upei.project.core.tiles;

import upei.project.player.Player;

/**
 * Represents the "Jail" tile on the game board.
 * This tile handles players being sent to Jail and manages their options for getting out,
 * including using a "Get Out of Jail Free" card, paying bail, or rolling doubles.
 */
public class Jail extends Node {

    /**
     * Constructor for the "Jail" tile.
     * Initializes the tile with the name "Jail".
     */
    public Jail()
    {
        super("Jail");
    }

    /**
     * Defines the action that occurs when a player lands on the "Jail" tile.
     * The player is marked as being in Jail, and their jail turn counter is reset.
     *
     * @param player The player who landed on the tile.
     */
    @Override
    public void onLand(Player player) {
        System.out.println(player.getName() + " is sent to Jail!");
        player.setInJail(true);
        player.setJailTurns(0); // Reset the number of turns in jail
    }

    /**
     * Manages a player's turn while they are in Jail.
     * The player has several options to attempt to get out of Jail:
     * - Use a "Get Out of Jail Free" card (if available)
     * - Pay the bail amount (if they have enough money)
     * - Attempt to roll doubles (up to 3 attempts)
     * If none of these options succeed after 3 turns, the player must pay bail if they can afford it.
     *
     * @param player     The player currently in Jail.
     * @param bailAmount The amount required to pay bail.
     */
    public void handleJailTurn(Player player, int bailAmount) {
//        System.out.println(player.getName() + " is currently in Jail.");

        if (tryGetOutOfJailFreeCard(player)) return;
        if (tryPayBail(player, bailAmount)) return;
        tryRollForDoubles(player, bailAmount);
    }

    /**
     * Attempts to use a "Get Out of Jail Free" card to release the player from Jail.
     *
     * @param player The player in Jail.
     * @return True if the player used the card, false otherwise.
     */
    private boolean tryGetOutOfJailFreeCard(Player player) {
        if (player.getOutOfJailFreeCards() > 0) {

            player.useGetOutOfJailFreeCard();
            player.setInJail(false);
            return true;
        }
        return false;
    }

    /**
     * Attempts to pay the bail amount to release the player from Jail.
     *
     * @param player     The player in Jail.
     * @param bailAmount The amount required to pay bail.
     * @return True if the player paid bail and was released, false otherwise.
     */
    private boolean tryPayBail(Player player, int bailAmount) {
        if (player.getMoney() >= bailAmount) {
            System.out.println(player.getName() + " pays $" + bailAmount + " to get out of Jail.");
            player.updateMoney(-bailAmount);
            player.setInJail(false);
            return true;
        }
        System.out.println(player.getName() + " cannot afford the bail ($" + bailAmount + ").");
        return false;
    }

    /**
     * Attempts to roll doubles to release the player from Jail.
     * If doubles are rolled, the player is released and moves according to the dice roll.
     * If the player fails to roll doubles after 3 turns, they must pay bail if they can afford it.
     *
     * @param player     The player in Jail.
     * @param bailAmount The amount required to pay bail if necessary.
     */
    private void tryRollForDoubles(Player player, int bailAmount) {
        int die1 = rollDie();
        int die2 = rollDie();

        System.out.println(player.getName() + " rolls " + die1 + " and " + die2 + ".");

        if (die1 == die2) {
            System.out.println(player.getName() + " rolled doubles and is free from Jail!");
            player.setInJail(false);
            player.setJailTurns(0);
            player.move(die1 + die2); // Move according to dice roll
        } else {
            player.setJailTurns(player.getJailTurns() + 1);
            System.out.println(player.getName() + " did not roll doubles. Jail turns: " + player.getJailTurns());

            // Force bail payment after 3 turns
            if (player.getJailTurns() >= 3) {
                System.out.println(player.getName() + " has been in Jail for 3 turns.");
                if (player.getMoney() >= bailAmount) {
                    System.out.println(player.getName() + " pays $" + bailAmount + " to get out of Jail.");
                    player.updateMoney(-bailAmount);
                    player.setInJail(false);
                } else {
                    System.out.println(player.getName() + " cannot afford bail and remains in Jail.");
                }
            }
        }
    }

    /**
     * Simulates rolling a single six-sided die.
     *
     * @return The result of the dice roll (1-6).
     */
    private int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }
}
