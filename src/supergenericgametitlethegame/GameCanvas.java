/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.util.Queue;
import bulletHell.*;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Paolo
 */
public class GameCanvas extends GCanvas implements SuperGenericGameTitleTheGameConstants{
    private GLabel placeholder = new GLabel("Placeholder");
    
    public void placeholder(){
        placeholder.setLocation(getWidth()/2, getHeight()/2);
        add(placeholder);
    }
    
    public void drawFrame(Stack bullets){
        while(!bullets.empty()){
            Projectile bullet = (Projectile)bullets.pop();
            add(bullet.getImage(), bullet.getX(), bullet.getY());
        }
    }
    
    
}
