/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.scenes.gamescene;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author basroding
 */
public interface Selectable
{
    Vector2 getPosition();
    SelectableType getSelectableType();
}
