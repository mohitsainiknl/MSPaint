package mspaint.helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * A toolbar-specific separator. An object with dimension but
 * no contents used to divide buttons on a tool bar into groups.
 */
public class TSeparator extends JPanel {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private Color color;
    private int width;
    private int height;
    private int gap;
    private int orientation;

    public TSeparator(int width, int height, int gap, Color color, int orientation) {
        super();
        this.width = width;
        this.height = height;
        this.gap = gap;
        this.color = color;
        this.orientation = orientation;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        if(orientation == VERTICAL) {
            g.drawLine(width/2, gap, width/2, height-gap);
        }
        else if(orientation == HORIZONTAL) {
            g.drawLine(gap, height/2, width-gap, height/2);
        }
    }
}
