/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;

import acm.graphics.*;
import acm.graphics.GImage;
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
   
    double insertstartingXcoordinate=0;
    double insertstartingYcoordinate=0;
    
 
    public GImage pImage = new GImage("fuck.png");
        
  
    double x=100;
    double y= 100;
        public void keyPressed(KeyEvent ke) {
            switch (ke.getKeyCode()) {
              
                case KeyEvent.VK_W:
                    pImage.setLocation(0.5*(insertstartingXcoordinate)-pImage.getWidth()+x,insertstartingYcoordinate-pImage.getHeight()+y);
                    y--;
                    break;
                case KeyEvent.VK_A:
                      pImage.setLocation(0.5*(insertstartingXcoordinate)-pImage.getWidth()+x,insertstartingYcoordinate-pImage.getHeight()+y);
                      x--;
                    break;
                case KeyEvent.VK_S:
                    pImage.setLocation(0.5*(insertstartingXcoordinate)-pImage.getWidth()+x,insertstartingYcoordinate-pImage.getHeight()+y);
                    y++;
                    break;
                case KeyEvent.VK_D:
                    pImage.setLocation(0.5*(insertstartingXcoordinate)-pImage.getWidth()+x,insertstartingYcoordinate-pImage.getHeight()+y);
                    x++;
                    break;
                case KeyEvent.VK_CONTROL:
                    //return projectiles to fire at opponent
                    break;
                default:
                    break;
            }
        }
    public void mouseMoved(MouseEvent me){
        pImage.setLocation(me.getX()-0.5*pImage.getWidth(),me.getY()-0.5*pImage.getHeight());
    }
    
    public void damagePlayer(){
          //if (isPlayerHit())
              pHealth--;
    }
    public void attackPlayer(){
        
    }

            
   // public boolean isPlayerHit(){
            //if( getElementAt(pImage.getX(),pImage.getY())!=null){
                //return true;
            //}
            //else if ( getElementAt(pImage.getX()+pImage.getWidth(),pImage.getY())!=null){
                //return true;
            //}
            //else if ( getElementAt(pImage.getX(),pImage.getY()+pImage.getHeight())!=null){
                //return true;
            //}
            //else if ( getElementAt(pImage.getX()+pImage.getWidth(),pImage.getY()+pImage.getHeight())!=null){
                //return true;
           // }
            //return false;
   // }
}
