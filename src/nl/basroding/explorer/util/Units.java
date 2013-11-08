/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.util;

/**
 *
 * @author basroding
 */
public class Units
{
    public static final int POINT_TO_METER = 1566000;
    
    public static float pointToKilometer(float points)
    {
        return points * POINT_TO_METER / 1000;
    }
}
