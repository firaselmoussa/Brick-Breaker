/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brick.breaker;

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
    
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    
    private Timer time;
    private int delay = 8;
    
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 250;
    private int ballXdir = -1;
    private int ballYdir = -2;


    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    
}
