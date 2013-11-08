/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.models;

/**
 *
 * @author basroding
 */
public class Sun extends Planet
{
    public final static int SUN_MAX_RADIUS = 700;
    public final static int SUN_MIN_RADIUS = 400;
    
    public Sun()
    {
        super();
        
        minRadius = SUN_MIN_RADIUS;
        maxRadius = SUN_MAX_RADIUS;
        
    }
}
