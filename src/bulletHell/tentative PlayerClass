/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import acm.graphics.*;
import java.awt.event.*;

/**
 *
 * @author jiggy
 */
public class Player {

    double pHealth = 100;
    double xCoord = 0;
    double yCoord = 0;
    double startX = 0;
    double startY = 0;
    GImage pImage = null;

    public void drawPlayer() {
        pImage = new GImage(insert filename of image ?);
        add(pImage, insert starting Xcoordinate, insert starting Xcoordinate);
    }
    
    public void drawPlayer() {
        pImage = new GImage(insert filename of image ?);
        add(pImage, insert starting Xcoordinate, insert starting Xcoordinate);
    }
    
    public void mouseMoved(MouseEvent me){
        pImage.setLocation(me.getX()-0.5*pImage.getWidth(),me.getY()-0.5*pImage.getHeight());
    }
    
    public void damagePlayer(){
          if (isPlayerHit())
              pHealth--;
    }
    public boolean isPlayerHit(){
            if( getElementAt(pImage.getX(),pImage.getY())!=null){
                return true;
            }
            else if ( getElementAt(pImage.getX()+pImage.getWidth(),pImage.getY())!=null){
                return true;
            }
            else if ( getElementAt(pImage.getX(),pImage.getY()+pImage.getHeight())!=null){
                return true;
            }
            else if ( getElementAt(pImage.getX()+pImage.getWidth(),pImage.getY()+pImage.getHeight())!=null){
                return true;
            }
            return false;
    }
}
