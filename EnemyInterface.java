package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 * Interface of enemies in the game
 *
 * @author kuba
 */
public interface EnemyInterface {

    /**
     * Moving Physics of enemies
     *
     * @param movingPoints array of points (positions) on which enemies are
     * moving
     * @param i game delta
     */
    public void move(float[] movingPoints, int i);

    /**
     *
     * @return shape of enemy
     */
    public Shape getEnemyChar();

    /**
     * Set if enemy is moving
     * @param isMoving is moving
     */
    public void setIsMoving(boolean isMoving);

    /**
     *
     * @return fight animation
     */
    public Animation getEnemyFightAnimation();

    /**
     *
     * @return run animation
     */
    public Animation getEnemyRunAnimation();

    /**
     *
     * @return die animation
     */
    public Animation getEnemyDieAnimation();

    /**
     * Check if enemy is dead
     * @return true or false
     */
    public boolean isDead();

    /**
     *
     * @param healthLevel hp
     */
    public void setHealthLevel(int healthLevel);

    /**
     *
     * @return hp
     */
    public int getHealthLevel();

    /**
     *
     * @return cash
     */
    public int dropCash();

    /**
     *
     * @return current ammount of enemy cash
     */
    public boolean isOnAttack();

    /**
     * Sets if enemy is on attack
     * @param onAttack onAttack
     */
    public void setIsOnAttack(boolean onAttack);

    /**
     *
     * @return attack power
     */
    public int getAttackPower();

    /**
     *
     * @param newAttackPower new attack
     */
    public void setAttackPower(int newAttackPower);

    /**
     * Draws enemy
     * @param g Gpraphics class form Slick2D
     * @return Enemy if is dead or null if is not
     */
    public Enemy drawEnemy(Graphics g);
}
