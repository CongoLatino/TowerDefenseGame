package towerdefensegame;

import java.util.ArrayList;
import java.util.prefs.Preferences;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Class responsible for options in game
 *
 * @author kuba
 */
public class Options extends BasicGameState {

    Preferences userPreferences = Preferences.userRoot(); //to do ogarnięcia

    private Input userInput;
    private Image optionsBackground;

    private Line vSyncCheckLine;
    private Line blurCheckLine;
    private Line fpsCheckLine;
    private Line musicCheckLine;

    private Rectangle leaveButton;
    private Rectangle vSyncButton;
    private Rectangle blurButton;
    private Rectangle musicButton;
    private Rectangle fpsButton;

    private ArrayList<Rectangle> buttons;

    /**
     *
     * @return ID of current game state
     */
    @Override
    public int getID() {
        return 2;
    }

    /**
     * Inits initial values in this game state
     *
     * @param gc game container
     * @param sbg state based game
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        try {
            optionsBackground = new Image("/sprites/options.png");
        } catch (SlickException e) {
            System.err.println(e.getMessage() + "\nOptions backround image error");
        }
        userInput = new Input(Board.getHeight());

        leaveButton = new Rectangle(67, 692, 200, 70);
        vSyncButton = new Rectangle(64, 214, 200, 70);
        blurButton = new Rectangle(64, 315, 200, 70);
        fpsButton = new Rectangle(64, 410, 200, 70);
        musicButton = new Rectangle(64, 516, 200, 70);

        if (Board.getVSync()) {
            vSyncCheckLine = new Line(337, 270, 383, 270);
        } else {
            vSyncCheckLine = new Line(383, 270, 429, 270);
        }
        if (Board.getBlur()) {
            blurCheckLine = new Line(337, 366, 383, 366);
        } else {
            blurCheckLine = new Line(383, 366, 429, 366);
        }
        if (Board.getFps()) {
            fpsCheckLine = new Line(337, 461, 383, 461);
        } else {
            fpsCheckLine = new Line(383, 461, 429, 461);
        }
        musicCheckLine = new Line(338, 563, 384, 563);

        buttons = new ArrayList<>();
        buttons.add(vSyncButton);
        buttons.add(blurButton);
        buttons.add(fpsButton);
        buttons.add(musicButton);
        buttons.add(leaveButton);
        Soundtrack.music.play();
    }

    /**
     * update game state
     *
     * @param gc game container
     * @param sbg state based game
     * @param i game delta
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    /**
     *
     * @param gc game container
     * @param sbg state based game
     * @param g graphics class
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        optionsBackground.draw(0, 0, Board.getWidth(), Board.getHeight());
        g.setColor(Color.gray);
        for (int i = 0; i < buttons.size(); i++) {
            if (isMouseOnButton(buttons.get(i))) {
                g.draw(buttons.get(i));
            }
            if (isButtonClicked(buttons.get(i))) {
                afterClick(sbg, i);
            }
        }
        g.draw(vSyncCheckLine);
        g.draw(blurCheckLine);
        g.draw(fpsCheckLine);
        g.draw(musicCheckLine);
    }

    /**
     * Check if mouse is on button
     *
     * @param button button
     * @return true or false
     */
    public boolean isMouseOnButton(Rectangle button) {
        return (button.includes(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()) || button.contains(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY()));
    }

    /**
     * Check if left button of mouse is clicked
     *
     * @param button button
     * @return true or false
     */
    public boolean isButtonClicked(Rectangle button) {
        return isMouseOnButton(button) && userInput.isMouseButtonDown(0); 
    }

    /**
     *
     * @param sbg state based game
     * @param i game delta
     */
    public void afterClick(StateBasedGame sbg, int i) {
        switch (i) {
            case 0: //vSync
                Board.setVSync(!Board.getVSync());
                if (Board.getVSync()) {
                    vSyncCheckLine = new Line(337, 270, 383, 270);
                } else {
                    vSyncCheckLine = new Line(383, 270, 429, 270);
                }

                break;

            case 1: //Blur
                Board.setBlur(!Board.getBlur());
                if (Board.getBlur()) {
                    blurCheckLine = new Line(337, 366, 383, 366);
                } else {
                    blurCheckLine = new Line(383, 366, 429, 366);
                }
                break;
            case 2: // FPS
                Board.setFps(!Board.getFps());
                if (Board.getFps()) {
                    fpsCheckLine = new Line(337, 461, 383, 461);
                } else {
                    fpsCheckLine = new Line(383, 461, 429, 461);
                }
                break;
            case 3:
                if (Soundtrack.music.playing()) {
                    Soundtrack.music.stop();
                    musicCheckLine = new Line(388, 562, 429, 562);
                    
                } else {
                    Soundtrack.music.play();
                    Soundtrack.music.loop();
                    musicCheckLine = new Line(338, 562, 384, 562);
                }
                break;
            case 4:
                sbg.enterState(0, new FadeOutTransition(Color.black, 800), new FadeInTransition(Color.black, 800));
                break;
        }
    }
}
