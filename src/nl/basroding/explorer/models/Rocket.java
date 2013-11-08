package nl.basroding.explorer.models;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author basroding
 */
public class Rocket implements Model
{
    private RocketState state;
    
    private Planet planet;
    private StarSystem starSystem;
    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    
    public Rocket()
    {
        position = new Vector2((float)Math.random() * 10000, (float)Math.random() * 10000);
        
        state = RocketState.STATIONARY;
        speed = 12;
    }

    public StarSystem getStarSystem()
    {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem)
    {
        this.starSystem = starSystem;
    }
   
    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }
    
    public void setCourse(Planet planet)
    {
        state = RocketState.TRAVELING;
        
        velocity = planet.getPosition().cpy().add(position.cpy()).nor().scl(-speed);
        
        this.planet = planet;
    }
    
    public void dock(Planet planet)
    {
        state = RocketState.DOCKING;
        
        this.planet = planet;
    }
    
    public void orbit(Planet planet)
    {
        state = RocketState.ORBITING;
        
        this.planet = planet;
    }
    
    public boolean isOrbiting()
    {
        return state == RocketState.ORBITING;
    }
    
    public boolean isDocked()
    {
        return state == RocketState.DOCKING;
    }
    
    public boolean isTraveling()
    {
        return state == RocketState.TRAVELING;
    }

    @Override
    public void tick()
    {
        if(state == RocketState.TRAVELING)
        {
            
        }
    }

    @Override
    public void frameTick(float deltaTime)
    {
        if(state == RocketState.TRAVELING)
        {
            Vector2 distanceVector = planet.getPosition().cpy().add(position.cpy().scl(speed));
            
            if(distanceVector.len() < 10)
            {
                
            }
            else
            {
                position.add(velocity.cpy().scl(deltaTime));
            }
        }
    }

    public float getSpeed()
    {
        return speed;
    }
}
