/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;

import acm.graphics.GImage;

/**
 *
 * @author Paolo
 */
public class BHImage {
    GImage img = null;
    Object owner = null;
    
    public void setImage(GImage image){
        img = image;
    }
    
    public GImage getImage(){
        return img;
    }
    
    public void setOwner(Object obj){
        owner = obj;
    }
    
    public Object getOwner(){
        return owner;
    }
    
}
