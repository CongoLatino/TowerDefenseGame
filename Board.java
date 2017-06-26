package towerdefensegame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Class represents board 
 * @author kuba
 */
public class Board {

    private static AppGameContainer app;
    private static final int height = /*480;*/ 800;
    private static final int width = /*640;*/ 1024;
    private static boolean fps = false;
    private static boolean vSync = false;
    private static boolean blur = false;

    /**
     * Constructor of board
     */
    public Board() throws SlickException {
        app = new AppGameContainer(new SetupClass("Tower Defense"));
        app.setDisplayMode(width, height, false);
        app.setShowFPS(fps);
        app.setAlwaysRender(true);
        app.setVSync(vSync);
        app.start();

    }

    /**
     *
     * @return height of board
     */
    public static int getHeight() {
        return height;
    }

    /**
     *
     * @return width of board
     */
    public static int getWidth() {
        return width;
    }

    /**
     *
     * @return true if fps i shown, false if it isn't
     */
    public static boolean getFps() {
        return fps;
    }

    /**
     * Sets if the fps are supposed to be shown
     * @param show flag represents true or false
     */
    public static void setFps(boolean show) {
        Board.fps = show;
        app.setShowFPS(fps);
    }

    /**
     *
     * @return true if vsync is on or false if it isn't
     */
    public static boolean getVSync() {
        return vSync;
    }

    /**
     * Sets if vSync should be on or off
     * @param vSync on/off
     */
    public static void setVSync(boolean vSync) {
        Board.vSync = vSync;
        app.setVSync(vSync);
    }

    /**
     * Returns if blur is turn on or off
     * @return true or false
     */
    public static boolean getBlur() {
        return blur;
    }

    /**
     * Sets if blur should be on or off
     * @param blur on/off
     */
    public static void setBlur(boolean blur) {
        Board.blur = blur;
    }

}
