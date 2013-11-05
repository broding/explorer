package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.awt.Color;
import nl.basroding.explorer.Game;
import nl.basroding.explorer.models.Planet;

/**
 *
 * @author basroding
 */
public class PlanetActor extends Actor
{
    private static ShapeRenderer shapeRenderer;
    
    private Planet planet;
    
    public PlanetActor(final Planet planet)
    {
	if(shapeRenderer == null)
	    shapeRenderer = new ShapeRenderer();
	
	this.setTouchable(Touchable.enabled);
	
	this.planet = planet;
	this.setPosition(this.planet.getPosition().x - this.planet.getRadius(), this.planet.getPosition().y - this.planet.getRadius());
	this.size(this.planet.getRadius() * 2);
	
	this.addListener(new InputListener()
	{

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		if(button == 0)
		{
		    Game.getCamera().setPosition(planet.getPosition());
		    
		    return true;
		}
		
		return false;
	    }
	    
	});
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	super.draw(batch, parentAlpha);
	
	shapeRenderer.begin(ShapeType.Filled);
        
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	shapeRenderer.setColor(1,1,1,1);
	shapeRenderer.circle(planet.getPosition().x,planet.getPosition().y, planet.getRadius());
        shapeRenderer.end();
        
        for(Planet orbiter : planet.getOrbiters())
        {
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
            shapeRenderer.setColor(0.2f,0.2f,0.2f,0.1f);
            shapeRenderer.circle(planet.getPosition().x, planet.getPosition().y, orbiter.getPosition().dst(planet.getPosition()));
            shapeRenderer.end();
        }
        
    }
}
