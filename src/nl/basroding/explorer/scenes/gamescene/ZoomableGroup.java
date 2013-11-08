package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import nl.basroding.explorer.Game;

/**
 *
 * @author basroding
 */
public class ZoomableGroup extends Group
{
    private Actor selectedActor;

    public ZoomableGroup()
    {
	this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	this.setTouchable(Touchable.enabled);
    }
    
    public void onZoomInput(float zoom)
    {
	Game.getCamera().setZoom(Game.getCamera().zoom + Game.getCamera().zoom / 100 * zoom);
	Game.getCamera().update();
    }
    
    public void selectActor(Actor actor)
    {
	Game.getCamera().translate(actor.getX(), actor.getY());
    }
}
