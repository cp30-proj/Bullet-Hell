/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import bulletHell.*;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
/**
 *
 * @author Paolo
 */
public class ObjectTracker implements SuperGenericGameTitleTheGameConstants{
    private ArrayList<Projectile> bullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    
    
    public void addProjectile(Projectile newbullet){
        bullets.add(newbullet);
    }
    public void addProjectile(Iterator newbullets){
        while(newbullets.hasNext()){
            bullets.add((Projectile)newbullets.next());
        }
    }
    
    public void addEnemy(Enemy newenemy){
        enemies.add(newenemy);
    }
    public void addEnemy(Iterator newenemies){
        while(!newenemies.hasNext()){
            enemies.add((Enemy)newenemies.next());
        }
    }
    
    public void updateObjects(){
        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).updateLocation(FRAME_PAUSE);
        }
        //add enemy update location
    }
    
    public Iterator getBullets(){
        Iterator printBullets = bullets.iterator();
        return printBullets;
    }
    public Iterator getEnemies(){
        Iterator printEnemies = enemies.iterator();
        return printEnemies;
    }
    
}
