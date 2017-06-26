package towerdefensegame;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Class responsible for music in game
 *
 * @author kuba
 */
public class Soundtrack {

    static Music music;

    static {
        try {
            music = new Music("/music/output.ogg");
        } catch (SlickException e) {
            System.err.println("Music error!\n" + e.getMessage());
        }
    }

}
