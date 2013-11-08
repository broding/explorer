/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basroding
 */
public class Player implements Model
{
    private final List<Rocket> rockets;
    private final StarSystem starSystem;
    
    public Player(StarSystem starSystem)
    {
        this.starSystem = starSystem;
        
        rockets = new ArrayList<Rocket>();
        
        createRocket();
    }
    
    private Rocket createRocket()
    {
        Rocket rocket = new Rocket();
        rocket.setStarSystem(starSystem);
        rockets.add(rocket);
        
        return rocket;
    }

    public Iterable<Rocket> getRockets()
    {
        return rockets;
    }

    @Override
    public void tick()
    {
        for(Rocket rocket : rockets)
            rocket.tick();
    }

    @Override
    public void frameTick(float deltaTime)
    {
        for(Rocket rocket : rockets)
            rocket.frameTick(deltaTime);
    }
}
