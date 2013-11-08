/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import java.text.DecimalFormat;
import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.util.Units;

/**
 *
 * @author basroding
 */
public class PlanetInfo extends Group
{
    private Planet planet;
    
    private final List attributesList;
    private final List valueList;
    
    public PlanetInfo()
    {
        String[] attributes = {"Name:", "Radius:", "Temperature:"};
        attributesList = new List(attributes, UIOverlay.defaultSkin);
        attributesList.setSelectable(false);
        
        String[] values = {"Jupiter", "14364240024 km", "-42 degrees"};
        valueList = new List(values, UIOverlay.defaultSkin);
        valueList.setSelectable(false);
        valueList.setX(attributesList.getWidth() + 25);
        
        addActor(attributesList);
        addActor(valueList);
        
        this.setWidth(200);
        this.setHeight(attributesList.getHeight());
    }

    public void setPlanet(Planet planet)
    {
        this.planet = planet;
        
        DecimalFormat formatter = new DecimalFormat("#,###");
        String[] values = {"Jupiter", formatter.format(Units.pointToKilometer(planet.getRadius())) + " km", "-42 degrees"};
        valueList.setItems(values);
    }

    public Planet getPlanet()
    {
        return planet;
    }

    boolean isPlanetSelected()
    {
        return this.isVisible();
    }
}
