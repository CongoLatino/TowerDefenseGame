package towerdefensegame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 * Class represents points and shape of war path on which enemies are moving
 * @author kuba
 */
public class WarPath {

    private float[] pathPoints;
    private static Shape warPath = null;

    /**
     * Constructor of war path
     * @param newPoints newPoints
     */
    public WarPath(float[] newPoints) {
        pathPoints = newPoints;
        warPath = new Polygon(pathPoints);
    }

    /**
     *
     * @return shape of warpatch
     */
    public static Shape getWarPath() {
        return warPath;
    }

    /**
     * Draws a draw path
     * @param g Graphics class from Slick2D library
     */
    public void drawPath(Graphics g) {
        g.draw(warPath);
    }

}
