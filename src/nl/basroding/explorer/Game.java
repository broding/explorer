package nl.basroding.explorer;

import nl.basroding.explorer.display.Scene;

/**
 *
 * @author basroding
 */
public class Game
{

    public static Core core;

    public static void switchScene(Scene scene)
    {
	core.switchScene(scene);
    }
}
