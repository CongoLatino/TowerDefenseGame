package towerdefensegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Initial class
 * @author kuba
 */
public class SetupClass extends StateBasedGame {
   
    /**
     * Constructor of setup class
     * @param name name
     */
    public SetupClass(String name) {
        super(name);
    }

    /**
     * Main class
     * @param args args
     */
    public static void main(String[] args) throws SlickException {
        try {
            new Board();
        } catch (SlickException e) {
            System.err.println(e.getMessage() + "\n SetupClass - main");
        }
    }

    /**
     * Inherited from StateBasedgame. Use for adding states of every states in game
     * @param gc game container
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {

        addState(new MainMenu());
        addState(new Options());
        addState(new FirstGameState());
        addState(new GameOverState());
        addState(new VictoryState());
    }
}
