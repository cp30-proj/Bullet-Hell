/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHellObjectClasses;

/**
 *
 * @author Paolo
 */
public class Projectile {
    double xvelocity = 0;
    double yvelocity = 0;
    double direction = 0; //in radians
    
    
    public void setVelocity(double new_velocity){
        xvelocity = new_velocity * Math.cos(direction);
        yvelocity = new_velocity * Math.sin(direction);
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
    
}
