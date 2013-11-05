package nl.basroding.explorer.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.util.ArrayList;
import nl.basroding.explorer.Game;
import nl.basroding.explorer.display.Scene;
import nl.basroding.explorer.models.Galaxy;
import nl.basroding.explorer.scenes.gamescene.StarSystemMap;
import nl.basroding.explorer.scenes.gamescene.ZoomableGroup;

/**
 *
 * @author basroding
 */
public class GameScene extends Scene 
{
    private Galaxy galaxy;	
    private ZoomableGroup currentMap;
    
    private float dragTime;
    private boolean dragging;
    private Vector2 dragStart;
    
    
    public GameScene()
    {
	setTouchable(Touchable.enabled);
	
	dragging = false;
	dragStart = Vector2.Zero;
	
	galaxy = new Galaxy();
	currentMap = new StarSystemMap(galaxy.getStarSystems().get(0));
	
	addActor(currentMap);
	
	addListener(new InputListener()
	{
	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		if(keycode == Input.Keys.PLUS)
		{
		    currentMap.onZoomInput(-16);
		}
		
		if(keycode == Input.Keys.MINUS)
		{
		    currentMap.onZoomInput(16);
		}
		
		return false;
	    }

	    @Override
	    public boolean scrolled(InputEvent event, float x, float y, int amount)
	    {
		currentMap.onZoomInput(amount*16);
		
		return super.scrolled(event, x, y, amount);
	    }

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		if(button == 1)
		{
		    dragging = true;
		    dragStart.set(x, y);
		    return true;
		}
		
		return false;
	    }

	    @Override
	    public boolean mouseMoved(InputEvent event, float x, float y)
	    {
		if(dragging)
		{
		    Game.getCamera().setPosition(new Vector2(x, y));
		    Game.getCamera().update();
		    return true;
		}
		
		return super.mouseMoved(event, x, y); 
	    }
	    
	    

	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
	    {
		dragging = false;
		
		super.touchUp(event, x, y, pointer, button);
	    }
	    
	    
	});
    }
}
