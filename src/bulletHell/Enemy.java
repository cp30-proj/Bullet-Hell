/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;
import acm.graphics.GImage;
import bulletHell.Bullet;
import java.util.ArrayList;
import java.util.Iterator;
import supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants;
/**
 *
 * @author Paolo
 */
public class Enemy implements SuperGenericGameTitleTheGameConstants{
    private int health = 1;
    private GImage image = null;
    private String imgfile = "";
    private double xvelocity = 0;
    private double xlocation = 0;
    private double yvelocity = 0;
    private double ylocation = 0;
    private double direction = 0; //in degrees
    private double accelerate = 0;
    private double accel_direction = 0;
    private double xsize = 0;
    private double ysize = 0;
    private double xboundary = 0;
    private double yboundary = 0;
    
    private int cycle = 3000;
    private int cyclebuffer = FRAME_PAUSE;
    private int currenttime = 0;
    private int defaultspawntime = 3000;
    
    private ArrayList<Bullet> bullettemplates = new ArrayList<>();
    private ArrayList<Integer> spawntimes = new ArrayList<>();
    private ArrayList<Double> spawnpoints = new ArrayList<>(); //curcular points around the enemy where bullets will spawn
    private ArrayList<Double> spawndistance = new ArrayList<>();
    private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    private int n_behaviors = 5;
    private boolean[] behavior = new boolean[5];
    
    public Enemy(int xbound, int ybound){
        xboundary = xbound;
        yboundary = ybound;
        for(int i = 0; i<n_behaviors; i++){
            behavior[i] = false;
        }
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
    
    public void addCirclePattern(Bullet bullet, int numbullets, double speed, int spawntime, double rotate){
        Bullet newbullet;
        double angleshift=(360/numbullets);
        for(int i=0; i<numbullets; i++){
            newbullet = new Bullet();
            newbullet.setImage(bullet.getImageFile(), bullet.getXsize(), bullet.getYsize());
            newbullet.setDirectionDegrees(angleshift*i);
            newbullet.setVelocity(speed);
            bullettemplates.add(newbullet);
            spawntimes.add(spawntime);
            spawnpoints.add((angleshift*i)*rotate);
            spawndistance.add(0.0);
            alreadyspawned.add(Boolean.FALSE);
        }
        updateCycleTime();
    }
    
    public void clearSpawns(){
        bullettemplates.clear();
        spawntimes.clear();
        spawnpoints.clear();
        spawndistance.clear();
        alreadyspawned.clear();
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
                bullet.setImage(bullettemplates.get(i).getImageFile());
                bullet.setVelocity(bullettemplates.get(i).getXVelocity(), bullettemplates.get(i).getYVelocity());
                bullet.setLocation(getXCenter()+(spawndistance.get(i)*Math.cos(spawnpoints.get(i)*(3.14/180))), getYCenter()-(spawndistance.get(i)*Math.sin(spawnpoints.get(i)*(3.14/180))));
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
    
    private void behaviorAction(){
        if(behavior[0]){ 
            
        }
    }
    
    public void setcyclebuffer(int buffer){
        cyclebuffer = buffer;
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
    
    public void setHealth(int hearts){
        health = hearts;
    }
    
    public void damage(int damage){
        health-=damage;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void updateLocation(double time){
        behaviorAction();
        xlocation+=(xvelocity*time/1000);
        ylocation+=(yvelocity*time/1000);
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
        imgfile = filename;
        image = new GImage(filename);
        xsize = image.getWidth();
        ysize = image.getWidth();
    }
    
    public void setImage(String filename, double xsize, double ysize){
        imgfile = filename;
        image = new GImage(filename);
        image.setSize(xsize, ysize);
        this.xsize = xsize;
        this.ysize = ysize;
    }
    
    public void setImageSize(double xsize, double ysize){
        image.setSize(xsize, ysize);
        this.xsize = xsize;
        this.ysize = ysize;
    }
    
    public GImage getImage(){
        return image;
    }
    
    public String getImageFile(){
        return imgfile;
    }
    
    public double getXsize(){
        return xsize;
    }
    
    public double getYsize(){
        return ysize;
    }
    
    public void setLocation(double x, double y){
        xlocation = x;
        ylocation = y;
    }
    
    public double getXCenter(){
        return xlocation+(xsize/2);
    }
    
    public double getYCenter(){
        return ylocation-(ysize/2);
    }
}
