package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Fire Wizard class
 * @author kuba
 */
public class FireWizard extends Enemy {

    /**
     * Regular contructor of FireWizard class
     * @param initialX Position X
     * @param initialY Position Y
     */
    public FireWizard(float initialX, float initialY) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_4.png")};
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
        enemyFightAnimation = new Animation(fightSheet, 140);
        enemyDieAnimation = new Animation(dieSheet, 300);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 90;
        speed = 0.21f;
        attackPower = 10;
        dropCash = 7;
    }

    /**
     * Contructor of FireWizard with improvements
     * @param initialX Position X
     * @param initialY Position Y
     * @param increaseHp Additional hp
     * @param increaseSpeed Additional speed
     * @param increaseAttack Additional attack power
     */
    public FireWizard(float initialX, float initialY, int increaseHp, float increaseSpeed, int increaseAttack) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/fireWizard/die/dead_4.png")};
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
        enemyFightAnimation = new Animation(fightSheet, 140);
        enemyDieAnimation = new Animation(dieSheet, 300);
        enemyDieAnimation.setLooping(false); //do wsyzstkich!!!!
        healthLevel = 90 + increaseHp;
        speed = 0.21f + increaseSpeed;
        attackPower = 10 + increaseAttack;
        dropCash = 7;
    }
}
