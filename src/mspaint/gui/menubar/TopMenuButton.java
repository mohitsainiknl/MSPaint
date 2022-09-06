package mspaint.gui.menubar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.swing.JButton;

import mspaint.gui.GUIHandler;
import mspaint.style.Style;
import mspaint.theme.Theme;

public class TopMenuButton extends JButton implements ActionListener{
    private boolean isSelected;
    private Theme theme;
    private String text;
    private GUIHandler gui;
    
    public TopMenuButton(String text, Style style, Theme theme, GUIHandler gui) {
        super(text);
        this.text = text;
        this.theme = theme;
        this.gui = gui;
        setFocusable(false);
        setPreferredSize(new Dimension(58, 23));
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        addActionListener(this);
        setContentAreaFilled(false);
    }

    public void setSelected(boolean b) {
        isSelected = b;
        repaint();
    }

    public boolean getSelected() {
        return isSelected;
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight();
        final Color blueLineColor = new Color(25, 121, 202);

        if(isSelected) {
            g.setColor((text.equals("Text"))? blueLineColor : theme.getBorderColor());
            g.drawRect(0, 0, width-1, height);

            g.setColor(theme.getBgColor());
            g.fillRect(1, 1, width-2, height);
        }
        else if(text.equals("Text")){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setPaint(new GradientPaint(0, 0, blueLineColor, 0, height, Color.WHITE));
            g2d.drawRect(0, 0, width-1, height);
        }
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isSelected) {
            isSelected = true;
            gui.setTopMenuSelection(isSelected, text);
        }
    }
}
