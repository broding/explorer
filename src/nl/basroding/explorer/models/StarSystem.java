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
    public final static int MINIMAL_PLANET_DISTANCE = Sun.SUN_MIN_RADIUS * 1;
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
	size = 2000;
	planets = new ArrayList<Planet>();
        
        generate();
    }
    
    private void generate()
    {
        planets.clear();
        
        sun = addSun();
    }
    
    private Sun addSun()
    {
        Sun aSun = new Sun();
        aSun.setRadius(200);
        aSun.setPosition(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        planets.add(aSun);
        
        return aSun;
    }
    
    private Planet addPlanetInOrbit(Planet parentPlanet)
    {
        int range = random.nextInt(size);
        
        Planet planet = new Planet();
        planet.setRadius(random.nextInt(Planet.PLANET_MAX_RADIUS));
        
        Vector2 randomVector = new Vector2((random.nextFloat() * 2 - 1), (random.nextFloat() * 2 - 1));
        randomVector = randomVector.nor().scl(range);
        planet.setPosition(parentPlanet.getPosition().add(randomVector));
        
        System.out.print("range: " + range);
        System.out.print(" - dis: " + planet.getPosition().dst(parentPlanet.getPosition()) + "\n");
        
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
