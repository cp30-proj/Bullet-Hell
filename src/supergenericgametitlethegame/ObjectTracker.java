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
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author Paolo
 */
public class ObjectTracker implements SuperGenericGameTitleTheGameConstants{
    private final ArrayList<Projectile> bullets = new ArrayList<>();
    private final LinkedList<Enemy> enemies = new LinkedList<>();
    
    
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
    
    public Stack getBullets(){
        Stack<Projectile> printbullets = new Stack<>();
        for(int i=0; i<bullets.size(); i++){
            printbullets.add(bullets.get(i));
        }
        return printbullets;
    }
    public Stack getEnemies(){
        Stack<Enemy> printenemies = new Stack<>();
        for(int i=0; i<enemies.size(); i++){
            printenemies.add(enemies.get(i));
        }
        return printenemies;
    }
    
}
