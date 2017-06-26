package towerdefensegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Smile tower button
 *
 * @author kuba
 */
public class SmileTowerButton extends MenuButton {

    private static SmileTower smileTower;

    /**
     * Constructor of Smile Tower Button
     *
     * @param gc game container
     */
    public SmileTowerButton(GameContainer gc) throws SlickException {
        smileTower = new SmileTower();
        buttonSprite = new Image("/sprites/smileTower.png");
        if (Board.getBlur()) {
            buttonSprite.setFilter(Image.FILTER_NEAREST);
        }
        buttonRange = new Rectangle(gc.getWidth() - gc.getWidth() * 0.1f, gc.getHeight() * 0.13f * 3, gc.getWidth() * 0.1f, gc.getHeight() * 0.13f); //ustawienie guzika w odpowiednim miejscu w sideMenu
        towerRange = new Circle(gc.getWidth() - gc.getWidth() * 0.1f, 0, SmileTower.getInitTowerRange());
    }

    @Override
    public String toString() {
        return "smileTowerButton";
    }

    /**
     *
     * @return tower range shape
     */
    @Override
    public Shape getTowerRange() {
        return towerRange;
    }

    /**
     *
     * @return Building object of smile tower
     */
    @Override
    public Building getBuilding() {
        return smileTower;
    }

    /**
     *
     * @param x position X
     * @param y position Y
     * @return Building object of smile tower
     */
    @Override
    public Building getBuilding(float x, float y) throws SlickException {
        return new SmileTower(x, y);
    }
}
