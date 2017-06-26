package towerdefensegame;

/**
 * This class represents player character
 * @author kuba
 */
public class Player {

    private static int initPlatyerCash;

    static int playerCash = 99;

    /**
     *
     * @return current player cash
     */
    public static int getCash() {
        return playerCash;
    }

    /**
     * Add money to player's wallet 
     * @param value Additional cash
     */
    public static void addCash(int value) {
        playerCash += value;
    }

    /**
     * Reduce money from player wallet
     * @param value Value to reduce of
     */
    public static void reduceCash(int value) {
        playerCash -= value;
    }

}
