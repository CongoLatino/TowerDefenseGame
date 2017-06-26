package towerdefensegame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Interface for buttons in game
 * @author kuba
 */
public interface MenuButtonsInterface {

    /**
     *
     * @return button range
     */
    public Rectangle getButtonRange();

    /**
     *
     * @return button Image
     */
    public Image getButtonSprite();

    /**
     *
     * @return tower range
     */
    public Shape getTowerRange();

    /**
     *
     * @return string of class
     */
    @Override
    public String toString();

    /**
     *
     * @return object of building
     */
    public Building getBuilding();

    /**
     *
     * @param x position X
     * @param y position Y
     * @return object of building
     */
    public Building getBuilding(float x, float y) throws SlickException;
}
