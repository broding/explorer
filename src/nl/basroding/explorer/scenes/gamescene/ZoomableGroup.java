package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 *
 * @author basroding
 */
public class ZoomableGroup extends Group
{
    private OrthographicCamera camera;

    public ZoomableGroup()
    {
	this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	this.setTouchable(Touchable.enabled);
	
	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
	addListener(new InputListener()
	{
	    @Override
	    public boolean scrolled(InputEvent event, float x, float y, int amount)
	    {
		return super.scrolled(event, x, y, amount); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		    
		if(keycode == Keys.PLUS)
		{
		    ZoomableGroup group = (ZoomableGroup) event.getRelatedActor();
		    group.onZoomInput(1);
		}
		else if(keycode == Keys.MINUS)
		{
		    ZoomableGroup group = (ZoomableGroup) event.getRelatedActor();
		    group.onZoomInput(1);
		}
		return false;
	    }
	});
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
	Matrix4 oldProjection = batch.getProjectionMatrix();

	System.out.println(camera.zoom);
	camera.update();
	batch.setProjectionMatrix(camera.projection);
	super.draw(batch, parentAlpha);

	batch.setProjectionMatrix(oldProjection);
    }
    
    public void onZoomInput(float zoom)
    {
	camera.zoom += zoom;
    }

    public OrthographicCamera getCamera()
    {
	return camera;
    }
}
