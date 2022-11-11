/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brick.breaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Firas
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener{
    
//    GAME VARIABLES
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    
    private Timer timer;
    private int delay = 8;
    
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 250;
    private int ballXdir = -1;
    private int ballYdir = -2;
    
//    GAME CONSTRUCTOR
    public Gameplay(){
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        
    }

//    GAME GRAPHICS
    public void paint(Graphics g){
        
//      BACKGROUND
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
//      BORDERS
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
//      THE PADDLE
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        
//      THE BALL
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        g.dispose();
        
    }

    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        
        timer.start();
        
        if(play){
            ballposX += ballXdir;
            ballposY += ballYdir;
            
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            
            if(ballposX < 670){
                ballXdir = -ballXdir;
            }
            
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
        }
        
        
        
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    
//    DETECTING KEY CLICKS
    public void keyPressed(KeyEvent e) {
//        DETECTING RIGHT KEY CLICK
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
            
        }
        
//        DETECTING LEFT KEY CLICK
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
            
        }
        
    }
//  PLAYER MOVES METHODS
    public void moveRight(){
        play = true;
        playerX +=20;
    }
    
    public void moveLeft(){
        play = true;
        playerX -=20;
    }

    
    
    
}
