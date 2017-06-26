package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * IceWizard class
 * @author kuba
 */
public class IceWizard extends Enemy {

    /**
     * Regular Contructor of IceWizard
     * @param initialX posiiotn X
     * @param initialY posiiotn Y
     */
    public IceWizard(float initialX, float initialY) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_4.png")};
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
        healthLevel = 75;
        speed = 0.21f;
        attackPower = 6;
        dropCash = 4;
    }
    
    /**
     * IceWizard contructor with improvements
     * @param initialX posiiotn X
     * @param initialY posiiotn Y
     * @param increaseHp Addtional Hp
     * @param increaseSpeed Additional Speed
     * @param increaseAttack Additional Attack Powe
     */
    public IceWizard(float initialX, float initialY, int increaseHp, float increaseSpeed, int increaseAttack) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_3.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/fight/attack_3.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_1.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_2.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_3.png"),
            new Image("SpritesSheets/Wizards/Assets/iceWizard/die/dead_4.png")};
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
        healthLevel = 75 + increaseHp;
        speed = 0.21f + increaseSpeed;
        attackPower = 6 + increaseAttack;
        dropCash = 4;
    }
}
