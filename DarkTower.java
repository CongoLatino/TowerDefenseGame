package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * DarkTower tower
 * @author kuba
 */
public class DarkTower extends Building {

    private static float initTowerRange = 190; //wartosc poczatkowa zasiegu wieży (bez ulepszen)

    /**
     * Contructor of DarkTower
     * @param x X position
     * @param y Y position
     */
    public DarkTower(float x, float y) throws SlickException {
        buildingSprite = new Image("/sprites/darkTower.png");
        bulletSprite = new Image("SpritesSheets/shots/darkTowerShot.png");
        if (Board.getBlur()) {
            bulletSprite.setFilter(Image.FILTER_NEAREST);
            buildingSprite.setFilter(Image.FILTER_NEAREST);
        }
        buildingShape = new Rectangle(x - 26.5f, y - 26.5f, 54, 54);
        buildingSprite.setCenterOfRotation(x, y);
        range = initTowerRange;
        bulletFrequency = 400; //mniej -> częściej
        buildingShootingRange = new Circle(x, y, range);
        bullet = new Circle(x, y, 5);
        enemiesOnRange = new ArrayList<>();
        positionX = x;
        positionY = y;
        firePower = 35;
        cost = 160;
    }

    /**
     * Contructor of DarkTower
     */
    public DarkTower() throws SlickException //konstruktor wieży tylko do rysowania w menuMode
    {
        cost = 160;
        range = 230;
        buildingShootingRange = new Circle(0, 0, initTowerRange);
    }

    /**
     *
     * @return Range of tower from the beginning of the game
     */
    public static float getInitTowerRange() {
        return initTowerRange;
    }

    /**
     *
     * @return current tower cost
     */
    @Override
    public int getCost() {
        return cost;
    }

}
