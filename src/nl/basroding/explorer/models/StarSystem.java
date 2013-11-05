package nl.basroding.explorer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author basroding
 */
public class StarSystem
{   
    public final static int MINIMAL_PLANET_DISTANCE = Sun.SUN_MIN_RADIUS * 3;
    public static Random random;
    
    private Vector2 position;
    private int size;
    private ArrayList<Planet> planets;
    private Sun sun;
    
    public StarSystem()
    {
	if(random == null)
	    random = new Random();
	
	position = new Vector2();
	size = 10000;
	planets = new ArrayList<Planet>();
        
        generate();
    }
    
    private void generate()
    {
        planets.clear();
        
        sun = addSun();
	
	this.addPlanetInOrbit(sun);
	this.addPlanetInOrbit(sun);
	this.addPlanetInOrbit(sun);
	this.addPlanetInOrbit(sun);
    }
    
    private Sun addSun()
    {
        Sun aSun = new Sun();
        aSun.setRadius(100);
        aSun.setPosition(new Vector2(0,0));
        planets.add(aSun);
	
        return aSun;
    }
    
    private Planet addPlanetInOrbit(Planet parentPlanet)
    {
        int range = random.nextInt(size);
	
	while(parentPlanet.isPlanetInRange(range, MINIMAL_PLANET_DISTANCE) || parentPlanet.getRadius() * 2 > range)
	{
	    range = random.nextInt(size);
	}
        
        Planet planet = new Planet();
        planet.setRadius(random.nextInt(Planet.PLANET_MAX_RADIUS));
        
        Vector2 randomVector = new Vector2((random.nextFloat() * 2 - 1), (random.nextFloat() * 2 - 1));
        randomVector = randomVector.nor().scl(range);
        planet.setPosition(parentPlanet.getPosition().cpy());
	planet.getPosition().add(randomVector);
        
        parentPlanet.addOrbiter(planet);
        planets.add(planet);
        
        return planet;
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
