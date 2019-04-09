/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import acm.graphics.GLabel;
import acm.io.IODialog;
import bulletHell.*;
import acm.program.*;
import javax.swing.JLabel;
import java.io.*;
import java.util.Stack;
import java.util.logging.Logger;
import sun.audio.*;
import java.awt.event.*;
/**
 *
 * @author Paolo
 */
public class SuperGenericGameTitleTheGame extends GraphicsProgram implements SuperGenericGameTitleTheGameConstants{
    private Bullet samplebullet = new Bullet();
    private ObjectTracker tracker = new ObjectTracker();
    private Level level = null;
    private Player Player = new Player();
   private JLabel Health = new JLabel("Health: "+ Player.getHealth());
   IODialog dialog = getDialog();
   private Boss boss = new Boss();
  
    int x = 0;
    int currentlevel = 1;
    InputStream music;
    
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////----- UCMI Connection -----///////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    UCMI ucmi = new UCMI();
    ControlConfig ctrlConfig;
    /// mine other
    int width,height;   //init bounds follows init window size
    
    
    ///////////////////////////////////////////////////////////////
    private GLabel placeholder = new GLabel("Placeholder");
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        ////////////////////////////////////////////////////////////////////////////
        //////////////////////----- UCMI Connection -----///////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ucmi.init();
        if(ucmi.isPortConnected){
            ucmi.ReqPlayer(1);
            System.out.println("port connected. 1st requests sent.");
        }
        ////////////////////////////////
        /// defining controls (default)
        ///////////////////////////////
        ctrlConfig = new ControlConfig(ucmi);
        ///////
        String[] genControlNames = new String[]{"Up","Down","Left","Right","Fire","Open Controls Settings"}; //please insrt new controls at end, unless wiling to change indexing in code.
        double[]  genDirGrpNo = new double[]{1.1,1.2,1.3,1.4,2,3};
        ///////
        ctrlConfig.gameControls = new String[2][]; //player indexing starts at '1'
        for (int i = 1; i < ctrlConfig.gameControls.length; i++) {
            ctrlConfig.gameControls[i] =  genControlNames;
        }
        ////////
        ctrlConfig.directionalGroupNo = new double[2][]; //player indexing starts at '1'
        for (int i = 1; i < ctrlConfig.directionalGroupNo.length; i++) {
            ctrlConfig.directionalGroupNo[i] = genDirGrpNo;
        }
        ////////
        ctrlConfig.kybdControls = new int[2][]; //player indexing starts at '1'
        ctrlConfig.kybdControls[1] = new int[]{KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_SPACE,KeyEvent.VK_ESCAPE};    //P1 default
        ////////
        ctrlConfig.uartControls = new CM[2][]; //player indexing starts at '1'
        CM[] genUart = new CM[]{CM.LEFT_ANALOG_STICK_Y,CM.LEFT_ANALOG_STICK_Y,CM.LEFT_ANALOG_STICK_X,CM.LEFT_ANALOG_STICK_X,CM.A_FACE_BUTTON,CM.SELECT_BUTTON};
        for (int i = 1; i < ctrlConfig.uartControls.length; i++) {
            ctrlConfig.uartControls[i] = genUart;
        }
        ////////
        ctrlConfig.uartHolddownWaitCount = new int[ctrlConfig.gameControls.length][ctrlConfig.gameControls[1].length];
        for (int i = 1; i < ctrlConfig.uartHolddownWaitCount.length; i++) {
            for (int j = 0; j < ctrlConfig.uartHolddownWaitCount[i].length; j++) {
                ctrlConfig.uartHolddownWaitCount[i][j] = 0;
            }
        }
        
        
        ///////////////////////////////
        ///////////////////////////////
        addKeyListeners();
        addMouseListeners();
        add(Health, NORTH);
        addbg(1);
        try {
            music();
        } catch (IOException ex) {
            Logger.getLogger(SuperGenericGameTitleTheGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        for(int i=0;i!=3;i++){
        Bullet bullet = new Bullet();
        bullet.setDirectionDegrees(60+(i*30));
        bullet.setVelocity(500);
        bullet.setImage("bluebullet.png", 10,10);
        Player.addBulletSpawn(bullet, 90, 10, 10);
        }
        tracker.setPlayer(Player);
        boss.initBoss();
    }
    
        public boolean isPlayerHit(){
            if( getElementAt(Player.pImage.getX(),Player.pImage.getY())!=null&&getElementAt(Player.pImage.getX(),Player.pImage.getY())!=Player.pImage&&getElementAt(Player.pImage.getX(),Player.pImage.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=null&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=Player.pImage&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!= null&&getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!=Player.pImage&&getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!=Level.bg){
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=null&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=Level.bg&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=Player.pImage){
                return true;
            }
            return false;
   }
    public void mouseMoved(MouseEvent me){/*
                Player.setXCoordinate(me.getX()-0.5*Player.pImage.getWidth());
                Player.setYCoordinate(me.getY()-0.5*Player.pImage.getHeight());*/
            }
    final static double PLAYER_SPEED = 15;
    boolean up = false,dn = false,lf = false,rt = false,fire = false;
    
    @Override
    public void keyPressed(KeyEvent e) {
        //vvvv Remember: Controls indexing Starts with [0]; players indexing starts with [1].
        //String[] genControlNames = new String[]{"Up","Down","Left","Right","Start","Select","Quit"}; <--from top-((as of 10:34 pc clk))]
        int id = e.getKeyCode();
        //System.out.println("id = " + id);
        //System.out.println("pressed: "+e.getKeyText(id));
        //System.out.println("kybdCtrls[1]:"+Arrays.toString(ctrlConfig.kybdControls[1]));
        //System.out.println("kybdCtrls[2]:"+Arrays.toString(ctrlConfig.kybdControls[2]));
        
        if(id == ctrlConfig.kybdControls[1][0]){//p1 up
            up = true;
        }
        if(id == ctrlConfig.kybdControls[1][1]){//p1 down
            dn = true;
        }
        if(id == ctrlConfig.kybdControls[1][2]){//p1 left
            lf = true;
        }
        if(id == ctrlConfig.kybdControls[1][3]){//p1 right
            rt = true;
        }
        if(id == ctrlConfig.kybdControls[1][4]){//p1 fire
            fire = true;
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        //vvvv Remember: Controls indexing Starts with [0]; players indexing starts with [1].
        //String[] genControlNames = new String[]{"Up","Down","Left","Right","Start","Select","Quit"}; <--from top-((as of 10:34 pc clk))]
        int id = e.getKeyCode();
        
        if(id == ctrlConfig.kybdControls[1][0]){//p1 up
            up = false;
        }
        if(id == ctrlConfig.kybdControls[1][1]){//p1 down
            dn = false;
        }
        if(id == ctrlConfig.kybdControls[1][2]){//p1 left
            lf = false;
        } 
        if(id == ctrlConfig.kybdControls[1][3]){//p1 right
            rt = false;
        } 
        if(id == ctrlConfig.kybdControls[1][4]){//any select (control settings)
            fire = false;
        }
        if(id == ctrlConfig.kybdControls[1][5]){//any select (control settings)
            actConSettings();
        }
    }
    
    ////////////////////////
    //actions
    private void shoot(){
        tracker.addProjectile(Player.getBullets(FRAME_PAUSE).iterator());
    }
    
    private void actConSettings(){  //open control settings
        if(ctrlConfig.jframe == null  || !ctrlConfig.jframe.isVisible()){
            ctrlConfig.openSettings(1, 0, 1, 2, 3, 4);
        }
    }
    ////
    private void moveUp(){
        double speed = PLAYER_SPEED;
        if(Player.getYCoordinate()-speed < 0){
            Player.setYCoordinate(0);
        } else {
            Player.setYCoordinate(Player.getYCoordinate()-speed);
        }
    }
    private void moveUp(double percentSpd){
        double speed = (percentSpd/100*PLAYER_SPEED);
        if(speed<0){
            speed = 1;
        }
        if(Player.getYCoordinate()-speed < 0){
            Player.setYCoordinate(0);
        } else {
            Player.setYCoordinate(Player.getYCoordinate()-speed);
        }
    }
    ////
    private void moveDn(){
        double speed = PLAYER_SPEED;
        if(Player.getYCoordinate()+Player.pImage.getHeight()+speed > height){
            Player.setYCoordinate(height-Player.pImage.getHeight());
        } else {
            Player.setYCoordinate(Player.getYCoordinate()+speed);
        }
    }
    private void moveDn(double percentSpd){
        double speed = (percentSpd/100*PLAYER_SPEED);
        if(speed<0){
            speed = 1;
        }
        if(Player.getYCoordinate()+Player.pImage.getHeight()+speed > height){
            Player.setYCoordinate(height-Player.pImage.getHeight());
        } else {
            Player.setYCoordinate(Player.getYCoordinate()+speed);
        }
    }
    ////
    private void moveRt(){
        double speed = PLAYER_SPEED;
        if(Player.getXCoordinate()+Player.pImage.getWidth()+speed > width){
            Player.setXCoordinate(width-Player.pImage.getWidth());
        } else {
            Player.setXCoordinate(Player.getXCoordinate()+speed);
        }
    }
    private void moveRt(double percentSpd){
        double speed = (percentSpd/100*PLAYER_SPEED);
        if(speed<0){
            speed = 1;
        }
        if(Player.getXCoordinate()+Player.pImage.getWidth()+speed > width){
            Player.setXCoordinate(width-Player.pImage.getWidth());
        } else {
            Player.setXCoordinate(Player.getXCoordinate()+speed);
        }
    }
    ////
    private void moveLf(){
        double speed = PLAYER_SPEED;
        if(Player.getXCoordinate()-speed < 0){
            Player.setXCoordinate(0);
        } else {
            Player.setXCoordinate(Player.getXCoordinate()-speed);
        }
    }
    private void moveLf(double percentSpd){
        double speed = (percentSpd/100*PLAYER_SPEED);
        if(speed<0){
            speed = 1;
        }
        if(Player.getXCoordinate()-speed < 0){
            Player.setXCoordinate(0);
        } else {
            Player.setXCoordinate(Player.getXCoordinate()-speed);
        }
    }
    
    
    ////
    
    public void run(){
        //mine
        width = getWidth();
        height = getHeight();
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        ////////////////
        tracker.setBounds(getWidth(), getHeight());
        dialog.println("[Up,Dn,Lf,Rt] - Movement \n [Fire] - Shoot Bullets");
        //placeholder();
        while(true){
            uartReadLoopIterate();
            /////
            demo();
            tracker.clearLists();
            while(Player.getHealth()>0){
                //player movement
                if(up){
                    moveUp();
                }
                if(dn){
                    moveDn();
                }
                if(lf){
                    moveLf();
                }
                if(rt){
                    moveRt();
                }
                if(fire){
                    shoot();
                }
                uartReadLoopIterate();
                
                
                tracker.updateObjects();
                pause(FRAME_PAUSE);
                if(level!=null&&boss!=null){
                    if(!level.isLevelFinished())
                        tracker.addEnemy(level.spawnEnemies(FRAME_PAUSE).iterator());
                    else{
                        tracker.addEnemy(boss.spawnEnemies(FRAME_PAUSE).iterator());
                    }
                }
                if(isPlayerHit()){
                    Player.damagePlayer(1);
                }
                Health.setText("Health"+ (Player.getHealth()-(Player.getHealth()%1)) + "  \tScore: "+Player.getScore());
                drawFrame(tracker.getBullets(), tracker.getEnemies());
                
            }
            uartLoopExitResetHoldDown();
            pause(2500);
            tracker.clearLists();
            Player.reset();
        }
    }
    public void music() throws FileNotFoundException, IOException{
        music=new FileInputStream(new File("src\\audio\\unowen.wav"));
        AudioStream audios=new AudioStream(music);
        AudioPlayer.player.start(audios); 
        
    }
    
    public void demo(){
        
        level = new Level();
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(0,50,200,0,270), 1000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(470,50,-200,0,270), 1000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(0,0,0,150,0), 4000+(i*800));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(470,0,0,150,180), 4800+(i*800));
        }
        level.addEnemySpawn(laser(470,0,0,0,270), 9000);
        level.addEnemySpawn(laser(470,50,0,0,230), 9000);
        level.addEnemySpawn(laser(0,0,0,0,310), 9000);
        level.addEnemySpawn(laser(0,50,0,0,270), 9000);
        level.addEnemySpawn(laser(320,100,0,0,270), 9500);
        level.addEnemySpawn(laser(150,100,0,0,270), 9500);
        level.addEnemySpawn(wiper(235,0,0,0), 10000);
        level.addEnemySpawn(spawncounterspinner(235,0,0,350), 12000);
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(285+(-i*100),0,0,350), 12200);
        }
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(335+(-i*200),0,0,350), 12400);
        }
        level.addEnemySpawn(wiper(235,0,0,20), 13000);
        level.addEnemySpawn(boomer(235,0,0,20), 16000);
        for(int i = 0;i!=2;i++){
        level.addEnemySpawn(boomer(385+(-i*300),0,0,20), 16000);
        }
        level.addEnemySpawn(spawncounterspinner(235,0,0,350), 17000);
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(285+(-i*100),0,0,350), 17200);
        }
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(335+(-i*200),0,0,350), 17400);
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,350,100), 19000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(spawncounterspinner(470,100,-350,100), 20000+(i*200));
        }
        for(int i = 0;i!=5;i++){
        level.addEnemySpawn(wiper(235,0,0,400), 22000+(i*200));
        }
        for(int i = 0;i!=2;i++){
            for(int j=0;j!=4;j++){
        level.addEnemySpawn(boomer(385+(-i*300),0,0,300), 22000+(j*400));
        }
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,350,100), 25000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawnclockwisespinner(470,100,-350,100), 25000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,0,200), 27000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawnclockwisespinner(470,100,0,200), 27000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(laser(0,0,0,200,0), 30000+(i*200));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(laser(470,0,0,200,180), 30000+(i*200));
        }
        //level.addEnemySpawn(boss(235,200,0,200,180), 35000);
        
    }
    public Enemy spawnclockwisespinner(int xloc, int yloc, int xvel, int yvel){
        Bullet bullet;
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=12;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(30*i);
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, i*100);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("spinner.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy spawncounterspinner(int xloc, int yloc, int xvel, int yvel){
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=12;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(120+(-i*30));
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, i*100);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("spinner.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy wiper(int xloc, int yloc, int xvel, int yvel){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=1;i!=18;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(-i*10);
        bullet.setVelocity(500);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, i*100);
        }
        for(int i=1;i!=18;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(180+(i*10));
        bullet.setVelocity(500);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, 1800+(i*100));
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("wiper.gif",50,50);
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy laser(int xloc, int yloc, int xvel, int yvel,int angle){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(angle);
        bullet.setVelocity(400);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 100);
       
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("ufo.png");
        enemy.setImageSize(50, 50);
        return enemy;
    }
    public Enemy boomer(int xloc, int yloc, int xvel, int yvel){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=8;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(45+(i*45));
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 600);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("boomer.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    
    public void placeholder(){
        placeholder.setLocation(getWidth()/2, getHeight()/2);
        add(placeholder);
    }
    public void addbg(int level){
        currentlevel=level;
        if(level==1){
           Level.setbglevel("testbg.png");
        }
        add(Level.bg,0,0);
    }
    public void addplayer(){
        add(Player.pImage);
    }
    public void setbglocation(int x){
        
    }
    

    
    public void drawFrame(Stack bullets, Stack enemies){
        removeAll();
        addbg(currentlevel);
        Player.pImage.setSize(50, 50);
        add(Player.pImage, Player.getXCoordinate(), Player.getYCoordinate() );
        while(!bullets.empty()){
            Bullet bullet = (Bullet)bullets.pop();
            add(bullet.getImage(), bullet.getX()-(bullet.getXsize()/2), bullet.getY()-(bullet.getYsize()/2));
        }
        while(!enemies.empty()){
            Enemy enemy = (Enemy)enemies.pop();
            add(enemy.getImage(), enemy.getX()-(enemy.getXsize()/2), enemy.getY()-(enemy.getYsize()/2));
        }
    }
    
    //////////// UART
    private void uartReadLoopIterate() {
        ////////////////////////////////////////////////////////////////////////////
        //////////////////////----- UCMI Connection -----///////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        //uart controls (Movement + Fire)
        if (ucmi.isPortConnected && !ctrlConfig.disableCMInput){
            //---- [0],[1]    Up,DN CONTROLS
            //player control
            if(ctrlConfig.uartControls[1][0].isAnalogAxis()){
                int val1 = ucmi.p[1].readAnalogAxis(ctrlConfig.uartControls[1][0]);  //0 to 255
                val1 = val1 - 128;
                double percentage = val1;  //(val1/100)*100;      //i made maximum analog stick 100 (instead of 127)
                if(percentage > 0){
                    /*if(percentage>100){
                    percentage = 100;
                    }*/
                    moveUp(percentage);
                } else if (percentage < 0){
                    percentage = -percentage;
                    /*if(percentage>100){
                    percentage = 100;
                    }*/
                    moveDn(percentage);
                }
            } else {
                if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][0])){
                    moveUp();
                }
                if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][1])){
                    moveDn();
                }
                
            }
            
            //---- [2],[3]    LF,RT CONTROLS
            //player control ("LEFT" and "RIGHT") ==> index [2] and [3]
            if(ctrlConfig.uartControls[1][2].isAnalogAxis()){
                int val1 = ucmi.p[1].readAnalogAxis(ctrlConfig.uartControls[1][2]);  //0 to 255
                val1 = val1 - 128;
                double percentage = val1;  //(val1/100)*100;      //i made maximum analog stick 100 (instead of 127)
                if(percentage > 0){
                    /*if(percentage>100){
                    percentage = 100;
                    }*/
                    moveRt(percentage);
                } else if (percentage < 0){
                    percentage = -percentage;
                    /*if(percentage>100){
                    percentage = 100;
                    }*/
                    moveLf(percentage);
                }
            } else {
                if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][2])){
                    moveRt();
                }
                if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][3])){
                    moveLf();
                }
                
            }
            
            
            //---- [4] Fire
            if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][4])){
                shoot();
            }
            
            //---- [5] ConSett [NO repeat press on hold]
            if(ucmi.p[1].readButton(ctrlConfig.uartControls[1][5])){
                switch (ctrlConfig.uartHolddownWaitCount[1][5]){
                    case ControlConfig.UART_HOLD_DOWN_WAIT_TIME:
                        //actQuit();   [NO repeat press on hold]
                        break;
                    case 0:
                        actConSettings();
                        ctrlConfig.uartHolddownWaitCount[1][5]++;
                        break;
                    default:
                        ctrlConfig.uartHolddownWaitCount[1][5]++;
                        break;
                }
            } else {ctrlConfig.uartHolddownWaitCount[1][5]=0;}
        }
    }
    
    

    private void uartLoopExitResetHoldDown() {
        //////////////////////// UCMI YEAH
        //since by this point is outside read loop
        ctrlConfig.uartHolddownWaitCount[1][5]=0;
    }
    
    
    
    
    
    
    
    
    
    
    /////////// MAIN
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
