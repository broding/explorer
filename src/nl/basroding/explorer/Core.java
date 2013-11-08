package nl.basroding.explorer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import nl.basroding.explorer.display.Scene;
import nl.basroding.explorer.display.SpaceCamera;
import nl.basroding.explorer.scenes.GameScene;
import nl.basroding.explorer.scenes.gamescene.ui.SpaceOverlay;
import nl.basroding.explorer.scenes.gamescene.ui.UIOverlay;

/**
 *
 * @author basroding
 */
public class Core implements ApplicationListener
{
    private Stage gameStage;
    private Stage uiStage;
    
    private SpriteBatch spriteBatch;
    
    private Scene scene;
    private UIOverlay ui;
            
    private SpaceCamera camera;
    private OrthographicCamera uiCamera;
    
    private InputMultiplexer multiplexer;
    
    public Core(Scene scene)
    {
	Game.core = this;
	this.scene = scene;
    }

    @Override
    public void create()
    {
        multiplexer = new InputMultiplexer();
	spriteBatch = new SpriteBatch();

	camera = new SpaceCamera();
	uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.translate(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        
	gameStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, spriteBatch);
	gameStage.setCamera(camera);
        
        uiStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, spriteBatch);
	uiStage.setCamera(uiCamera);
        
        ui = new SpaceOverlay();
	scene = new GameScene(ui);
        
	gameStage.addActor(scene);
	gameStage.setKeyboardFocus(scene);
	gameStage.setScrollFocus(scene);
        
        uiStage.addActor(ui);
        
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(gameStage);
	Gdx.input.setInputProcessor(multiplexer);
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
	
	gameStage.act();
        uiStage.act();
        
	gameStage.draw();
        uiStage.draw();
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
	return (SpaceCamera) gameStage.getCamera();
    }

    void setCamera(SpaceCamera camera)
    {
        gameStage.setCamera(camera);
    }
}
