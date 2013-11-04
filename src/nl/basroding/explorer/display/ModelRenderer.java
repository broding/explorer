/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.basroding.explorer.display;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

/**
 *
 * @author basroding
 */
public interface ModelRenderer 
{   
    void render(ModelBatch batch, PerspectiveCamera camera);
}
