/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import acm.graphics.GImage;
import java.awt.event.*;

/**
 *
 * @author jiggy
 */
public class Player extends GraphicsProgram {
    
    double pHealth = 100;
    double xCoord = 100;
    double yCoord = 100;
    double startX = 0;
    double startY = 0;
   
    double insertstartingXcoordinate=0;
    double insertstartingYcoordinate=0;


    
    public GImage pImage = new GImage("player.gif",80,80);
      
  
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
        
    
    public void damagePlayer(){
          //if (isPlayerHit())
              pHealth--;
    }
    public double getHealth(){
        return pHealth;
    }
    

    public void attackPlayer(){
        
    }

    public void setXCoordinate (double XCoor){
        xCoord = XCoor;
    }
   public void setYCoordinate (double YCoor){
        yCoord = YCoor;
    }
     public double getXCoordinate (){
       return  xCoord;
    }
    
        public double getYCoordinate (){
        return yCoord;
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
