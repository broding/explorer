package nl.basroding.explorer.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static nl.basroding.explorer.models.StarSystem.random;

/**
 *
 * @author basroding
 */
public class Galaxy
{
    public static Random random;
    
    private Vector2 size;
    private ArrayList<StarSystem> planets;
    
    public Galaxy()
    {
	if(random == null)
	    random = new Random();
	
	size = new Vector2(10, 10);
	planets = new ArrayList<StarSystem>();
	
	addStarSystem(new StarSystem());
	addStarSystem(new StarSystem());
    }
    
    private void addStarSystem(StarSystem starSystem)
    {
	planets.add(starSystem);
	starSystem.setPositionX(random.nextInt((int) size.x));
	starSystem.setPositionY(random.nextInt((int) size.y));
	
    }

    public List<StarSystem> getStarSystems()
    {
	return planets;
    }
}
