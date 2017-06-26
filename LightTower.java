package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Light Tower class
 * @author kuba
 */
public class LightTower extends Building {

    private static float initTowerRange = 230; //wartosc poczatkowa zasiegu wieży (bez ulepszen)
    //private static final int cost=50;

    /**
     * regular light tower costructor 
     * @param x posiiotn X
     * @param y posiiotn Y
     */
    public LightTower(float x, float y) throws SlickException {

        buildingSprite = new Image("/sprites/lightTower.png");
        bulletSprite = new Image("SpritesSheets/shots/shot2.png");
        if (Board.getBlur()) {
            buildingSprite.setFilter(Image.FILTER_NEAREST);
            bulletSprite.setFilter(Image.FILTER_NEAREST);
        }
        buildingSprite.setCenterOfRotation(x, y);
        buildingShape = new Rectangle(x - 26.5f, y - 26.5f, 54, 54);
        range = initTowerRange;
        bulletFrequency = 200; //mniej -> częściej
        buildingShootingRange = new Circle(x, y, range);
        bullet = new Circle(x, y, 5);
        enemiesOnRange = new ArrayList<>();
        positionX = x;
        positionY = y;
        firePower = 15;
        cost = 50;
    }

    /**
     * Narrowed contructor of light tower (used ine sideMenu operations)
     */
    public LightTower() throws SlickException //konstruktor wieży tylko do rysowania w menuMode
    {
        cost = 50;
        range = 230;
        buildingShootingRange = new Circle(0, 0, initTowerRange);
    }

    /**
     *
     * @return Tower range fro the beginning of the game
     */
    public static float getInitTowerRange() {
        return initTowerRange;
    }

    /**
     *
     * @return current cost of the tower
     */
    @Override
    public int getCost() {
        return cost;
    }

}
