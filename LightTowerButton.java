package towerdefensegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Light tower button class
 * @author kuba
 */
public class LightTowerButton extends MenuButton {

    private static LightTower lightTower;

    /**
     * Contructor of light tower button
     * @param gc game container
     */
    public LightTowerButton(GameContainer gc) throws SlickException {
        lightTower = new LightTower();
        buttonSprite = new Image("/sprites/lightTower.png");
        if(Board.getBlur()) buttonSprite.setFilter(Image.FILTER_NEAREST);
        buttonRange = new Rectangle(gc.getWidth() - gc.getWidth() * 0.1f, 0, gc.getWidth() * 0.1f, gc.getHeight() * 0.13f);
        towerRange = new Circle(gc.getWidth() - gc.getWidth() * 0.1f, 0, LightTower.getInitTowerRange());
        //buildingCost=LightTower.getCost();
    }

    @Override
    public String toString() {
        return "lightTowerButton";
    }

    /**
     * 
     * @return light tower button range
     */
    @Override
    public Shape getTowerRange() {
        return towerRange;
    }

    /**
     *
     * @return light tower object
     */
    @Override
    public Building getBuilding() {
        return lightTower;
    }

    /**
     * return light tower Building (for creation a new tower object in side menu)
     * @param x position X
     * @param y position Y
     * @return new Building
     */
    @Override
    public Building getBuilding(float x, float y) throws SlickException {
        return new LightTower(x, y);
    }
}
