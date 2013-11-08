package nl.basroding.explorer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import nl.basroding.explorer.display.Scene;
import nl.basroding.explorer.display.SpaceCamera;

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
    
    public static SpaceCamera getCamera()
    {
	return core.getCamera();
    }
    
    public static void setCamera(SpaceCamera camera)
    {
        core.setCamera(camera);
    }
}
