/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brick.breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    
    private MapGenerator map;
    
//    GAME CONSTRUCTOR
    public Gameplay(){
        
//        CONSTRUCT THE MAP
        map = new MapGenerator(3, 7);

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
        
//      DRAW THE MAP
        map.draw((Graphics2D) g);
                 
//      BORDERS
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
//      SCORE
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score: "+score, 550, 30);
        
//      THE PADDLE
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        
//      THE BALL
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! Score: "+score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("press 'Enter' to restart", 200, 350);
        }
        
        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over! Score: "+score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("press 'Enter' to restart", 200, 350);
        }
        
        g.dispose();
        
    }

    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        
        timer.start();
        
        if(play){
            
//          DETECTING BALL X PADDLE COLLISION
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))){
                
                ballYdir = -ballYdir;
		ballXdir = -2;
                
            }else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))){
                
		ballYdir = -ballYdir;
		ballXdir = ballXdir + 1;
                
            }else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))){
            
                ballYdir = -ballYdir;
            }
            
//          DETECTING BALL X BRICKS COLLISION
            A: for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickWidth + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBricksValue(0, i, j);
                            totalBricks --;
                            score += 5;
                            
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            
                            break A;
                        }
                    }
                }
                
            }
            
//          MOVING THE BALL
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
  
//      DETECTING ENTER KEY CLICK
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 250;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);
                
                repaint();
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
