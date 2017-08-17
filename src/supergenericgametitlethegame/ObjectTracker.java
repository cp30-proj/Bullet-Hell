/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import bulletHell.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author Paolo
 */
public class ObjectTracker implements SuperGenericGameTitleTheGameConstants{
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final LinkedList<Enemy> enemies = new LinkedList<>();
    private double xboundary = 0;
    private double yboundary = 0;
    private Level Level = new Level();
    
    public void addProjectile(Bullet newbullet){
        bullets.add(newbullet);
    }
    public void addProjectile(Iterator newbullets){
        while(newbullets.hasNext()){
            bullets.add((Bullet)newbullets.next());
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
        removeOffScreenObjects();
        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).updateLocation(FRAME_PAUSE);
            //System.out.print("Bullet "+i+" location"+bullets.get(i).getX()+", "+bullets.get(i).getY()+"\n");
        }
        for(int i=0; i<enemies.size(); i++){
            ArrayList addbullets = enemies.get(i).getBullets(FRAME_PAUSE);
            for(int j=0; j<addbullets.size(); j++){
                System.out.print("bullets recieves\n");
                bullets.add((Bullet)addbullets.get(j));
            }
            enemies.get(i).updateLocation(FRAME_PAUSE);
        }
        System.out.print("Current Bullets: "+ bullets.size()+"\nCurrent Enemies: "+enemies.size()+"\n");
    }
    
    public Stack getBullets(){
        Stack<Bullet> printbullets = new Stack<>();
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
    
    private void removeOffScreenObjects(){
        double x = 0;
        double y = 0;
        for(int i=bullets.size()-1; i>=0; i--){
            x = bullets.get(i).getX();
            y = bullets.get(i).getY();
            if((x>(xboundary + OFFSCREEN_BUFFER)) || (x<(0-bullets.get(i).getXsize()-OFFSCREEN_BUFFER)) || (y>(yboundary + OFFSCREEN_BUFFER)) || (y<(0-bullets.get(i).getYsize()-OFFSCREEN_BUFFER))){
                bullets.remove(i);
            }
        }
        for(int i=enemies.size()-1; i>=0; i--){
            x = enemies.get(i).getX();
            y = enemies.get(i).getY();
            if((x>(xboundary + OFFSCREEN_BUFFER)) || (x<(0-enemies.get(i).getXsize()-OFFSCREEN_BUFFER)) || (y>(yboundary + OFFSCREEN_BUFFER)) || (y<(0-enemies.get(i).getYsize()-OFFSCREEN_BUFFER))){
                enemies.remove(i);
            }
        }
    }
    
    public void setBounds(double xbound, double ybound){
        xboundary = xbound;
        yboundary = ybound;
    }
}
