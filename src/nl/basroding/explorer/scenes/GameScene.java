package nl.basroding.explorer.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.util.ArrayList;
import java.util.List;
import nl.basroding.explorer.Game;
import nl.basroding.explorer.display.Scene;
import nl.basroding.explorer.models.Galaxy;
import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.models.Player;
import nl.basroding.explorer.models.Rocket;
import nl.basroding.explorer.scenes.gamescene.PlanetActor;
import nl.basroding.explorer.scenes.gamescene.RocketActor;
import nl.basroding.explorer.scenes.gamescene.Selectable;
import nl.basroding.explorer.scenes.gamescene.StarSystemMap;
import nl.basroding.explorer.scenes.gamescene.ZoomableGroup;
import nl.basroding.explorer.scenes.gamescene.ui.SpaceOverlay;
import nl.basroding.explorer.scenes.gamescene.ui.UIEvent;
import nl.basroding.explorer.scenes.gamescene.ui.UIEventType;
import nl.basroding.explorer.scenes.gamescene.ui.UIOverlay;

/**
 *
 * @author basroding
 */
public class GameScene extends Scene 
{
    private UIOverlay ui;
    
    private Galaxy galaxy;	
    private List<Player> players;
    private ZoomableGroup currentMap;
    
    private int gameSpeed;
    
    private final int tickSpeed;
    private float tickSpeedTimer;
    
    private Selectable selected;
    private Rocket selectedRocket;
    
    private float dragTime;
    private boolean dragging;
    private Vector2 dragStart;
    
    public GameScene(UIOverlay ui)
    {
        this.ui = ui;
        
	setTouchable(Touchable.enabled);
	
	galaxy = new Galaxy();
	currentMap = new StarSystemMap(galaxy.getStarSystems().get(0));
        players = new ArrayList<Player>();
        players.add(new Player(galaxy.getStarSystems().get(0)));
        
        gameSpeed = 1;
        tickSpeed = 1;
        tickSpeedTimer = 0;
        
	dragging = false;
	dragStart = Vector2.Zero;
	
	addActor(currentMap);
        
        for(Player player : players)
            for(Rocket rocket : player.getRockets())
                addActor(new RocketActor(rocket));
        
        addActor(ui);
	
	addListener(new InputListener()
	{

            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button)
            {   
                Actor actor = hit(x, y, false);
                
                if(actor != null && actor instanceof Selectable)
                {
                    Selectable selectable = (Selectable) actor;
                    GameScene.this.selected = selectable;
                    Game.getCamera().setTarget(selectable.getPosition());
                    
                    switch(selectable.getSelectableType())
                    {   
                        case ROCKET:
                            RocketActor rocketActor = (RocketActor)selectable;
                            selectedRocket = rocketActor.getRocket();
                            UIEvent rocketEvent = new UIEvent(UIEventType.SELECT_ROCKET);
                            rocketEvent.rocket = selectedRocket;
                            GameScene.this.ui.receiveUIEvent(rocketEvent);
                            break;
                            
                        case PLANET:
                            PlanetActor planetActor = (PlanetActor)selectable;
                            UIEvent planetEvent = new UIEvent(UIEventType.SELECT_PLANET);
                            planetEvent.planet = planetActor.getPlanet();
                            GameScene.this.ui.receiveUIEvent(planetEvent);
                            break;
                    }
                }
                
                super.touchDown(inputEvent, x, y, pointer, button);
                
                return true;
            }
            
	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		if(keycode == Input.Keys.RIGHT_BRACKET)
		{
		    gameSpeed *= 2;
                    gameSpeed = Math.min(32, gameSpeed);
		}
		
		if(keycode == Input.Keys.LEFT_BRACKET)
		{
                    gameSpeed /= 2;
                    gameSpeed = Math.max(1, gameSpeed);
		}
		
		return false;
	    }

	    @Override
	    public boolean scrolled(InputEvent event, float x, float y, int amount)
	    {
		currentMap.onZoomInput(amount*16);
		
		return super.scrolled(event, x, y, amount);
            }
	});
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        
        delta *= gameSpeed;
        
        frameTick(delta);
        
        tickSpeedTimer += delta;
        
        if(tickSpeedTimer > tickSpeed)
        {
            tickSpeedTimer = 0;
            tick();
        }
    }
    
    public void tick()
    { 
        galaxy.tick();

        for(Player player : players)
            player.tick();
    }
    
    private void frameTick(float delta)
    {
        galaxy.frameTick(delta);

        for(Player player : players)
            player.frameTick(delta);
    }
}
