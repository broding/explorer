package nl.basroding.explorer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import nl.basroding.explorer.display.Scene;

/**
 *
 * @author basroding
 */
public class Core implements ApplicationListener
{
	private SpriteBatch spriteBatch;
	private Scene scene;
	
	public Core(Scene scene)
	{
		Game.core = this;
		this.scene = scene;
	}
	
	@Override
	public void create()
	{
		this.spriteBatch = new SpriteBatch();
	}

	@Override
	public void resize(int i, int i1)
	{
	}

	@Override
	public void render()
	{
		if(spriteBatch == null)
			return;
		
		this.scene.act(Gdx.graphics.getDeltaTime());
		
		spriteBatch.begin();
		this.scene.draw(spriteBatch, 1.0f);
		spriteBatch.end();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
		this.scene.dispose();
	}

	public void switchScene(Scene scene)
	{
		if(this.scene != null)
			this.scene.dispose();
		
		this.scene = scene;
	}
}
