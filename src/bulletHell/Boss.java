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
    private GraphDP<Enemy> bosses = new GraphDP<>();
    ArrayList<Enemy> visited = new ArrayList<>();
    
    //private ArrayList<Enemy> enemytemplates = new ArrayList<>();
    //private ArrayList<Integer> spawntimes = new ArrayList<>();
    //private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    public void Boss(){
        //initialize bigboss
        bosses.addVertex(bigboss);
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT);     
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(270);
        bullet.setVelocity(400);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 100);    
        //tracker.addProjectile(bullet);      
        enemy.setLocation(275,200);
        enemy.setVelocity(0, 0);
        enemy.setImage("boss.gif");
        enemy.setImageSize(200, 200);
        
    }
    
    public void addEnemySpawn(Enemy newenemy, int spawntime){
        
    }
    
    public ArrayList spawnEnemies(int time){
        //System.out.print(currenttime + "\n");
        visited.clear();
        ArrayList<Enemy> newenemies = new ArrayList<>();
        Enemy current = bigboss;
        Queue bfsq = new LinkedList();
        boolean hasAdjecentUnvisited;
        bfsq.add(bigboss);
        while(!bfsq.isEmpty()){
            hasAdjecentUnvisited=false;
            current = (Enemy)bfsq.remove();
            if(bosses.getNumConnections(current)<2){
                Enemy newsmolboss = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                //make small boss;
                newenemies.add(newsmolboss);
                bosses.addVertex(newsmolboss);
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
