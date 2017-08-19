/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;

/**
 *
 * @author Paolo
 */
public interface SuperGenericGameTitleTheGameConstants {
    public static final int APPLICATION_WIDTH = 500;

	/** The height of the application window */
    public static final int APPLICATION_HEIGHT = 700;
    
    /**the framerate of the game*/
    public static final int FPS = 60;
    /**the pause between frames in milliseconds. dependent on FPS*/
    public static final int FRAME_PAUSE = 1000/FPS;
    
    public static final double OFFSCREEN_BUFFER = 50;
    
    public static final int DYING_ANIMATION_BUFFER = 2000;
    
    public static final String DEFAULT_DYING_ANIMATION_FILE = "";
    
}
