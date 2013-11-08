/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene.ui;

import nl.basroding.explorer.models.Planet;
import nl.basroding.explorer.models.Rocket;

/**
 *
 * @author basroding
 */
public class UIEvent
{
    private UIEventType type;
    
    public Planet planet;
    public Rocket rocket;
    
    public UIEvent(UIEventType type)
    {
        this.type = type;
    }

    public UIEventType getType()
    {
        return type;
    }
}
