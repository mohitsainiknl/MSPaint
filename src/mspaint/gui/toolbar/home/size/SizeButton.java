package mspaint.gui.toolbar.home.size;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import mspaint.helper.TButton;

public class SizeButton extends TButton {
    private int size;

    public SizeButton(int size) {
        super("", null, TButton.Type.SELECTABLE);
        this.size = size;
        setPreferredSize(new Dimension(128, 40));
    }

    public int getStrokeSize() {
        return size;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = getWidth();
        final int height = getHeight();
        final int sideGap = 7;
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(sideGap, height/2, width-sideGap, height/2);
    }
}
