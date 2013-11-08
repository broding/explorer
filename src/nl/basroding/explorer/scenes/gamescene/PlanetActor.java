package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import nl.basroding.explorer.Game;
import nl.basroding.explorer.models.Planet;

/**
 *
 * @author basroding
 */
public class PlanetActor extends Actor implements Selectable
{
    private static final int SUB_SIZE = 13;
    private static final int ORBIT_LINE_THICKNESS = 1;
    private static ShapeRenderer shapeRenderer;
    
    private Planet planet;
    
    private float subStartFade;
    private float subEndFace;
    private int subSize;
    
    public PlanetActor(final Planet planet)
    {
	if(shapeRenderer == null)
	    shapeRenderer = new ShapeRenderer();
	
	this.planet = planet;
	this.setPosition(this.planet.getPosition().x - this.planet.getRadius(), this.planet.getPosition().y - this.planet.getRadius());
	this.size(this.planet.getRadius() * 2);
        subStartFade = planet.getRadius() / 3;
        subEndFace = planet.getRadius() / 2;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        
        // if planet is too small, then increase size for bigger clickable area
        if(Game.getCamera().zoom > subStartFade)
            this.setSize(SUB_SIZE * Game.getCamera().zoom, SUB_SIZE * Game.getCamera().zoom);
        else
            this.setSize(planet.getRadius() * 2, planet.getRadius() * 2);
        
        this.setPosition(this.planet.getPosition().x - this.getWidth() / 2, this.planet.getPosition().y - this.getWidth() / 2);
    }
    
    

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	super.draw(batch, parentAlpha);
	
        batch.end();
        
	shapeRenderer.begin(ShapeType.Filled);
        
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	shapeRenderer.setColor(1,1,1,1);
	shapeRenderer.circle(planet.getPosition().x,planet.getPosition().y, planet.getRadius());
        shapeRenderer.end();
        
        // draw orbiter lines
        for(Planet orbiter : planet.getOrbiters())
        {
            Gdx.gl.glEnable(GL10.GL_BLEND);
            Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
            shapeRenderer.setColor(1f,1f,1f,Math.min(0.2f, lerp(0.03f, 0.2f, (Game.getCamera().zoom - 1) / 5)));
            for(int i = 0; i < ORBIT_LINE_THICKNESS; i++)
                shapeRenderer.circle(planet.getPosition().x, planet.getPosition().y, orbiter.getPosition().dst(planet.getPosition()) - i * 2);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL10.GL_BLEND);
        }
        
        // if planet is too small, then draw a sub
        if(Game.getCamera().zoom > this.subStartFade)
        {
            Gdx.gl.glEnable(GL10.GL_BLEND);
            Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
            shapeRenderer.setColor(1,1,1,Math.min(0.5f, lerp(0f, 0.5f, (Game.getCamera().zoom - subStartFade) / subEndFace)));
            shapeRenderer.circle(planet.getPosition().x,planet.getPosition().y, SUB_SIZE * Game.getCamera().zoom);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL10.GL_BLEND);
        }
        
        batch.begin();
    }
    
    private float lerp(float start, float end, float percent)
    {
        return (start + percent*(end - start));
    }

    @Override
    public Vector2 getPosition()
    {
        return planet.getPosition();
    }

    @Override
    public SelectableType getSelectableType()
    {
        return SelectableType.PLANET;
    }

    public Planet getPlanet()
    {
        return planet;
    }
}
