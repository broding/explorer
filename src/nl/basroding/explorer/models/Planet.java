package nl.basroding.explorer.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *
 * @author basroding
 */
public class Planet
{
    public final static int PLANET_MAX_RADIUS = 40;
    public final static int PLANET_MIN_RADIUS = 1;
    
    private Vector2 position;
    private float radius;
    private final ArrayList<Planet> orbiters;
    
    protected int minRadius;
    protected int maxRadius;
    
    public Planet()
    {
        orbiters = new ArrayList<Planet>(50);
        minRadius = PLANET_MIN_RADIUS;
        maxRadius = PLANET_MAX_RADIUS;
        
	position = new Vector2();
        radius = 1;
    }
    
    public void addOrbiter(Planet planet)
    {
        orbiters.add(planet);
    }
    
    public void removeOrbiter(Planet planet)
    {
        orbiters.remove(planet);
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

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        radius = Math.min(radius, this.maxRadius);
        radius = Math.max(radius, this.minRadius);
        
        this.radius = radius;
    }
    
    public final ArrayList<Planet> getOrbiters()
    {
        return orbiters;
    }
    
    public boolean isPlanetInRange(int range, int minDistance)
    {
        for(Planet planet : this.orbiters)
        {
            float rangee = planet.getPosition().dst(getPosition());
            
            if (rangee - minDistance <= range && range < rangee + minDistance)
                return true;
        }
        
        return false;
    }
}
