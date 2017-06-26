package towerdefensegame;

import java.util.ArrayList;
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
 * Class represents main menu 
 * @author kuba
 */
public class MainMenu extends BasicGameState {

    private Image menuBackground;
    private Rectangle startButton;
    private Rectangle optionsButton;
    private Rectangle exitButton;
    private ArrayList<Rectangle> buttons;
    private Input userInput;

    /**
     *
     * @return ID of current State Game
     */
    @Override
    public int getID() {
        return 0;
    }

    /**
     * Inits initial values
     * @param gc game container
     * @param sbg state based game
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        try {
            menuBackground = new Image("/sprites/mainMenu.png");
        } catch (SlickException e) {
            System.err.println(e.getMessage() + "\nMenu background image");
        }
        startButton = new Rectangle(143, 117, 200, 70);
        optionsButton = new Rectangle(143, 210, 200, 70);
        exitButton = new Rectangle(143, 304, 200, 70);
        userInput = new Input(800);
        buttons = new ArrayList<>();
        buttons.add(startButton);
        buttons.add(optionsButton);
        buttons.add(exitButton);
    }

    /**
     *
     * @param gc game container
     * @param sbg state based game
     * @param i game delta
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
               if(isButtonClicked(startButton)) sbg.enterState(1, new FadeOutTransition(Color.black, 1200), new FadeInTransition(Color.black, 1200));
               else if(isButtonClicked(optionsButton)) sbg.enterState(2, new FadeOutTransition(Color.black, 800), new FadeInTransition(Color.black, 800));
               else if(isButtonClicked(exitButton)) gc.exit();
    }

    /**
     *
     * @param gc game container
     * @param sbg state based game
     * @param g graphics class from Slick2d library
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        menuBackground.draw(0, 0, Board.getWidth(), Board.getHeight());
        g.setColor(Color.gray);
        buttons.stream().filter((button) -> (isMouseOnButton(button))).forEachOrdered((button) -> {
            g.draw(button);
        });
        
    }

    /**
     * Check if mopuse is on the button
     * @param button button
     * @return true or false
     */
    public boolean isMouseOnButton(Rectangle button) {
        return (button.includes(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()) || button.contains(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()));
    }

    /**
     * check if left button of mouse is clicked on the button
     * @param button button
     * @return true or false
     */
    public boolean isButtonClicked(Rectangle button) {
        return isMouseOnButton(button) && userInput.isMouseButtonDown(0);
    }

}
