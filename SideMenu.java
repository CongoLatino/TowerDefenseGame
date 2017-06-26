package towerdefensegame;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Side menu class with towers to buy and player's cash
 * @author kuba
 */
public class SideMenu {

    private float sideMenuHeight;
    private float sideMenuWidth;
    private Shape sideMenuShape;
    private ArrayList<MenuButton> menuButtons;
    static boolean menuMode = false;
    static MenuButton clickedButton = null;
    static Rectangle clickedTowerButtonBorder;
    static boolean startAttack = false;
    private Building builtTower = null;
    private float sideMenuX;
    private float sideMenuY;

    /**
     * Contructor of SideMenu
     * @param gc game container
     */
    public SideMenu(GameContainer gc) throws SlickException {
        sideMenuHeight = gc.getHeight();
        sideMenuWidth = gc.getWidth() * 0.1f;
        sideMenuX=gc.getWidth() - gc.getWidth() * 0.1f;
        sideMenuY=0;
        sideMenuShape = new Rectangle(sideMenuX, sideMenuY, sideMenuWidth, sideMenuHeight);
        menuButtons = new ArrayList<>();
        menuButtons.add(new LightTowerButton(gc));
        menuButtons.add(new RussianTowerButton(gc));
        menuButtons.add(new DarkTowerButton(gc));
        menuButtons.add(new SmileTowerButton(gc));
        clickedTowerButtonBorder = new Rectangle(0, 0, 54, 54);
    }

    /**
     * Draws a side menu
     * @param g Graphics class from Slick2D library
     */
    public void drawSideMenu(Graphics g) {

        //////rysowanie menu//////
        g.setColor(Color.gray);
        try {
            g.fillRect(sideMenuX, sideMenuY, sideMenuWidth, sideMenuHeight, new Image("/sprites/sideMenu2.jpg"),0, 0);
            g.setColor(Color.yellow);
            g.fillRect(sideMenuX, sideMenuY, 2, sideMenuHeight);
        } catch (SlickException e) {
            System.err.println(e.getMessage());
        }
        
        //////rysowanie guzików w menu//////
        for (int i = 0; i < menuButtons.size(); i++) {
            g.drawImage(menuButtons.get(i).getButtonSprite(), menuButtons.get(i).getButtonRange().getCenterX() - 28.9f,
                    menuButtons.get(i).getButtonRange().getCenterY() - 31.8f);
        }
    }

    /**
     * draw Strings on board
     * @param g Graphics class from Slick2D library
     */
    public void drawStrings(Graphics g) {
        g.setColor(Color.black);
        if (!SideMenu.startAttack) {
            g.drawString("Press SPACE to start attack", 340, 740);
        }
        g.setColor(Color.white);
        g.drawString(Player.getCash() + "$$", 955, 770);
        for (int i = 0; i < menuButtons.size(); i++) {
            g.drawString("" + menuButtons.get(i).getBuilding().getCost(), menuButtons.get(i).getButtonRange().getCenterX() - 6.5f,
                    menuButtons.get(i).getButtonRange().getCenterY() + 32);
        }
        g.drawString("Castle HP\n   " + Castle.currentHealthLevel, 935, 700);
        g.setColor(Color.gray);
        g.drawString("_____________", sideMenuX, 740);
        g.drawString("_____________", sideMenuX, 660);
        
    }

    /**
     * Draws a Image of button when user click some tower button in side menu. This Image follows by cursor until player didn't cancel or build a tower
     * @param g Graphics class from Slick2D library
     * @param towers Array of towers from game
     * @param userInput user input
     */
    public void drawButtonsFollowingByCursor(Graphics g, ArrayList<Building> towers, Input userInput) { //rysowanie wieże podążającej za kurserem po pierwszym kliknięciu w guzik (w menuMode)
        if (menuMode) {
            clickedButton.getButtonSprite().drawCentered(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY());
            clickedButton.getTowerRange().setLocation(userInput.getAbsoluteMouseX() - clickedButton.getTowerRange().getWidth() / 2,
                    userInput.getAbsoluteMouseY() - clickedButton.getTowerRange().getHeight() / 2);
            g.draw(clickedButton.getTowerRange());
            g.draw(clickedButton.getButtonRange());
            for (int i = 0; i < towers.size(); i++) { //rysowanie zasięgu zbudowanych wierz w menuMode
                g.draw(towers.get(i).getBuildingRange());
            }
        }
    }

    /**
     * This method is responsible for bulding a tower, and supports clicking
     * @param userInput user input
     * @param towers towers from game (check if is collision when bulding)
     * @return Builded tower or null if is not succeed
     */
    public Building clickToBuild(Input userInput, ArrayList<Building> towers) {
        if (menuMode) {
            clickedTowerButtonBorder.setCenterX(userInput.getAbsoluteMouseX()); //kwadrat określający granice budowania wieży (dla każdej wieży taki sam)
            clickedTowerButtonBorder.setCenterY(userInput.getAbsoluteMouseY());
        }
        for (int i = 0; i < menuButtons.size(); i++) {
            if (isButtonClickedFirstTime(userInput, i)) { //pierwsze kliknięcie na przycisk z wieżą
                clickedButton = menuButtons.get(i);
                menuMode = true;
            } else if (isButtonClickedCanceled(userInput)) { //anulowanie wybierania (prawy przycisk myszy)
                menuMode = false;
            } else if (isButtonClickedSecondTime(userInput) && isTowerPossibleToBuild(towers)) { //drugie kliknięcie na przycisk z wieżą ->budowa
                menuMode = false;
                try {
                    Player.reduceCash(clickedButton.getBuilding().getCost());
                    return clickedButton.getBuilding(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY());
                } catch (SlickException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * Check if the left button of mouse is clicked first time
     * @param userInput input
     * @param i index of button that was clicked (from menuButtons array)
     * @return true or false
     */
    public boolean isButtonClickedFirstTime(Input userInput, int i) {
        return menuButtons.get(i).getButtonRange().contains(userInput.getAbsoluteMouseX(), userInput.getAbsoluteMouseY())
                && userInput.isMousePressed(0) && menuButtons.get(i).getBuilding().getCost() <= Player.getCash() && !menuMode;
    }

    /**
     * Check if the left button of mouse is clicked second time
     * @param userInput user input
     * @return true or false
     */
    public boolean isButtonClickedSecondTime(Input userInput) {
        return menuMode && userInput.isMousePressed(0);
    }

    /**
     * Check if right button of mouse was clicked to canceled choosing
     * @param userInput user input
     * @return true or false
     */
    public boolean isButtonClickedCanceled(Input userInput) {
        return menuMode && userInput.isMousePressed(1);
    }

    /**
     * Waits for space to run the attack
     * @param userInput user input
     */
    public void pollSpaceToAttack(Input userInput) {
        if (!menuMode && !startAttack && userInput.isKeyPressed(Input.KEY_SPACE)) {
            startAttack = true; //wartość false włączana w f. GameState.enemiesSpawn - gdy skończą się wszystkie fale
        }
    }
    
    public void pollEscToPause(Input userInput, StateBasedGame sbg){
        if(!menuMode && userInput.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(0,new FadeOutTransition(Color.black, 1200), new FadeInTransition(Color.black, 1200));
        }
    }

    /**
     * Check if tower player wante to build isn't colliding with other towers, war path, side menu or castle
     * @param towers array of towers in game (with might be colliding with new one)
     * @return true or false
     */
    public boolean isTowerPossibleToBuild(ArrayList<Building> towers) {

        boolean isAnyTowerColliding = false;
        for (Building tower : towers) {
            if (tower.getBuildingShape().contains(clickedTowerButtonBorder) || tower.getBuildingShape().intersects(clickedTowerButtonBorder)) {
                isAnyTowerColliding = true;
            }
        }
        return !isAnyTowerColliding && !(WarPath.getWarPath().intersects(clickedTowerButtonBorder) || WarPath.getWarPath().contains(clickedTowerButtonBorder))
                && !(sideMenuShape.intersects(clickedTowerButtonBorder) || sideMenuShape.contains(clickedTowerButtonBorder))
        && !(Castle.getCastleBuildingRange().contains(clickedTowerButtonBorder) || Castle.getCastleBuildingRange().intersects(clickedTowerButtonBorder));
    }

    /**
     *
     * @return succeed built tower
     */
    public Building getBuiltTower() {
        return builtTower;
    }

    /**
     *
     * @return side menu height
     */
    public float getHeight() {
        return sideMenuHeight;
    }

    /**
     *
     * @return side menu width
     */
    public float getWidth() {
        return sideMenuWidth;
    }

    /**
     *
     * @return side menu shape
     */
    public Shape getSideMenuShape() {
        return sideMenuShape;
    }

    /**
     *
     * @return light tower button
     */
    public MenuButton getLightTowerButton() {
        return menuButtons.get(0);
    }

    /**
     *
     * @return russian tower button
     */
    public MenuButton getRussianTowerButton() {
        return menuButtons.get(1);
    }

    /**
     *
     * @return dark tower button
     */
    public MenuButton getDarkTowerButton() {
        return menuButtons.get(2);
    }

    /**
     *
     * @return smile tower button
     */
    public MenuButton getSmileTowerButton() {
        return menuButtons.get(3);
    }

    /**
     *
     * @return array of menu buttons
     */
    public ArrayList<MenuButton> getMenuButtons() {
        return menuButtons;
    }

}
