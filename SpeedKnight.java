package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Speed Knight class
 * @author kuba
 */
public class SpeedKnight extends Enemy {

    /**
     * Constructor of speed knight
     * @param initialX position X
     * @param initialY position Y
     */
    public SpeedKnight(float initialX, float initialY) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 5);
        runSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/run/Run1.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run2.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run3.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run4.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run5.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run6.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run7.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run8.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run9.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run10.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/attack/Attack1.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack2.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack3.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack4.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack5.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack6.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack7.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack8.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack9.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack10.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/die/Dead1.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead2.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead3.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead4.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead5.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead6.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead7.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead8.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead9.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead10.png")};
        if (Board.getBlur()) {
            for (int i = 0; i < runSheet.length - 1; i++) {
                runSheet[i].setFilter(Image.FILTER_NEAREST);
            }
            for (int i = 0; i < dieSheet.length - 1; i++) {
                dieSheet[i].setFilter(Image.FILTER_NEAREST);
            }
            for (int i = 0; i < fightSheet.length - 1; i++) {
                fightSheet[i].setFilter(Image.FILTER_NEAREST);
            }
        }
        enemyRunAnimation = new Animation(runSheet, 75);
        enemyFightAnimation = new Animation(fightSheet, 120);
        enemyDieAnimation = new Animation(dieSheet, 50);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 90;
        speed = 0.26f;
        attackPower = 7;
        dropCash = 3;
    }
    
    /**
     * Contructor of SpeedKnight with improvements
     * @param initialX position X
     * @param initialY position Y
     * @param increaseHp Additional Hp
     * @param increaseSpeed Additional Speed
     * @param increaseAttack Additional Attack power
     */
    public SpeedKnight(float initialX, float initialY, int increaseHp, float increaseSpeed, int increaseAttack) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 5);
        runSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/run/Run1.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run2.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run3.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run4.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run5.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run6.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run7.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run8.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run9.png"),
            new Image("SpritesSheets/SpeedKnight/png/run/Run10.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/attack/Attack1.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack2.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack3.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack4.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack5.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack6.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack7.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack8.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack9.png"),
            new Image("SpritesSheets/SpeedKnight/png/attack/Attack10.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/SpeedKnight/png/die/Dead1.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead2.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead3.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead4.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead5.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead6.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead7.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead8.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead9.png"),
            new Image("SpritesSheets/SpeedKnight/png/die/Dead10.png")};
        if (Board.getBlur()) {
            for (int i = 0; i < runSheet.length - 1; i++) {
                runSheet[i].setFilter(Image.FILTER_NEAREST);
            }
            for (int i = 0; i < dieSheet.length - 1; i++) {
                dieSheet[i].setFilter(Image.FILTER_NEAREST);
            }
            for (int i = 0; i < fightSheet.length - 1; i++) {
                fightSheet[i].setFilter(Image.FILTER_NEAREST);
            }
        }
        enemyRunAnimation = new Animation(runSheet, 75);
        enemyFightAnimation = new Animation(fightSheet, 140);
        enemyDieAnimation = new Animation(dieSheet, 300);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 90 + increaseHp;
        speed = 0.26f + increaseSpeed;
        attackPower = 7 + increaseAttack;
        dropCash = 3;
    }

}
