package nl.basroding.explorer.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author basroding
 */
public class SpaceCamera extends OrthographicCamera
{
    private Vector2 position;
    private Vector2 velocity;
    
    public SpaceCamera()
    {
	super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
	this.position = Vector2.Zero;
	this.velocity = Vector2.Zero;
    }
    
    @Override
    public void update()
    {
	super.update();
    }
    
    public void scrollToPosition(Vector2 position)
    {
	
    }

    public Vector2 getPosition()
    {
	return position;
    }

    public void setPosition(Vector2 position)
    {
	this.translate(-this.position.x, -this.position.y);
	
	this.position = position;
	
	this.translate(this.position.x, this.position.y);
    }

    public Vector2 getVelocity()
    {
	return velocity;
    }

    public void setVelocity(Vector2 velocity)
    {
	this.velocity = velocity;
    }
}
