package towerdefensegame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Abstract class represents enemy 
 * @author kuba
 */
public abstract class Enemy implements EnemyInterface {

    /**
     * angle of shooting from towers
     */
    protected double angle;

    /**
     * Auxiliary variable for calculating angle
     */
    protected double deltaX;

    /**
     * Auxiliary variable for calculating angle
     */
    protected double deltaY;

    /**
     * Next X position of enemy moving
     */
    protected int newXposition = 2;

    /**
     * Next Y position of enemy moving
     */
    protected int newYposition = 3;

    /**
     * Shape of enemy
     */
    protected Shape enemyChar = null;

    /**
     * Animation of enemy running
     */
    protected Animation enemyRunAnimation;

    /**
     * Animation of enemy fighting
     */
    protected Animation enemyFightAnimation;

    /**
     * Animation of enemy dying
     */
    protected Animation enemyDieAnimation;

    /**
     * array of Images with die frames
     */
    protected Image[] dieSheet;

    /**
     * array of Images with run frames
     */
    protected Image[] runSheet;

    /**
     * array of Images with fight frames
     */
    protected Image[] fightSheet;

    /**
     * Flag if enemy is moving
     */
    protected boolean isMoving = true;

    /**
     * flag if is enemy on attack
     */
    protected boolean isOnAttack = false;

    /**
     * speed of enemy
     */
    protected float speed;

    /**
     * hp of enemy
     */
    protected int healthLevel;

    /**
     * attack power of enemy
     */
    protected int attackPower;

    /**
     * ammount of cash that enemy drops
     */
    protected int dropCash;

    /**
     * Moving physics 
     * @param movingPoints Points on with enemy is moving
     * @param gameDelta gameDelta
     */
    @Override
    public void move(float[] movingPoints, int gameDelta) {
        if (isMoving) {
            float i = (float) gameDelta;
            float newX = movingPoints[newXposition]; //następne punkty, do którego dojdą przeciwnicy
            float newY = movingPoints[newYposition];
            deltaX = newX - enemyChar.getCenterX();
            deltaY = newY - enemyChar.getCenterY();
            angle = (float) Math.atan2(deltaY, deltaX);
            enemyChar.setCenterX(enemyChar.getCenterX() + speed * (float) Math.cos(angle) * i);
            enemyChar.setCenterY(enemyChar.getCenterY() + speed * (float) Math.sin(angle) * i);
            if ((float) round(enemyChar.getCenterX(), 0) == (float) round(movingPoints[newXposition], 0)
                    && (float) round(enemyChar.getCenterY(), 0) == (float) round(movingPoints[newYposition], 0)) //jeśli przeciwnik doszedł mniej wiecje w to miejsce (round)
            {
                if (newXposition < movingPoints.length - 2.0f && newYposition < movingPoints.length - 1.0f) //jeśli przeciwnik nie doszedł do ostatniego punktu (zamek) ustawiamy następny
                {
                    newXposition += 2;
                    newYposition += 2;
                }
                //else isMoving=false; //nie dziala
            }
        }
    }
    
    /**
     * Draw enemies
     * @param g Graphics class from Slick2D library
     * @return Enemy if is dead or null if it's still alive
     */
    @Override
    public Enemy drawEnemy(Graphics g) {
        if (isDead()) //rysowanie animacji dla śmierci przeciwnika
        {
            setIsMoving(false);

            g.drawAnimation(enemyDieAnimation, enemyChar.getCenterX() - 20.0f, enemyChar.getCenterY() - 50.0f);
            if (enemyDieAnimation.getFrame() == enemyDieAnimation.getFrameCount() - 1) {
                Player.addCash(dropCash());
                return this;
            }
        } else if (Castle.getCastleRange().intersects(enemyChar) || Castle.getCastleRange().contains(enemyChar)) //rysowanie aninmacji przy ataku na zamek 
        {
            setIsMoving(false);
            setIsOnAttack(true);
            g.drawAnimation(enemyFightAnimation, Castle.getCastleRange().getMinX(), Castle.getCastleRange().getCenterY());
        } else //rysowanie animacji przy bieganiu przeciwnika
        {
            g.drawAnimation(enemyRunAnimation, enemyChar.getCenterX() - 20.0f,
                    enemyChar.getCenterY() - 50.0f);
        }
        return null;
    }

    /**
     * 
     * @return shape of enemy
     */
    @Override
    public Shape getEnemyChar() {
        return enemyChar;
    }

    /**
     * Set if enemy is moving
     * @param isMoving Flag if is enemy moving
     */
    @Override
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     *
     * @return true if enemy is dead
     * false if its alive
     */
    @Override
    public boolean isDead() {
        if (healthLevel <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return EnemyFightAnimation
     */
    @Override 
    public Animation getEnemyFightAnimation() {
        return enemyFightAnimation;
    }

    /**
     *
     * @return EnemyRunAnimation
     */
    @Override
    public Animation getEnemyRunAnimation() {
        return enemyRunAnimation;
    }

    /**
     *
     * @return EnemyDieAnimation
     */
    @Override
    public Animation getEnemyDieAnimation() {
        return enemyDieAnimation;
    }

    /**
     * Round number in more efficient way
     * @param value Value of number to  round
     * @param places how much places after a comma
     * @return rounded number
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *
     * @return current hp of enemy
     */
    @Override
    public int getHealthLevel() {
        return healthLevel;
    }

    /**
     * Set new hp 
     * @param healthLevel new hp of enemy
     */
    @Override
    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    /**
     *
     * @return current cash of enemy
     */
    @Override
    public int dropCash() {
        return dropCash;
    }

    /**
     *
     * @return true if enemy is on attack
     * false if its not
     */
    @Override
    public boolean isOnAttack() {
        return isOnAttack;
    }

    /**
     * Set if enemy is on attack
     * @param onAttack Flag if it's attacking
     */
    @Override
    public void setIsOnAttack(boolean onAttack) {
        isOnAttack = onAttack;
    }

    /**
     * 
     * @return enemy attack power
     */
    @Override
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Set new attack power of enemy
     * @param newAttackPower new attack power
     */
    @Override
    public void setAttackPower(int newAttackPower) {
        attackPower = newAttackPower;
    }

}
