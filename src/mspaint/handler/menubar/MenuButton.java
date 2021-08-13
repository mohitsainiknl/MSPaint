package mspaint.handler.menubar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;

import mspaint.values.Theme;

public class MenuButton extends JButton implements ActionListener {
    public MenuBar menuBar;
    private String text;
    private boolean isSelected;
    
    public MenuButton(String text, MenuBar menuBar) {
        super(text);
        this.text = text;
        this.menuBar = menuBar;
        setFocusable(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(58, 23));
        setMargin(new Insets(0, 0, 0, 0));
        setOpaque(false);
        addActionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight();
        final Theme theme = menuBar.handler.theme;

        if(isSelected) {
            final Color color;
            if(text.equals("Text")) {
                color = theme.textLineColor;
                menuBar.isTextLine = true;
                menuBar.repaint();
            }
            else {
                color = theme.borderColor;
            }
            g.setColor(color);
            g.drawRect(0, 0, width-1, height+1);
            
            g.setColor(theme.toolbarBgColor);
            g.fillRect(1, 1, width-2, height);
        }
        else if(text.equals("Text")){
            menuBar.isTextLine = false;
            menuBar.repaint();
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setPaint(new GradientPaint(0, 0, theme.textLineColor, 0, height-2, theme.menubarBgColor));
            g2d.drawRect(0, 0, width-1, height);

            g.setColor(theme.borderColor);
            g.drawLine(0, height-1, width-1, height-1);
        }
        super.paintComponent(g);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        repaint();
    }

    public boolean getSelected() {
        return isSelected;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isSelected) {
            isSelected = true;
            menuBar.setTopMenuSelection(isSelected, text);
        }
    }
}
