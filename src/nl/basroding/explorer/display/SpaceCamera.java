package nl.basroding.explorer.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author basroding
 */
public final class SpaceCamera extends OrthographicCamera
{
    private float scrollSpeed;
    private Vector2 targetPosition;
    private Vector2 spacePosition;
    private boolean targeting;
    
    private float totalTime;
    private float currentTime;
    
    public SpaceCamera()
    {
        this.viewportWidth = Gdx.graphics.getWidth();
        this.viewportHeight = Gdx.graphics.getHeight();
        this.near = 0;
        
        this.currentTime = 0;
	this.spacePosition = Vector2.Zero;
        this.targetPosition = Vector2.Zero;
	this.targeting = false;
                
        this.update();
    }
    
    @Override
    public void update()
    {
	super.update();
        
        if(targeting)
        {
            if(!targetPosition.epsilonEquals(spacePosition, 0.3f))
            {
                // lerp position to actual position
                currentTime += Gdx.graphics.getDeltaTime();
                setPosition(spacePosition.cpy().lerp(targetPosition, currentTime / totalTime));
            }
            else
            {
                targeting = false;
            }
        }
        
        if(this.isMouseOutOfBounds() && !targeting)
        {
            Vector2 mouseDirection = this.getMouseDirection();
            this.setPosition(spacePosition.cpy().add(mouseDirection));
        }
    }
    
    private boolean isMouseOutOfBounds()
    {
        return Gdx.input.getX() > Gdx.graphics.getWidth() ||
                Gdx.input.getX() < 0 ||
                Gdx.input.getY() < 0 ||
                Gdx.input.getY() > Gdx.graphics.getHeight();
    }
    
    /**
     * Calculates a unit vector according to which direction the mouse is (north, south, west, east)
     * @return A unit vector
     */
    private Vector2 getMouseDirection()
    {
        Vector2 vector = Vector2.Zero;
        int distanceFromCentre = 0;
        
        // setup west
        distanceFromCentre = Gdx.graphics.getWidth() / 2 - Gdx.input.getX();
        vector.x = 1;
        
        // check east
        if(distanceFromCentre < Gdx.input.getX() - Gdx.graphics.getWidth() / 2)
        {
            distanceFromCentre = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
            vector = Vector2.Zero;
            vector.x = -1;
        }
        
        if(distanceFromCentre < Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
        {
            distanceFromCentre = Gdx.graphics.getHeight() / 2 - Gdx.input.getY();
            vector = Vector2.Zero;
            vector.y = 1;
        }
        
        if(distanceFromCentre < Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
        {
            distanceFromCentre = Gdx.input.getY() - Gdx.graphics.getHeight() / 2;
            vector = Vector2.Zero;
            vector.y = -1;
        }
        
        return vector;
    }
    
    public void setTarget(Vector2 target)
    {
        this.currentTime = 0;
        this.totalTime = 2;
        this.targetPosition = target;
        this.targeting = true;
    }

    public Vector2 getPosition()
    {
	return spacePosition;
    }

    public void setPosition(Vector2 position)
    {
        spacePosition = position;
	this.position.set(position.x, position.y, 0);
    }
}
