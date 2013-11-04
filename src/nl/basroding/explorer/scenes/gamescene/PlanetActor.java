package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.awt.Color;
import nl.basroding.explorer.models.Planet;

/**
 *
 * @author basroding
 */
public class PlanetActor extends Actor
{
    private static ShapeRenderer shapeRenderer;
    
    private Planet planet;
    
    public PlanetActor(Planet planet)
    {
	if(shapeRenderer == null)
	    shapeRenderer = new ShapeRenderer();
	
	this.planet = planet;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	super.draw(batch, parentAlpha);
	
	shapeRenderer.begin(ShapeType.Filled);
        
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	shapeRenderer.setColor(1,1,1,1);
	shapeRenderer.circle(0,0, planet.getRadius());
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
