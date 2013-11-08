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
    public static final int SCROLL_EDGE = 20;
    public static final float SCROLL_SPEED = 3;
    
    public float maxZoom;
    public float minZoom;
    
    private float scrollSpeed;
    private Vector2 targetPosition;
    private Vector2 spacePosition;
    private boolean targeting;
    
    private float totalTime;
    private float currentTime;
    
    public SpaceCamera()
    {
        this.maxZoom = Float.MAX_VALUE;
        this.minZoom = 0;
        
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
            this.setPosition(spacePosition.cpy().add(mouseDirection.scl(SCROLL_SPEED * zoom)));
        }
    }
    
    private boolean isMouseOutOfBounds()
    {
        return Gdx.input.getX() > Gdx.graphics.getWidth() - SCROLL_EDGE ||
                Gdx.input.getX() < 0 + SCROLL_EDGE ||
                Gdx.input.getY() < 0 + SCROLL_EDGE ||
                Gdx.input.getY() > Gdx.graphics.getHeight() - SCROLL_EDGE;
    }
    
    /**
     * Calculates a unit vector according to which direction the mouse is (north, south, west, east)
     * @return A unit vector
     */
    private Vector2 getMouseDirection()
    {
        Vector2 vector = Vector2.Zero.cpy();
        
        if(Gdx.input.getX() > Gdx.graphics.getWidth() - SCROLL_EDGE)
            vector.x = 1;
        else if(Gdx.input.getX() < 0 + SCROLL_EDGE)
            vector.x = -1;
        
        if(Gdx.input.getY() > Gdx.graphics.getHeight() - SCROLL_EDGE)
            vector.y = -1;
        else if(Gdx.input.getY() < 0 + SCROLL_EDGE)
            vector.y = 1;
        
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
    
    public void setZoom(float zoom)
    {
        this.zoom = zoom;
        this.zoom = Math.min(this.zoom, this.maxZoom);
        this.zoom = Math.max(this.zoom, this.minZoom);
    }
}
