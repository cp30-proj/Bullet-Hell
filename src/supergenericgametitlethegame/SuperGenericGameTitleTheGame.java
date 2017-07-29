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
    private Queue meme;
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        add(Score, NORTH);
        add(canvas);
        
    }
    public void run(){
        canvas.placeholder();
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
