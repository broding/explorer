/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import nl.basroding.explorer.models.Rocket;
import nl.basroding.explorer.util.Units;

/**
 *
 * @author basroding
 */
public class RocketInfo extends Group
{
    public final TextButton button;
    
    private Rocket rocket;
    
    private final List attributesList;
    private final List valueList;
    
    public RocketInfo()
    {
        String[] attributes = {"Name:", "Speed:", "Heading for:"};
        attributesList = new List(attributes, UIOverlay.defaultSkin);
        attributesList.setSelectable(false);
        
        String[] values = {"Explorer #13", "100000 km/h", "Jupiter"};
        valueList = new List(values, UIOverlay.defaultSkin);
        valueList.setSelectable(false);
        valueList.setX(attributesList.getWidth() + 25);
        
        button = new TextButton("Set course", UIOverlay.defaultSkin);
        
        addActor(attributesList);
        addActor(valueList);
        addActor(button);
        
        this.setWidth(200);
        this.setHeight(attributesList.getHeight());
    }
    
    public void setRocket(Rocket rocket)
    {
        this.rocket = rocket;
        
        String[] values = {"Explorer #13", Units.pointToKilometer(rocket.getSpeed()) + " km/h", "Jupiter"};
        valueList.setItems(values);
        
    }

    public Rocket getRocket()
    {
        return rocket;
    }
}
