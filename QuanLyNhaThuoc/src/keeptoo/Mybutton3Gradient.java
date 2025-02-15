/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package keeptoo;

/**
 *
 * @author THAONGAN
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Mybutton3Gradient extends JButton{

    public Mybutton3Gradient() {
        defaultCursor = this.getCursor();
        handCursor = new Cursor(Cursor.HAND_CURSOR);
        color1=new Color(3, 137, 255);
        color2=new Color(213, 252, 255);
        hoverColor1 = new Color(216,171,255);
        hoverColor2 = new Color(3,137,255);
        setContentAreaFilled(false);
        //add event
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);
               over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);
                over = false;
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
          

        });
    }
    private Color color1;
    private Color color2;
    private Color hoverColor1;
    private Color hoverColor2;
    private boolean over;
    private Cursor defaultCursor;
    private Cursor handCursor;
    public int GradientFocus = 500;
    public int hoverGradientFocus = 225;
    
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }
     public Color gethoverColor1() {
        return hoverColor1;
    }

    public void sethoverColor1(Color hoverColor1) {
        this.hoverColor1 = hoverColor1;
    }

    public Color getHoverColor2() {
        return hoverColor2;
    }

    public void setHoverColor2(Color hoverColor2) {
        this.hoverColor2 = hoverColor2;
    }


    public int getGradientFocus() {
        return GradientFocus;
    }

    public void setGradientFocus(int GradientFocus) {
        this.GradientFocus = GradientFocus;
    }

    public int getHoverGradientFocus() {
        return hoverGradientFocus;
    }

    public void setHoverGradientFocus(int hoverGradientFocus) {
        this.hoverGradientFocus = hoverGradientFocus;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
       
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        GradientPaint gra = new GradientPaint(0, 0, color1, GradientFocus, 0, color2);
        g2d.setPaint(gra);
        g2d.fillRect(0, 0, getWidth(), getHeight());
         if (over) {
        paintPressed(g2d);
        }
        super.paintComponent(g);
    }
     private void paintPressed(Graphics2D g2) {
     
        g2.setColor(getBackground());
        GradientPaint gra = new GradientPaint(0, 0, hoverColor1, hoverGradientFocus, 0, hoverColor2);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
    }
     
}
