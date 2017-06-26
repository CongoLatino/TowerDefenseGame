package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Represents first state of game. Loads first map sprite, enemy moving points,
 * sprites, enemies chars, towers. This class cotrols switching beetwen attack
 * rush and rounds.
 *
 * @author kuba
 */
public class FirstGameState extends BasicGameState {

    private Image firstMapSprite;
    private ArrayList<Enemy> enemies;
    private WarPath warPath;
    private Castle castle;
    private ArrayList<Building> towers;
    private SideMenu sideMenu;
    private Input userInput;
    private static float[] firstLevelPathPoints;
    private static float[] enemyMovingPoints;
    private int timePassed;
    private int enemiesCounter = 0;
    private int currentAttackRush = 1;
    private int currentRound = 1;
    private Building newTower;
    static boolean gameOver = false;
    static Soundtrack music;

    /**
     * Method inherited from Slick2D library. It inits every component of the
     * class.
     *
     * @param gc - GameContainer
     * @param sbg - StateBasedGame used mainly for switching between game states
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        firstLevelPathPoints = new float[]{0, 70, 820, 70, 820, 380, 120, 380, 120, 560, 750, 560, 750, 640, 40, 640, 40, 300, 740, 300, 740, 150, 0, 150};
        enemyMovingPoints = new float[]{0, 110, 780, 110, 780, 340, 80, 340, 80, 600, 900, 600};

        warPath = new WarPath(firstLevelPathPoints);
        try {
            firstMapSprite = new Image("/sprites/firstMap.png");
            sideMenu = new SideMenu(gc);
            castle = new Castle(750, 540);
            music = new Soundtrack();
        } catch (SlickException e) {
            System.err.println(e.getMessage());
        }
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        userInput = gc.getInput();
        timePassed = 0;

    }

    /**
     * Method inherited from Slick2D library. It inits every component of the
     * class.
     *
     * @param gc GameContainer
     * @param sbg StateBasedGame used mainly for switching between game states
     * @param delta GameDelta - it fix number of milliseconds between frames
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        enemiesSpawn(gc, sbg);
        enemies.forEach((enemy) -> {
            enemy.move(enemyMovingPoints, delta);
        });

        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).updateEnemiesOnRange(enemies);
            towers.get(i).shooting(delta);
        }

        castle.underAttack(enemies);
        sideMenu.pollSpaceToAttack(userInput);
        sideMenu.pollEscToPause(userInput, sbg);
        newTower = sideMenu.clickToBuild(userInput, towers);
        if (newTower != null) {
            towers.add(newTower);
        }
        gameOverStage(gc, sbg);

    }

    /**
     *
     * @param gc GameContainer
     * @param sbg StateBasedGame used mainly for switching between game states
     * @param g Gparhics element, used for render objects
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        //g.scale(Display.getWidth(), Display.getHeight()/800);
        g.setColor(Color.white);
        firstMapSprite.draw(0, 0, Board.getWidth(), Board.getHeight());
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).drawEnemy(g) != null) {
                enemies.remove(i);
            }
        }

        towers.forEach(
                (tower) -> {
                    tower.drawBullets();
                    tower.drawTowers();
                }
        );
        sideMenu.drawSideMenu(g);

        sideMenu.drawButtonsFollowingByCursor(g, towers, userInput);

        sideMenu.drawStrings(g);

        castle.drawCastle(g);

    }

    /**
     * This method returns a ID of current Game State
     *
     * @return ID
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     * This method represents enemies spawnn in first round. It depends on
     * timePassed between every rush, counter of enemies and sometimes increase
     * some enemies values by calling a different constructor
     */
    public void roundOne() {
        if (currentRound == 1 && SideMenu.startAttack) //RUNDA 1
        {
            timePassed++;
            int timeToSpawnNextEnemy;
            if (currentAttackRush == 4) {
                timeToSpawnNextEnemy = 700;
            } else {
                timeToSpawnNextEnemy = 1000;
            }
            if (timePassed > timeToSpawnNextEnemy) //czas pomiędzy pojedynczymi przeciwnikami
            {
                timePassed = 0;
                try {
                    switch (currentAttackRush) {
                        //pierwsza fala
                        case 1:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //druga fala
                        case 2:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //trzecia fala
                        case 3:

                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1], 12, 0, 1));
                                    break;
                                case 3:
                                case 4:
                                case 5:
                                    enemies.add(new GayWizard(enemyMovingPoints[0], enemyMovingPoints[1], 5, 0, 1));
                                    break;
                                case 6:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1], 2, 0, 3));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //czwarta fala       
                        case 4:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new SpeedKnight(enemyMovingPoints[0], enemyMovingPoints[1], 10, 0, 1));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1], 15, 0.1f, 2));
                                    break;
                                case 5:
                                case 6:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1], 5, 0, 1));
                                    break;
                                case 7:
                                case 8:
                                case 9:
                                    enemies.add(new SpeedKnight(enemyMovingPoints[0], enemyMovingPoints[1], 4, 0, 0));
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        default:
                            nextRound();
                            break;
                    }
                    enemiesCounter++;
                } catch (SlickException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * This method represents enemies spawnn in second round. It depends on
     * timePassed between every rush, counter of enemies and sometimes increase
     * some enemies values by calling a different constructor
     */
    public void roundTwo() {
        if (currentRound == 2 && SideMenu.startAttack) //RUNDA 2
        {
            int timeToSpawnNextEnemy = 700;
//            sideMenu.getMenuButtons().forEach((button) -> {
//                button.getBuilding().setCost(500);
//            });
            //sideMenu.getMenuButtons().forEach(t -> t.getBuilding().setCost(500));
            timePassed++;
            if (timePassed > timeToSpawnNextEnemy) //czas pomiędzy pojedynczymi przeciwnikami
            {
                timePassed = 0;
                try {
                    switch (currentAttackRush) {
                        //pierwsza fala
                        case 1:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //druga fala
                        case 2:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //trzecia fala
                        case 3:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //czwarta fala       
                        case 4:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new SpeedKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                case 6:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        default:
                            nextRound();
                            break;
                    }
                    enemiesCounter++;
                } catch (SlickException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * This method represents enemies spawnn in third round. It depends on
     * timePassed between every rush, counter of enemies and sometimes increase
     * some enemies values by calling a different constructor
     */
    public void roundThree() {
        if (currentRound == 3 && SideMenu.startAttack) //RUNDA 3
        {
            timePassed++;
            if (timePassed > 580) //czas pomiędzy pojedynczymi przeciwnikami
            {
                timePassed = 0;
                try {
                    switch (currentAttackRush) {
                        //pierwsza fala
                        case 1:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //druga fala
                        case 2:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //trzecia fala
                        case 3:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //czwarta fala       
                        case 4:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new SpeedKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                case 6:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        default:
                            nextRound();
                            break;
                    }
                    enemiesCounter++;
                } catch (SlickException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * This method represents enemies spawnn in fourth round. It depends on
     * timePassed between every rush, counter of enemies and sometimes increase
     * some enemies values by calling a different constructor
     */
    public void roundFour() {
        if (currentRound == 2 && SideMenu.startAttack) //RUNDA 4
        {
            timePassed++;
            if (timePassed > 400) //czas pomiędzy pojedynczymi przeciwnikami
            {
                timePassed = 0;
                try {
                    switch (currentAttackRush) {
                        //pierwsza fala
                        case 1:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //druga fala
                        case 2:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 2:
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //trzecia fala
                        case 3:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new IceWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        //czwarta fala       
                        case 4:
                            switch (enemiesCounter) {
                                case 0:
                                case 1:
                                case 2:
                                    enemies.add(new SpeedKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 3:
                                case 4:
                                    enemies.add(new DarkKnight(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                case 5:
                                case 6:
                                    enemies.add(new FireWizard(enemyMovingPoints[0], enemyMovingPoints[1]));
                                    break;
                                default:
                                    nextRush();
                                    break;
                            }
                            break;
                        default:
                            nextRound();
                            break;
                    }
                    enemiesCounter++;
                } catch (SlickException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * This method represents enemies spawnn in fifth round. It depends on
     * timePassed between every rush, counter of enemies and sometimes increase
     * some enemies values by calling a different constructor
     */
    public void finalRound(StateBasedGame sbg) {
        if (currentRound == 5 && enemies.isEmpty() && Castle.currentHealthLevel > 0) {
            sbg.enterState(4, new FadeOutTransition(Color.black, 1200), new FadeInTransition(Color.black, 1600));
        }
    }

    /**
     * It increase currentAttackRush and prolongs the time of next round. Also
     * it decrease enemiesCounter becouse after the switch in rounds-functions
     * this value is increamented
     */
    public void nextRush() {
        currentAttackRush++; // przchodzimy do następnej fali
        enemiesCounter = -1; //bo za switchem zawsze inkrementujemy
        timePassed = -10000;
    }

    /**
     * Increse currentRound, set startAttack to false and resets the round
     * pointers
     */
    public void nextRound() {
        currentRound++;
        SideMenu.startAttack = false;
        timePassed = 0;
        enemiesCounter = 0;
        currentAttackRush = 1;
    }

    /**
     * This function calls every round function if startAttack is active
     * (pressed space)
     *
     * @param gc game container
     */
    public void enemiesSpawn(GameContainer gc, StateBasedGame sbg) //3 fale ataku na jedną rundę. Rund na mapie jest 4
    {
        if (SideMenu.startAttack) {
            roundOne();
            roundTwo();
            roundThree();
            roundFour();
            finalRound(sbg);
        }
    }

    /**
     * It checks if gameOver flag is acquired and if it is - it enter the
     * GameOver state
     *
     * @param gc game container
     * @param sbg state based game
     */
    public void gameOverStage(GameContainer gc, StateBasedGame sbg) {
        if (gameOver) {
            resetAll();
            sbg.enterState(3, new FadeOutTransition(Color.black, 1200), new FadeInTransition(Color.black, 1600));
        }
    }

    /**
     * This method resets all values when gameOver is acquired
     */
    public void resetAll() {
        SideMenu.startAttack = false;
        currentRound = 1;
        currentAttackRush = 1;
        enemiesCounter = 0;
        Player.playerCash = 99;
        towers.clear();
        enemies.clear();
        castle.setHealthLevel(castle.getInitHealthLevel());
        gameOver = false;
    }

}
