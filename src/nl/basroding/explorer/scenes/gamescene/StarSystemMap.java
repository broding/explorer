package nl.basroding.explorer.scenes.gamescene;

import nl.basroding.explorer.Game;
import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.models.StarSystem;

/**
 *
 * @author basroding
 */
public class StarSystemMap extends ZoomableGroup
{
    private StarSystem system;
    
    public StarSystemMap(StarSystem system)
    {
	super();
        
	this.system = system;
        Game.getCamera().maxZoom = system.getSize() / 170;
	
	for(Planet planet : system.getPlanets())
	    this.addActor(new PlanetActor(planet));
        
    }

    @Override
    public void act(float delta)
    {
	super.act(delta);
    }
}
