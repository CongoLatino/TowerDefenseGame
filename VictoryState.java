package towerdefensegame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * State of game responsible for victory
 * @author kuba
 */
public class VictoryState extends BasicGameState {

    private Image victoryBackground;
    private Rectangle menuButton;
    private Input userInput;

    /**
     * 
     * @return ID of victory state game
     */
    @Override
    public int getID() {
        return 4;
    }

    /**
     * Inits values at the beginning of the victory state
     * @param gc GameContainer
     * @param sbg StateBasedGame
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        try {
            victoryBackground = new Image("sprites/victory.png");
        } catch (SlickException e) {
            System.err.println(e.getMessage() + "\nvictory background image!!");
        }
        menuButton = new Rectangle(19, 711, 200, 70);
        userInput = new Input(Board.getHeight());
    }

    /**
     * Render graphics
     * @param gc Game Container
     * @param sbg State based game
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(victoryBackground, 0, 0);
        g.setColor(Color.gray);
        if(isMouseOnButton(menuButton)) g.draw(menuButton);
    }

    /**
     * Update game state
     * @param gc Game Container
     * @param sbg StateBasedGame
     * @param i gameDelta
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if (isButtonClicked(menuButton)) sbg.enterState(0, new FadeOutTransition(Color.black, 800), new FadeInTransition(Color.black, 800));
    }

    /**
     * Check if mouse is on the button
     * @param button button on which pointer supposed to be
     * @return true or false
     */
    public boolean isMouseOnButton(Rectangle button) {
        return (button.includes(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()) || button.contains(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()));
    }

    /**
     * Checks if left button of mouse is clicked on button
     * @param button Button
     * @return true or false
     */
    public boolean isButtonClicked(Rectangle button) {
        return isMouseOnButton(button) && userInput.isMouseButtonDown(0);
    }
}