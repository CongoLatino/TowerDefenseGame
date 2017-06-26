package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Class represents buildings
 * @author kuba
 */
public abstract class Building implements BuildingInteface //wszystkie budynki moga atakować
{

    /**
     *
     */
    protected Image buildingSprite;

    /**
     *
     */
    protected Shape buildingShootingRange;

    /**
     *
     */
    protected Shape buildingShape;

    /**
     *
     */
    protected float range;

    /**
     *
     */
    protected ArrayList<Enemy> enemiesOnRange;

    /**
     *
     */
    protected Enemy aimedEnemy;

    /**
     *
     */
    protected Shape bullet;

    /**
     *
     */
    protected Image bulletSprite;

    /**
     *
     */
    protected float bulletSpeed = 1.4f;

    /**
     *
     */
    protected int bulletFrequency;

    /**
     *
     */
    protected int bulletFreq;

    /**
     *
     */
    protected int firePower;

    /**
     *
     */
    protected float deltaX;

    /**
     *
     */
    protected float deltaY;

    /**
     *
     */
    protected float angle;

    /**
     *
     */
    protected float positionX;

    /**
     *
     */
    protected float positionY;

    /**
     *
     */
    protected boolean onShot = false;

    /**
     *
     */
    protected boolean isEnemyHit = true;

    /**
     *
     */
    protected Image[] explosionSheet;

    /**
     *
     */
    protected int cost;
    /**
     * 
     */

    /**
     *
     * @return shape of building range
     */
    public Shape getBuildingRange() {
        return buildingShootingRange;
    }

    /**
     *
     * @return Image of building
     */
    public Image getBuildingSprite() {
        return buildingSprite;
    }

    /**
     *
     * @return Frames of explosion
     */
    public Image[] getExplosion() {
        return explosionSheet;
    }

    /**
     *
     * @param gameDelta game delta
     */
    @Override
    public void shooting(int gameDelta) {
        float i = (float) gameDelta;
        if (!enemiesOnRange.isEmpty()) {

            if (isEnemyHit) //obłusga pierwszego kontaktu z przeciwnikiem i zmiany na kolejnego przy kolejnych strzałach (isEnemyHit poczatkowo = true)
            {

                bullet.setCenterX(buildingShootingRange.getCenterX()); //ustawienie kuli we wieży
                bullet.setCenterY(buildingShootingRange.getCenterY());
                aimedEnemy = enemiesOnRange.get(0);  //namierzenie peirwszego(i później kolejnego) przeciwnika - zawsze pierwszego z enemiesOnRange

                isEnemyHit = false;  //mamy namierzonego wroga, więc dopoóki kula w niego nie uderzy będzie się wykonywał poniższy else i kolejny else (ruch kuli)
            }

            if (buildingShootingRange.contains(bullet)) // ?? ale działa ->> a powinno na logike działać !buildingShootingRange.contains(bullet)
            {
                bullet.setCenterX(buildingShootingRange.getCenterX()); //ustawienie kuli we wieży
                bullet.setCenterY(buildingShootingRange.getCenterY());
            }

            if (aimedEnemy.getEnemyChar().intersects(bullet)) //przeciwnik dostaje kulke
            {
                bullet.setCenterX(buildingShootingRange.getCenterX()); //ustawienie kuli we wieży
                bullet.setCenterY(buildingShootingRange.getCenterY());
                aimedEnemy.setHealthLevel(enemiesOnRange.get(0).getHealthLevel() - firePower); //obnizenie życia o wartość firePower
                if (aimedEnemy.isDead()) {
                    enemiesOnRange.remove(aimedEnemy);  //jeśli umarł to usuwamy go z enemiesOnRange
                }
                isEnemyHit = true; //flaga na 1, co pozwoli wejść w peirwszego ifa i zmienić przeciwnika 
                bulletFreq = bulletFrequency;
            } else {
                deltaX = aimedEnemy.getEnemyChar().getCenterX() - bullet.getCenterX();
                deltaY = aimedEnemy.getEnemyChar().getCenterY() - bullet.getCenterY(); //oblicznie dla niego nowych delt i kąta
                angle = (float) Math.atan2(deltaY, deltaX);
                if (bulletFreq < 1) {
                    bullet.setCenterX(bullet.getCenterX() + bulletSpeed * (float) Math.cos(angle) * i); //ruch
                    bullet.setCenterY(bullet.getCenterY() + bulletSpeed * (float) Math.sin(angle) * i);
                    bulletFreq = 0;
                    aimedEnemy = enemiesOnRange.get(0);
                }
                bulletFreq--;
            }
        } else {

            bullet.setCenterX(buildingShootingRange.getCenterX());
            bullet.setCenterY(buildingShootingRange.getCenterY());
        }

    }

    /**
     * Updates enemies from array of enemies in the game to array enemies on the range
     * @param enemies array of enemies in the game
     */
    @Override
    public void updateEnemiesOnRange(ArrayList<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy temp;
            temp = enemies.get(i);
            if ((buildingShootingRange.contains(temp.getEnemyChar()) || buildingShootingRange.intersects(temp.getEnemyChar()))) {
                if (!enemiesOnRange.contains(temp)) {
                    enemiesOnRange.add(temp);
                }
            } else {
                enemiesOnRange.remove(temp);
            }
        }
    }

    /**
     * Is enemy on range?
     * @param enemy enemy
     * @return true or false
     */
    @Override
    public boolean enemyOnRange(Enemy enemy) {
        return buildingShootingRange.contains(enemy.getEnemyChar());
    }

    /**
     *
     * @return shape of bullet
     */
    @Override
    public Shape getBullet() {
        return bullet;
    }

    /**
     *
     * @return image of bullet
     */
    @Override
    public Image getBulletSprite() {
        return bulletSprite;
    }

    /**
     *
     * @return array of enemies wich are on the towers range
     */
    @Override
    public ArrayList<Enemy> getEnemiesOnRange() {
        return enemiesOnRange;
    }

    /**
     *
     * @return cost cost
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     *
     * @param newCost newCost
     */
    @Override
    public void setCost(int newCost) {
        cost = newCost;
    }

    /**
     *
     * @return range range
     */
    public float getRange() {
        return range;
    }

    /**
     *
     * @param range range
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     *
     * @return bullet freq
     */
    public int getBulletFrequency() {
        return bulletFrequency;
    }

    /**
     *
     * @return shape of the building
     */
    public Shape getBuildingShape() {
        return buildingShape;
    }

    /**
     * Draws bullets
     */
    public void drawBullets() {
        bulletSprite.draw(bullet.getCenterX() - 10, bullet.getCenterY() - 10);
    }

    /**
     * Draws towers
     */
    public void drawTowers() {
        buildingSprite.drawCentered(buildingShootingRange.getCenterX(), buildingShootingRange.getCenterY());
    }

}
