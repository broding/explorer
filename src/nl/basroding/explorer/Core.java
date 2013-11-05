package nl.basroding.explorer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import nl.basroding.explorer.display.Scene;
import nl.basroding.explorer.display.SpaceCamera;
import nl.basroding.explorer.scenes.GameScene;

/**
 *
 * @author basroding
 */
public class Core implements ApplicationListener
{
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Scene scene;
    private SpaceCamera camera;

    public Core(Scene scene)
    {
	Game.core = this;
	this.scene = scene;
    }

    @Override
    public void create()
    {
	spriteBatch = new SpriteBatch();
	stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, spriteBatch);
	scene = new GameScene();
	camera = new SpaceCamera();
	stage.setCamera(camera);
	stage.addActor(scene);
	stage.setKeyboardFocus(scene);
	stage.setScrollFocus(scene);
	Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int i, int i1)
    {
    }

    @Override
    public void render()
    {
	if (spriteBatch == null)
	    return;
	
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	stage.act();
	stage.draw();
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
	if (this.scene != null)
	{
	    this.scene.dispose();
	}

	this.scene = scene;
    }

    public SpaceCamera getCamera()
    {
	return this.camera;
    }
}
