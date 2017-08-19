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
import java.util.ArrayList;
import supergenericgametitlethegame.*;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.FRAME_PAUSE;
/**
 *
 * @author jiggy
 */
public class Player extends GraphicsProgram implements SuperGenericGameTitleTheGameConstants {
    
    double pHealth = 100;
    double xCoord = 100;
    double yCoord = 100;
    double startX = 0;
    double startY = 0;
   
    double insertstartingXcoordinate=0;
    double insertstartingYcoordinate=0;

        
    private int cycle = 3000;
    private int cyclebuffer = FRAME_PAUSE;
    private int currenttime = 0;
    private int defaultspawntime = 3000;
    
    private ArrayList<Bullet> bullettemplates = new ArrayList<>();
    private ArrayList<Integer> spawntimes = new ArrayList<>();
    private ArrayList<Double> spawnpoints = new ArrayList<>(); //curcular points around the enemy where bullets will spawn
    private ArrayList<Double> spawndistance = new ArrayList<>();
    private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    public GImage pImage = new GImage("player.gif");
      
  
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
        private void updateCycleTime(){
        cycle=0;
        for(int i=0; i<spawntimes.size(); i++){
            if(spawntimes.get(i)>cycle){
                cycle = spawntimes.get(i);
            }
        }
        cycle += cyclebuffer;
    }
    public void addBulletSpawn(String img, double newspawnpnt, double centerdistance, double speed, int spawntime){
        Bullet newbullet = new Bullet();
        newbullet.setImage(img);
        newbullet.setDirectionDegrees(newspawnpnt);
        newbullet.setVelocity(speed);
        bullettemplates.add(newbullet);
        spawntimes.add(spawntime);
        spawnpoints.add(newspawnpnt);
        spawndistance.add(centerdistance);
        alreadyspawned.add(Boolean.FALSE);
        updateCycleTime();
    }
    
    public void addBulletSpawn(Bullet newbullet, double newspawnpnt, double centerdistance, int spawntime){
        bullettemplates.add(newbullet);
        spawntimes.add(spawntime);
        spawnpoints.add(newspawnpnt);
        spawndistance.add(centerdistance);
        alreadyspawned.add(Boolean.FALSE);
        updateCycleTime();
    }
        public ArrayList getBullets(int time){
        //System.out.print(currenttime + "\n");
        ArrayList<Bullet> newbullets = new ArrayList<>();
        currenttime+=time;
        Bullet bullet = null;
        for(int i=0; i<spawntimes.size(); i++){
            //System.out.print("bullet "+ i+ ": "+spawntimes.get(i)+"\n");
            if(spawntimes.get(i)<currenttime && !alreadyspawned.get(i)){
                bullet = new Bullet();
                bullet.setImage(bullettemplates.get(i).getImageFile(), bullettemplates.get(i).getXsize(), bullettemplates.get(i).getYsize());
                bullet.setVelocity(bullettemplates.get(i).getXVelocity(), bullettemplates.get(i).getYVelocity());
                bullet.setLocation(getXCenter()+(spawndistance.get(i)*Math.cos(spawnpoints.get(i)*(3.14/180))), getYCenter()+(spawndistance.get(i)*Math.sin(spawnpoints.get(i)*(3.14/180))));
                newbullets.add(bullet);
                alreadyspawned.set(i, true);
                //System.out.print("Making new bullet\n");
            }
        }
        if(currenttime>cycle+cyclebuffer){
            currenttime=0;
            for(int i=0; i<alreadyspawned.size(); i++){
                alreadyspawned.set(i, Boolean.FALSE);
            }
        }
            
        return newbullets;
    }
    public double getHealth(){
        return pHealth;
    }
    

    public void attackPlayer(){
        
    }
        public double getXCenter(){
        return xCoord+(pImage.getWidth()/2);
    }
    
    public double getYCenter(){
        return yCoord-(pImage.getHeight()/2);
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
