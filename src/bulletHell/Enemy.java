/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;
import acm.graphics.GImage;
import bulletHell.Projectile;
/**
 *
 * @author Paolo
 */
public class Enemy{
    private int health;
    private GImage image = null;
    private double xvelocity = 0;
    private double xlocation = 0;
    private double yvelocity = 0;
    private double ylocation = 0;
    private double direction = 0; //in degrees
    private double accelerate = 0;
    private double accel_direction = 0;
    private double sizex = 0;
    private double sizey = 0;
    
    private int n_behaviors = 5;
    private boolean[] behavior = new boolean[5];
    
    public void Enemy(){
        for(int i = 0; i<n_behaviors; i++){
            behavior[i] = false;
        }
    }
    
    public void updateLocation(double time){
        behaviorAction();
        xlocation+=(xvelocity*time/1000);
        ylocation+=(yvelocity*time/1000);
    }
    
    public void behaviorAction(){
        if(behavior[0]){ 
            
        }
    }
    
    public void setBehavior(int index, boolean active){
        behavior[index] = active;
    }
    
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
    public void setImage(String filename){
        image = new GImage(filename);
        sizex = image.getWidth();
        sizey = image.getWidth();
    }
    
    public void setImage(GImage img){
        image = img;
        sizex = image.getWidth();
        sizey = image.getWidth();
    }
    
    public void setImage(String filename, double sizex, double sizey){
        image = new GImage(filename);
        image.setSize(sizex, sizey);
        this.sizex = sizex;
        this.sizey = sizey;
    }
    
    public void setImage(GImage img, double sizex, double sizey){
        image = img;
        image.setSize(sizex, sizey);
        this.sizex = sizex;
        this.sizey = sizey;
    }
    
    public void setImageSize(double sizex, double sizey){
        image.setSize(sizex, sizey);
        this.sizex = sizex;
        this.sizey = sizey;
    }
    
    public double getXsize(){
        return sizex;
    }
    
    public double getYsize(){
        return sizey;
    }
    
    public GImage getImage(){
        return image;
    }
    
    public void setLocation(double x, double y){
        xlocation = x;
        ylocation = y;
    }
    
    
    
}
