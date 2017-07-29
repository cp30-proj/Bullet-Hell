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
public class Projectile {
    GImage bulletimage = null;
    double xvelocity = 0;
    double xlocation = 0;
    double yvelocity = 0;
    double ylocation = 0;
    double direction = 0; //in degrees
    
    
    public void setVelocity(double new_velocity){
        double rads = (3.14/180)*direction;
        xvelocity = new_velocity * Math.cos(rads);
        yvelocity = new_velocity * Math.sin(rads)*(-1);
    }
    
    public void setVelocity(double newx, double newy){
        xvelocity = newx;
        yvelocity = newy;
    }
    
    public void setXVelocity(double newx){
        xvelocity = newx;
    }
    public void setYVelocity(double newy){
        yvelocity = newy;
    }
    public double getXVelocity(){
        return xvelocity;
    }
    public double getYVelocity(){
        return yvelocity;
    }
    /**sets the direction in degrees*/
    public void setDirectionDegrees(double degree){
        direction = degree;
    }
    
    
    public double getX(){
        return xlocation;
    }
    
    public double getY(){
        return ylocation;
    }
    
    /**set image using the filename found in src/images */
    public void setProjectileImage(String filename){
        bulletimage = new GImage(filename);
    }
    
    public void setProjectileImage(GImage img){
        bulletimage = img;
    }
    
    public void setProjectileImage(String filename, double sizex, double sizey){
        bulletimage = new GImage(filename);
        bulletimage.setSize(sizex, sizey);
    }
    
    public void setProjectileImage(GImage img, double sizex, double sizey){
        bulletimage = img;
        bulletimage.setSize(sizex, sizey);
    }
    
    public void setLocation(double x, double y){
        xlocation = x;
        ylocation = y;
    }
    
    public GImage getProjectileImage(){
        return bulletimage;
    }
    
    public void updateLocation(double time){
        xlocation+=(xvelocity*time/1000);
        ylocation+=(yvelocity*time/1000);
    }
    
}
