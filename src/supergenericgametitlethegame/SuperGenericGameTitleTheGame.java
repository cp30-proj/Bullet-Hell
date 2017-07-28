/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import acm.program.*;
import bulletHellObjectClasses.*;
import javax.swing.JLabel;
/**
 *
 * @author Paolo
 */
public class SuperGenericGameTitleTheGame extends ConsoleProgram implements SuperGenericGameTitleTheGameConstants{
    private Projectile samplebullet = new Projectile();
    private GameCanvas canvas = new GameCanvas();
    private JLabel Score = new JLabel("Placeholder");
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        add(Score, NORTH);
        add(canvas);
    }
    public void run(){
        //dank 
        //memes
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
