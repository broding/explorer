package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.models.StarSystem;

/**
 *
 * @author basroding
 */
public class StarSystemMap extends ZoomableGroup
{
    private StarSystem system;
    private OrthographicCamera camera;
    
    public StarSystemMap(StarSystem system)
    {
	super();
	
	camera = new OrthographicCamera(500, 500);
	this.system = system;
	
	for(Planet planet : system.getPlanets())
	{
	    this.addActor(new PlanetActor(planet));
	}
    }

    @Override
    public void act(float delta)
    {
	super.act(delta);
    }
    
    
}
