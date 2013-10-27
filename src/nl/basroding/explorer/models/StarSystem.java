package nl.basroding.explorer.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author basroding
 */
public class StarSystem
{
    public final static int GRID_PIXEL_RATIO = 50000; 
    
    public static Random random;
    
    private Vector2 position;
    private Vector2 size;
    private ArrayList<Planet> planets;
    
    public StarSystem()
    {
	if(random == null)
	    random = new Random();
	
	position = new Vector2();
	size = new Vector2(10, 10);
	planets = new ArrayList<Planet>();
	
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
	addPlanetAtRandom(new Planet());
    }
    
    private void addPlanetAtRandom(Planet planet)
    {
	planets.add(planet);
	planet.setPositionX(random.nextInt((int) size.x));
	planet.setPositionY(random.nextInt((int) size.y));
	
    }
    
    public void setPositionX(int x)
    {
	position.x = x;
    }
    
    public void setPositionY(int y)
    {
	position.y = y;
    }

    public Vector2 getPosition()
    {
	return position;
    }

    public void setPosition(Vector2 position)
    {
	this.position = position;
    }

    public Iterable<Planet> getPlanets()
    {
	return this.planets;
    }
}
