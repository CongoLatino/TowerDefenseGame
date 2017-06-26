package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Class represents gay wizard
 * @author kuba
 */
public class GayWizard extends Enemy {

    /**
     * Regular GayWizard contructor
     * @param initialX position X
     * @param initialY position Y
     */
    public GayWizard(float initialX, float initialY) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_4.png")};
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
        enemyRunAnimation = new Animation(runSheet, 100);
        enemyFightAnimation = new Animation(fightSheet, 400);
        enemyDieAnimation = new Animation(dieSheet, 200);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 70;
        speed = 0.219f;
        attackPower = 4;
        dropCash = 2;
    }

    /**
     * GayWizard Contructor with improvements
     * @param initialX posiotn X
     * @param initialY position Y
     * @param increaseHp Additional Hp
     * @param increaseSpeed Additional Speed
     * @param increaseAttack Additional Attack Power
     */
    public GayWizard(float initialX, float initialY, int increaseHp, float increaseSpeed, int increaseAttack) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/gayWizard/die/dead_4.png")};
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
        enemyRunAnimation = new Animation(runSheet, 100);
        enemyFightAnimation = new Animation(fightSheet, 400);
        enemyDieAnimation = new Animation(dieSheet, 300);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 70 + increaseHp;
        speed = 0.219f + increaseSpeed;
        attackPower = 4 + increaseAttack;
        dropCash = 2;
    }
}
