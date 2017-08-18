/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;

import acm.graphics.*;
import java.awt.*;
import java.util.*;
import supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants;


/**
 *
 * @author Paolo
 */
public class Level implements SuperGenericGameTitleTheGameConstants{
    public GImage bg;
    public void setbglevel(String filename){
            bg = new GImage(filename);     
    }
    public void setmusiclevel(int level){
        
    }
    private double xboundary = 0;
    private double yboundary = 0;
    
    private int cycle = 3000;
    private int cyclebuffer = FRAME_PAUSE;
    private int currenttime = 0;
    private int defaultspawntime = 3000;
    
    private ArrayList<Enemy> enemytemplates = new ArrayList<>();
    private ArrayList<Integer> spawntimes = new ArrayList<>();
    private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    public void addEnemySpawn(Enemy newenemy, int spawntime){
        enemytemplates.add(newenemy);
        spawntimes.add(spawntime);
        alreadyspawned.add(Boolean.FALSE);
    }
    
    public ArrayList spawnEnemies(int time){
        //System.out.print(currenttime + "\n");
        ArrayList<Enemy> newenemies = new ArrayList<>();
        currenttime+=time;
        Enemy enemy = null;
        for(int i=0; i<spawntimes.size(); i++){
            if(spawntimes.get(i)<currenttime && !alreadyspawned.get(i)){
                enemy = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                enemy.setImage(enemytemplates.get(i).getImageFile(), enemytemplates.get(i).getXsize(), enemytemplates.get(i).getYsize());
                enemy.setVelocity(enemytemplates.get(i).getXVelocity(), enemytemplates.get(i).getYVelocity());
                enemy.setSpawns(enemytemplates.get(i).getSpawns());
                newenemies.add(enemy);
                alreadyspawned.set(i, true);
            }
        }
            
        return newenemies;
    }
    
    
    
}
