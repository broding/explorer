package nl.basroding.explorer.scenes;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.util.ArrayList;
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
    
    private ArrayList<ZoomableGroup> maps;
    
    public GameScene()
    {
	setTouchable(Touchable.enabled);
	maps = new ArrayList<ZoomableGroup>();
	galaxy = new Galaxy();
	addMap(new StarSystemMap(galaxy.getStarSystems().get(0)));
	
	addListener(new InputListener()
	{
	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		for(ZoomableGroup map : maps)
		    map.onZoomInput(-0.01f);
		
		return false;
	    }

	    @Override
	    public boolean scrolled(InputEvent event, float x, float y, int amount)
	    {
		for(ZoomableGroup map : maps)
		    map.onZoomInput(amount/4);
		
		return super.scrolled(event, x, y, amount); //To change body of generated methods, choose Tools | Templates.
	    }
	    
	    
	    
	});
    }
    
    private void addMap(ZoomableGroup map)
    {
	this.maps.add(map);
	addActor(map);
    }
    
    public void onZoomInput(float zoom)
    {
	for(ZoomableGroup map : maps)
	    map.onZoomInput(zoom);
    }
}
