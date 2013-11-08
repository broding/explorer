/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/**
 *
 * @author basroding
 */
public class SpaceOverlay extends UIOverlay
{
    private final RocketInfo rocketInfo;
    private final PlanetInfo planetInfo;
    
    public SpaceOverlay()
    {
        rocketInfo = new RocketInfo();
        rocketInfo.setX(0);
        rocketInfo.setY(Gdx.graphics.getHeight() - rocketInfo.getHeight());
        rocketInfo.setVisible(false);
        rocketInfo.button.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor)
            {
                if(planetInfo.isPlanetSelected())
                    rocketInfo.getRocket().setCourse(planetInfo.getPlanet());
            }
        });
        
        planetInfo = new PlanetInfo();
        planetInfo.setX(0);
        planetInfo.setY(Gdx.graphics.getHeight() - rocketInfo.getHeight() * 2 - 30);
        planetInfo.setVisible(false);
        
        addActor(rocketInfo);
        addActor(planetInfo);
    }

    @Override
    public void receiveUIEvent(UIEvent event)
    {
        switch(event.getType())
        {
            case SELECT_ROCKET:
                rocketInfo.setVisible(true);
                rocketInfo.setRocket(event.rocket);
                break;
            
            case SELECT_PLANET:
                planetInfo.setVisible(true);
                planetInfo.setPlanet(event.planet);
                break;
        }
    }
}
