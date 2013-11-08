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
public class Galaxy implements Model
{
    public static Random random;
    
    private Vector2 size;
    private ArrayList<StarSystem> systems;
    
    public Galaxy()
    {
	if(random == null)
	    random = new Random();
	
	size = new Vector2(10, 10);
	systems = new ArrayList<StarSystem>();
	
	addStarSystem(new StarSystem());
	addStarSystem(new StarSystem());
    }
    
    private void addStarSystem(StarSystem starSystem)
    {
	systems.add(starSystem);
	starSystem.setPositionX(random.nextInt((int) size.x));
	starSystem.setPositionY(random.nextInt((int) size.y));
	
    }

    public List<StarSystem> getStarSystems()
    {
	return systems;
    }

    @Override
    public void tick()
    {
        for(StarSystem system : this.systems)
            system.tick();
    }

    @Override
    public void frameTick(float deltaTime)
    {
        
    }
}
