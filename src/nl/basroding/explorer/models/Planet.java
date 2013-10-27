package nl.basroding.explorer.models;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author basroding
 */
public class Planet
{
    private Vector2 position;
    
    public Planet()
    {
	position = new Vector2();
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
}
