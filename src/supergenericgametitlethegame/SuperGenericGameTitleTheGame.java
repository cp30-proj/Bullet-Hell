/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import bulletHell.*;
import acm.program.*;
import java.util.Queue;
import javax.swing.JLabel;
/**
 *
 * @author Paolo
 */
public class SuperGenericGameTitleTheGame extends Program implements SuperGenericGameTitleTheGameConstants{
    private Projectile samplebullet = new Projectile();
    private GameCanvas canvas = new GameCanvas();
    private JLabel Score = new JLabel("Placeholder");
    private ObjectTracker tracker = new ObjectTracker();
    private Queue meme;
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        add(Score, NORTH);
        add(canvas);
        
    }
    public void run(){
        demobullet();
        //canvas.placeholder();
        while(true){
            tracker.updateObjects();
            canvas.drawFrame(tracker.getBullets());
            pause(FRAME_PAUSE);
        }
    }
    
    public void demobullet(){
        Projectile bullet = new Projectile();
        bullet.setLocation(50, 50);
        bullet.setDirectionDegrees(300);
        bullet.setVelocity(100);
        bullet.setProjectileImage("bluebullet.png");
        tracker.addProjectile(bullet);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
