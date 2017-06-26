package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Russian Tower
 *
 * @author kuba
 */
public class RussianTower extends Building {

    private static float initTowerRange = 170; //początkowy zasięg wierzy (bez ulepszen)

    /**
     *
     * @param x position X
     * @param y position Y
     */
    public RussianTower(float x, float y) throws SlickException {
        buildingSprite = new Image("/sprites/russianTower.png");
        bulletSprite = new Image("SpritesSheets/shots/russianTowerShot.png");
        if (Board.getBlur()) {
            buildingSprite.setFilter(Image.FILTER_NEAREST);
            bulletSprite.setFilter(Image.FILTER_NEAREST);
        }
        buildingSprite.setCenterOfRotation(x, y);
        buildingShape = new Rectangle(x - 26.5f, y - 26.5f, 54, 54);
        range = initTowerRange;
        bulletFrequency = 500; //mniej -> częściej
        buildingShootingRange = new Circle(x, y, initTowerRange);
        bullet = new Circle(x, y, 5);
        enemiesOnRange = new ArrayList<>();
        positionX = x;
        positionY = y;
        firePower = 30;
        cost = 90;
    }

    /**
     * Narrowed constructor of tower (used for sideMenu operations)
     */
    public RussianTower() {
        cost = 90;
    }

    /**
     *
     * @return tower range from the beginning of the game
     */
    public static float getInitTowerRange() {
        return initTowerRange;
    }

    /**
     *
     * @return current cost of tower
     */
    @Override
    public int getCost() {
        return cost;
    }

}
