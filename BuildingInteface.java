package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Interface responsible for every building in the game
 * @author kuba
 */
public interface BuildingInteface {

    /**
     * This method supports shooting physics in game
     * @param delta game delta
     */
    public void shooting(int delta);

    /**
     * This method update an array with enemies which are in the towers shooting area
     * @param enemies Array of all enemies spawned in game
     */
    public void updateEnemiesOnRange(ArrayList<Enemy> enemies);

    /**
     * Check if the enemy in param is on range
     * @param enemy enemy
     * @return true - if enemy is on shooting range
     *          false - if enemy isn't in shooting range 
     */
    public boolean enemyOnRange(Enemy enemy);

    /**
     * 
     * @return Returns bullet shape
     */
    public Shape getBullet();

    /**
     *
     * @return Returns bullet sprite
     */
    public Image getBulletSprite();

    /**
     *
     * @return Returns array with enemies on range
     */
    public ArrayList<Enemy> getEnemiesOnRange();
    
    /**
     * Set new cost of building
     * @param newCost - new cost of building
     */
    public void setCost(int newCost);
    
    /**
     * 
     * @return Returns current building cost
     */
    public int getCost();
}
