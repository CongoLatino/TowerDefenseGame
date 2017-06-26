package towerdefensegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Class represents russian tower button
 * @author kuba
 */
public class RussianTowerButton extends MenuButton {

    private static RussianTower russianTower;

    /**
     * Contructor of russian tower button
     *
     * @param gc game container
     */
    public RussianTowerButton(GameContainer gc) throws SlickException {
        russianTower = new RussianTower();
        buttonSprite = new Image("/sprites/russianTower.png");
        if (Board.getBlur()) {
            buttonSprite.setFilter(Image.FILTER_NEAREST);
        }
        buttonRange = new Rectangle(gc.getWidth() - gc.getWidth() * 0.1f, gc.getHeight() * 0.13f, gc.getWidth() * 0.1f, gc.getHeight() * 0.13f);
        towerRange = new Circle(gc.getWidth() - gc.getWidth() * 0.1f, gc.getHeight() * 0.13f, RussianTower.getInitTowerRange());
        //buildingCost=RussianTower.getCost();
    }

    @Override
    public String toString() {
        return "russianTowerButton";
    }

    /**
     *
     * @return shape of tower range
     */
    @Override
    public Shape getTowerRange() {
        return towerRange;
    }

    /**
     *
     * @return building object of this tower
     */
    @Override
    public Building getBuilding() {
        return russianTower;
    }

    /**
     *
     * @param x position X
     * @param y position Y
     * @return Building object of this tower (use to create new object in
     * SideMenu
     */
    @Override
    public Building getBuilding(float x, float y) throws SlickException {
        return new RussianTower(x, y);
    }

}
