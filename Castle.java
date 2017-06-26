package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
//import towerdefensegame.FirstGameState;

/**
 * This class represents main castle in the game, where player is obligated to
 * defense this. When this castle is destroyed game is over
 *
 * @author kuba
 */
public class Castle extends Building {

    private int waitTime = 1000;   //częstotliwość spadania życia wieży przeciwników
    private int waitCounter = waitTime + 1;
    static int currentHealthLevel;
    static int initHealthLevel = 200;
    static Shape buildingRange;
    static Rectangle castleBuildingBorders;
    private Animation explosionAnimation;

    /**
     * Constructor of castle
     *
     * @param x X position of the castle
     * @param y Y postion of the castle
     */
    public Castle(float x, float y) throws SlickException {
        buildingSprite = new Image("sprites/castle8.png");
        explosionSheet = new Image[]{new Image("SpritesSheets/explosion/boom01.png"),
            new Image("SpritesSheets/explosion/boom02.png"),
            new Image("SpritesSheets/explosion/boom03.png"),
            new Image("SpritesSheets/explosion/boom04.png"),
            new Image("SpritesSheets/explosion/boom05.png"),
            new Image("SpritesSheets/explosion/boom06.png"),
            new Image("SpritesSheets/explosion/boom07.png"),
            new Image("SpritesSheets/explosion/boom08.png"),
            new Image("SpritesSheets/explosion/boom09.png"),
            new Image("SpritesSheets/explosion/boom10.png"),
            new Image("SpritesSheets/explosion/boom11.png")};
        explosionAnimation = new Animation(explosionSheet, 120);
        if (Board.getBlur()) {
            buildingSprite.setFilter(Image.FILTER_NEAREST);
        }
        buildingShootingRange = new Circle(x, y, 70);
        buildingRange = new Circle(x, y, 70);
        buildingShape = new Rectangle(x - 20, y - 60, 130, 220);
        castleBuildingBorders = new Rectangle(x, y-30, 130, 130);
        currentHealthLevel = initHealthLevel;
    }

    /**
     *
     * @return castle Image
     */
    public Image getCastle() {
        return buildingSprite;
    }

    /**
     *
     * @return castle range shape (circle mainly)
     */
    public static Shape getCastleRange() {
        return buildingRange;
    }

    /**
     *
     * @return true - if currentHealthLevel is lower or equeal than 0
     * false where it is higher than0
     */
    public boolean isDestroyed() {
        return currentHealthLevel <= 0;
    }

    /**
     * Set new hp of the castle
     *
     * @param hp - new health level
     */
    public void setHealthLevel(int hp) {
        currentHealthLevel = hp;
    }

    /**
     *
     * @return Level of life at the beginning of the game
     */
    public int getInitHealthLevel() {
        return initHealthLevel;
    }

    /**
     * Supports action when some enemy is attacking the castle. If it is true the HP of the castle is going down
     * @param enemies Array of enemies spawned in the game
     */
    public void underAttack(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.isOnAttack() && waitCounter > waitTime) {
                if(currentHealthLevel>0) currentHealthLevel -= enemy.getAttackPower();
                waitCounter = 0;
            }
            waitCounter++;
        }
    }

    /**
     * Draws the castle sprite
     * @param g Graphics class from Slick2D library
     */
    public void drawCastle(Graphics g) {
        if (!FirstGameState.gameOver) {
            if (!isDestroyed()) {
                buildingSprite.draw(buildingShootingRange.getCenterX() - 20, buildingShootingRange.getCenterY() - 115);
            } else {
                g.drawAnimation(explosionAnimation, buildingShootingRange.getCenterX() - 20, buildingShootingRange.getCenterY() - 60);
                if (explosionAnimation.getFrame() == explosionAnimation.getFrameCount() - 1) {
                    FirstGameState.gameOver = true;
                }
            }
        }
    }
    /**
     * 
     * @return borders of castle. It's used to forbid building new towers on castle
     */
    public static Shape getCastleBuildingRange(){
        return castleBuildingBorders;
    }

}
