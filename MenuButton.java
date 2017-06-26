package towerdefensegame;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Class represents menu buttons
 * @author kuba
 */
public abstract class MenuButton implements MenuButtonsInterface {

    /**
     *
     */
    protected Rectangle buttonRange;

    /**
     *
     */
    protected Image buttonSprite;

    /**
     *
     */
    protected Shape towerRange;

    /**
     *
     */
    protected Building building;
    //protected int buildingCost;

    /**
     *
     * @return button range
     */
    @Override
    public Rectangle getButtonRange() {
        return buttonRange;
    }

    /**
     *
     * @return button Image
     */
    @Override
    public Image getButtonSprite() {
        return buttonSprite;
    }

    /**
     *
     * @return tower building
     */
    @Override
    public Building getBuilding() {
        return building;
    }

}
