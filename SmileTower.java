package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Smile Tower class
 * @author kuba
 */
public class SmileTower extends Building {

    private static float initTowerRange = 200; //początkowy zasięg wierzy (bez ulepszen)

    /**
     * Constructor of smile tower
     * @param x position X
     * @param y position Y
     */
    public SmileTower(float x, float y) throws SlickException {
        buildingSprite = new Image("/sprites/smileTower.png");
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
        firePower = 50;
        cost = 250;
//        smileTowerCost=250;
    }

    /**
     * Contrucotr of smile tower
     */
    public SmileTower() {
        cost = 250;
    }

    /**
     *
     * @return initTowerRange
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
