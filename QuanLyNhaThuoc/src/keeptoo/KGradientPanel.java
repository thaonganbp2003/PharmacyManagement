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
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseMotionListener;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
// PanelRound
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class KGradientPanel extends JPanel {

    public Color kStartColor = Color.MAGENTA;
    public Color kEndColor = Color.BLUE;
    public boolean kTransparentControls = true;
    public int kGradientFocus = 500;

    public Color getkStartColor() {
        return kStartColor;
    }

    public void setkStartColor(Color kStartColor) {
        this.kStartColor = kStartColor;
    }

    public Color getkEndColor() {
        return kEndColor;
    }

    public void setkEndColor(Color kEndColor) {
        this.kEndColor = kEndColor;
    }

    public boolean iskTransparentControls() {
        return kTransparentControls;
    }

    public void setkTransparentControls(boolean kTransparentControls) {
        this.kTransparentControls = kTransparentControls;
    }

    public int getkGradientFocus() {
        return kGradientFocus;
    }

    public void setkGradientFocus(int kGradientFocus) {
        this.kGradientFocus = kGradientFocus;
    }
    // Shape
public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    
    public KGradientPanel() {
        
        if (kTransparentControls) {
            setBg(true);
        } else {
            setBg(false);
        }
        

    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, kStartColor, kGradientFocus, h, kEndColor);    
        g2d.setPaint(gp);
        //shape
        
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        
        g2d.fill(area);
       // g2d.dispose();
    }

    private void setBg(boolean isOpaque) {
        Component[] components = this.getComponents();
        for (Component component : components) {

            ((JLabel) component).setOpaque(isOpaque);
            ((JCheckBox) component).setOpaque(isOpaque);
            ((JTextField) component).setOpaque(isOpaque);
            ((JPasswordField) component).setOpaque(isOpaque);
            ((JFormattedTextField) component).setOpaque(isOpaque);
            ((JToolBar) component).setOpaque(isOpaque);
            ((JRadioButton) component).setOpaque(isOpaque);

        }
    }
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
        
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }     
}



