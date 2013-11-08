/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.models.Rocket;

/**
 *
 * @author basroding
 */
public class RocketActor extends Actor implements Selectable
{
    private static ShapeRenderer shapeRenderer;
    
    private Rocket rocket;
    
    public RocketActor(Rocket rocket)
    {
        if(shapeRenderer == null)
            shapeRenderer = new ShapeRenderer();
        
        this.rocket = rocket;
        this.setSize(200, 200);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        
        this.setPosition(rocket.getPosition().x - 100, rocket.getPosition().y - 100);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	super.draw(batch, parentAlpha);
	
        batch.end();
        
	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	shapeRenderer.setColor(1,0,0,1);
	shapeRenderer.circle(rocket.getPosition().x,rocket.getPosition().y, 200);
        shapeRenderer.end();
        
        batch.begin();
        
    }

    @Override
    public Vector2 getPosition()
    {
        return rocket.getPosition();
    }

    @Override
    public SelectableType getSelectableType()
    {
        return SelectableType.ROCKET;
    }

    public Rocket getRocket()
    {
        return rocket;
    }

    public Planet getPlanet()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
