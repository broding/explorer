/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author basroding
 */
public class UIOverlay extends Group implements UIEventListener
{
    public static Skin defaultSkin;
    
    public UIOverlay()
    {
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.setPosition(0, 0);
        
        if(defaultSkin == null)
        {
            defaultSkin = new Skin(Gdx.files.internal("images/ui_atlas/skin.skin"));
        }
    }

    @Override
    public void receiveUIEvent(UIEvent event)
    {
        
    }
}
