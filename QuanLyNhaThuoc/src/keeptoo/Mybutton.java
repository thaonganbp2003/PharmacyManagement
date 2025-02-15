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

public class Mybutton extends JButton{

    public Mybutton() {
        defaultCursor = this.getCursor();
        handCursor = new Cursor(Cursor.HAND_CURSOR);
        setColor(Color.WHITE);
        color1=new Color(3, 137, 255);
        color2=new Color(213, 252, 255);
        borderColor=new Color(0, 255, 202);
        setContentAreaFilled(false);
        //add event
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(handCursor);
                over=true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(defaultCursor);
                over=false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
          

        });
    }
    private boolean over;
    private Color color;
    private Color color1;
    private Color color2;
    private Color borderColor;
    private int radius=0;
    private Cursor defaultCursor;
    private Cursor handCursor;

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

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

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    ///test
    private Color backgroundColor;
    public Color getBackgroundColor(){
    
        return backgroundColor;
    }
    public void setBackgroundColor(Color c){
    
        this.backgroundColor = c;
    }
    public int kGradientFocus = 500;
    public int getkGradientFocus() {
        return kGradientFocus;
    }

    public void setkGradientFocus(int kGradientFocus) {
        this.kGradientFocus = kGradientFocus;
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //paint border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //border set 2px
        g2.fillRoundRect(0, 0, getWidth(), getHeight()-4, radius, radius);
        
        // paint background
        if (over) {
        paintPressed(g2);
        }
        super.paintComponent(g);
    }
     
     private void paintPressed(Graphics2D g2) {
     
        g2.setColor(backgroundColor);
        GradientPaint gra = new GradientPaint(0, 0, color1, kGradientFocus, 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight()-4, radius,radius);
        
    }
}
