package towerdefensegame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Class represents dark knight class
 * @author kuba
 */
public class DarkKnight extends Enemy {

    /**
     * Regular DarkKnight contructor
     *
     * @param initialX X position of enemy
     * @param initialY Y position of enemy
     */
    public DarkKnight(float initialX, float initialY) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_4.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_4.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_5.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_6.png")};
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
        enemyRunAnimation = new Animation(runSheet, 150);
        enemyFightAnimation = new Animation(fightSheet, 200);
        enemyDieAnimation = new Animation(dieSheet, 200);
        healthLevel = 110;
        speed = 0.2f;
        attackPower = 8;
        dropCash = 5;

    }

    /**
     * Contructor of DarkKnight enemy with improvements
     *
     * @param initialX X position of enemy
     * @param initialY Y position of enemy
     * @param increaseHp Additional hp
     * @param increaseSpeed Additional speed
     * @param increaseAttack Additional attack power
     */
    public DarkKnight(float initialX, float initialY, int increaseHp, float increaseSpeed, int increaseAttack) throws SlickException {
        enemyChar = new Circle(initialX, initialY, 15);
        runSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/run/run_4.png")};
        fightSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/fight/attack_4.png")};
        dieSheet = new Image[]{new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_1.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_2.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_3.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_4.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_5.png"),
            new Image("SpritesSheets/BraveKnightPlatformerCharacter/Assets/black_knight/die/dead_6.png")};
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
        enemyRunAnimation = new Animation(runSheet, 150);
        enemyFightAnimation = new Animation(fightSheet, 200);
        enemyDieAnimation = new Animation(dieSheet, 300);
        healthLevel = 110 + increaseHp;
        speed = 0.2f + increaseSpeed;
        attackPower = 8 + increaseAttack;
        dropCash = 5;

    }

}
