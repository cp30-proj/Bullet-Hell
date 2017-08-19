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
    private BHImage image = new BHImage();
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
    
    private int animationtimer = DYING_ANIMATION_BUFFER;
    private String dyinganimation = "explosion.gif";
    
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
            newbullet.setDirectionDegrees((angleshift*i)+rotate);
            newbullet.setVelocity(speed);
            bullettemplates.add(newbullet);
            spawntimes.add(spawntime);
            spawnpoints.add((angleshift*i)+rotate);
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
                bullet.setShooter(this);
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
    
    private void behaviorAction(){
        if(behavior[0]){ 
            if(xlocation<0 || (xlocation+xsize)>xboundary)
                xvelocity*=-1;
            if(ylocation<0 || (ylocation+ysize)>yboundary)
                yvelocity*=-1;
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
    
    public int getAnimationTime(){
        return animationtimer;
    }
    
    public ArrayList getSpawns(){
        ArrayList spawns = new ArrayList();
        ArrayList temp = new ArrayList();
        temp.addAll(bullettemplates);
        spawns.add(temp);
        temp = new ArrayList();
        temp.addAll(spawntimes);
        spawns.add(temp);temp = new ArrayList();
        temp.addAll(spawnpoints);
        spawns.add(temp);temp = new ArrayList();
        temp.addAll(spawndistance);
        spawns.add(temp);temp = new ArrayList();
        temp.addAll(alreadyspawned);
        spawns.add(temp);
        return spawns;
    }
    
    public void setSpawns(ArrayList spawns){
        bullettemplates = (ArrayList)spawns.get(0);
        spawntimes = (ArrayList)spawns.get(1);
        spawnpoints = (ArrayList)spawns.get(2);
        spawndistance = (ArrayList)spawns.get(3);
        alreadyspawned = (ArrayList)spawns.get(4);
        updateCycleTime();
    }
    
    public void setHealth(int hearts){
        health = hearts;
    }
    
    public void damage(int damage){
        health-=damage;
        if(health<0)
            kill();
    }
    
    public void kill(){
        clearSpawns();
        health=-1;
        image.setImage(new GImage(dyinganimation));
        image.getImage().setSize(EXPLOSION_SIZE, EXPLOSION_SIZE);
        xvelocity=0;
        yvelocity=0;
        xsize=EXPLOSION_SIZE;
        ysize=EXPLOSION_SIZE;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void updateLocation(double time){
        behaviorAction();
        xlocation+=(xvelocity*time/1000);
        ylocation+=(yvelocity*time/1000);
        if(health<0){
            animationtimer -= time;
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
        imgfile = filename;
        image.setImage(new GImage(filename));
        xsize = image.getImage().getWidth();
        ysize = image.getImage().getWidth();
    }
    
    
    public void setImage(String filename, double xsize, double ysize){
        imgfile = filename;
        image.setImage(new GImage(filename));
        image.getImage().setSize(xsize, ysize);
        this.xsize = xsize;
        this.ysize = ysize;
    }
    
    
    
    public void setImageSize(double xsize, double ysize){
        image.getImage().setSize(xsize, ysize);
        this.xsize = xsize;
        this.ysize = ysize;
    }
    
    public GImage getImage(){
        return image.getImage();
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
