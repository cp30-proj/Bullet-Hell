/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;

import ADTs.Graph.GraphDP;
import acm.graphics.*;
import java.awt.*;
import java.util.*;
import javax.swing.JTree;
import supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.APPLICATION_HEIGHT;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.APPLICATION_WIDTH;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.FRAME_PAUSE;


/**
 *
 * @author Paolo
 */
public class Boss implements SuperGenericGameTitleTheGameConstants{
    public static GImage bg;
    
    private double xboundary = 0;
    private double yboundary = 0;
    
    private int cycle = 3000;
    private int cyclebuffer = FRAME_PAUSE;
    private int currenttime = 0;
    private int defaultspawntime = 3000;
    private boolean levelfinished = false;
    
    private Enemy bigboss = null;
    private boolean bigbossspawned = false;
    private GraphDP<Enemy> bosses = new GraphDP<>();
    ArrayList<Enemy> visited = new ArrayList<>();
    
    //private ArrayList<Enemy> enemytemplates = new ArrayList<>();
    //private ArrayList<Integer> spawntimes = new ArrayList<>();
    //private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    public void initBoss(){
        //initialize bigboss
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT/2);     
        bullet = new Bullet();    
        bullet.setLocation(200, 20);
        bullet.setDirectionDegrees(270);
        bullet.setVelocity(400);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 100);    
        //tracker.addProjectile(bullet);      
        enemy.setLocation(275,200);
        enemy.setVelocity(50, 10);
        enemy.setBehavior(0, true);
        enemy.setImage("boss.gif", 50,50);
        enemy.setImageSize(200, 200);
        enemy.setHealth(300);
        bigboss=enemy;
        bosses.addVertex(bigboss);
    }
    
    public void addEnemySpawn(Enemy newenemy, int spawntime){
        
    }
    
    public ArrayList spawnEnemies(int time){
        //System.out.print(currenttime + "\n");
        currenttime+=time;
        visited.clear();
        ArrayList<Enemy> newenemies = new ArrayList<>();
        if(currenttime>cycle){
            currenttime=0;
        if(!bigbossspawned){
            newenemies.add(bigboss);
            bigbossspawned=true;
            System.out.print("spawning bigboss\n");
            return newenemies;
        }
        else{
        Enemy current = bigboss;
        Queue bfsq = new LinkedList();
        bfsq.add(bigboss);
        while(!bfsq.isEmpty()){
            current = (Enemy)bfsq.remove();
            if(bosses.getNumConnections(current)<2&&bigboss.getHealth()>0){
                System.out.print("spawning smol boss\n");
                Enemy newsmolboss = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                newsmolboss.setImage(bigboss.getImageFile(), bigboss.getXsize()/2, bigboss.getYsize()/2);
                newsmolboss.setVelocity(bigboss.getXVelocity()*-1, bigboss.getYVelocity());
                newsmolboss.setLocation(bigboss.getX(), bigboss.getY());
                newsmolboss.setSpawns(bigboss.getSpawns());
                newsmolboss.setBehavior(0, true);
                newsmolboss.setHealth(bigboss.getHealth()/2);
                newenemies.add(newsmolboss);
                bosses.addVertex(newsmolboss);
                //System.out.print(bosses.getNumConnections(current)+"\n");
                bosses.addconnection(current, newsmolboss, 1);
                if(bosses.getNumConnections(current)<2)
                    continue;
                else
                    break;
            }
            if(!visited.contains(current)){
                visited.add(current);
            }
            for(Object element: bosses.getAdjecency(current)){
                if(!visited.contains(element)){
                    visited.add((Enemy)element);
                    bfsq.add(element);
                }
            }
        }
        return newenemies;
        }
        }
        return newenemies;
    }
    
    
    public boolean isLevelFinished(){
        return levelfinished;
    }
    
    public void setcyclebuffer(int buffer){
        cyclebuffer = buffer;
    }
    
    public static void setbglevel(String filename){
            bg = new GImage(filename);     
    }
    public static void setmusiclevel(int level){
        
    }
    
}
