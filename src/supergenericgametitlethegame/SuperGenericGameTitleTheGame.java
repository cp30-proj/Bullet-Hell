/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import bulletHell.*;
import acm.program.*;
import javax.swing.JLabel;
/**
 *
 * @author Paolo
 */
public class SuperGenericGameTitleTheGame extends Program implements SuperGenericGameTitleTheGameConstants{
    private Bullet samplebullet = new Bullet();
    private GameCanvas canvas = new GameCanvas();
    private JLabel Score = new JLabel("Placeholder");
    private ObjectTracker tracker = new ObjectTracker();
    private Level Level = new Level();
    private Player Player = new Player();
    int x = 0;
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        
        add(Score, NORTH);
        add(canvas);
        canvas.addbg(1);
        
    }
    public void run(){
        tracker.setBounds(getWidth(), getHeight());
        demo();
        //canvas.placeholder();
        while(true){
            tracker.updateObjects();
            canvas.drawFrame(tracker.getBullets(), tracker.getEnemies());
            pause(FRAME_PAUSE);
            
        }
    }
    
    public void demo(){
        
        
        Bullet bullet = new Bullet();
        bullet.setLocation(50, 50);
        bullet.setDirectionDegrees(290);
        bullet.setVelocity(200);
        bullet.setImage("bluebullet.png");
        tracker.addProjectile(bullet);
        bullet = new Bullet();
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(270);
        bullet.setVelocity(75);
        bullet.setImage("redbullet.png");
        tracker.addProjectile(bullet);
        
        Enemy enemy = new Enemy(getWidth(), getHeight());
        bullet = new Bullet();
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(270);
        bullet.setVelocity(75);
        bullet.setImage("redbullet.png");
        tracker.addProjectile(bullet);
        enemy.addBulletSpawn(bullet, 270, 10, 500);
        enemy.setLocation(50, 50);
        enemy.setVelocity(0);
        enemy.setImage("centrifuge.png");
        enemy.setImageSize(30, 30);
        tracker.addEnemy(enemy);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
