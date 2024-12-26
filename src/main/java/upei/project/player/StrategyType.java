package upei.project.player;


import upei.project.core.tiles.HousableProperty;
import upei.project.core.tiles.Property;

public enum StrategyType {
    AGGRESSIVE {
        @Override
        public boolean decideToBuyProperty(Player player, Property property, int bailAmount) {
            // Aggressive players buy any property they can afford
            return (player.getMoney() - property.getPrice()) > bailAmount;
        }
        @Override
        public int decideToBuyHouse(Player player, HousableProperty property) {
            // Aggressive strategy: Buy 3 houses if affordable
            int houseCost = property.getPrice();
            if (player.getMoney() > (houseCost * 3)) {
                return 3; // Buy 3 houses
            }
            return 0; // Don't buy if not enough money
        }
    },
    CONSERVATIVE {
        @Override
        public boolean decideToBuyProperty(Player player, Property property, int bailAmount) {
            // Conservative players buy properties only if they keep a cushion of cash
            return (player.getMoney() - property.getPrice()) > bailAmount + 500;
        }
        @Override
        public int decideToBuyHouse(Player player, HousableProperty property) {
            // Conservative strategy: Buy 1 house if it won't make the player bankrupt
            int houseCost = property.getPrice();
            if ((player.getMoney() - houseCost) > 500) {
                return 1; // Buy 1 house
            }
            return 0; // Don't buy if it risks bankruptcy
        }
    },
    RANDOM {
        @Override
        public boolean decideToBuyProperty(Player player, Property property, int bailAmount) {
            // Random strategy buys randomly regardless of financial status
            return Math.random() < 0.5;
        }
        public int decideToBuyHouse(Player player, HousableProperty property) {
            // Random strategy: Buy 1 or 3 houses randomly, if affordable
            int houseCost = property.getPrice();
            if (player.getMoney() > houseCost * 3) {
                return Math.random() < 0.5 ? 3 : 1; // Randomly decide between 1 or 3 houses
            } else if (player.getMoney() >= houseCost) {
                return 1; // Buy 1 house if 3 are not affordable
            }
            return 0; // Don't buy if no houses can be afforded
        }
    };

//    public abstract boolean decideToBuyHouse(Player player)
    // Abstract method to be implemented by each strategy
    public abstract boolean decideToBuyProperty(Player player, Property property, int bailAmount);
    public abstract int decideToBuyHouse(Player player, HousableProperty property);

}
