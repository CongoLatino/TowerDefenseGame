package towerdefensegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Dark tower button used in menus
 * @author kuba
 */
public class DarkTowerButton extends MenuButton {

    private DarkTower darkTower;

    /**
     * Contructor of DarkTower
     * @param gc GameContainer from Slick2D library
     */
    public DarkTowerButton(GameContainer gc) throws SlickException {
        darkTower = new DarkTower();
        buttonSprite = new Image("/sprites/darkTower.png");
        if(Board.getBlur()) buttonSprite.setFilter(Image.FILTER_NEAREST);
        buttonRange = new Rectangle(gc.getWidth() - gc.getWidth() * 0.1f, gc.getWidth() * 0.1f * 2, gc.getWidth() * 0.1f, gc.getHeight() * 0.13f);
        towerRange = new Circle(gc.getWidth() - gc.getWidth() * 0.1f, 0, DarkTower.getInitTowerRange());
        //buildingCost=DarkTower.getCost();
    }

    @Override
    public String toString() {
        return "darkTowerButton";
    }

    /**
     *
     * @return TowerRange shape (circle mainly)
     */
    @Override
    public Shape getTowerRange() {
        return towerRange;
    }

    /**
     *
     * @return Building narrow version of dark tower object (only cost and range) 
     */
    @Override
    public Building getBuilding() {
        return darkTower;
    }

    /**
     * Returns building with position in parameter, used for sideMenu operations (creating new building)
     * @param x X position
     * @param y Y position
     * @return building 
     */
    @Override
    public Building getBuilding(float x, float y) throws SlickException {
        return new DarkTower(x, y);
    }

}
