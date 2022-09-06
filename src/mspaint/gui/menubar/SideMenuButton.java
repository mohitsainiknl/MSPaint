package mspaint.gui.menubar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

import javax.swing.JButton;

import mspaint.gui.GUIHandler;
import mspaint.style.Style;
import mspaint.theme.Theme;

public class SideMenuButton extends JButton implements ActionListener {

    private boolean isSelected;
    private Theme theme;
    private String text;
    private GUIHandler gui;
    
    public SideMenuButton(String text, Style style, Theme theme, GUIHandler gui) {
        super(text+"s");
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

        if(isSelected) {
            g.setColor(theme.getBorderColor());
            g.drawRect(0, 0, width-1, height);

            g.setColor(theme.getBgColor());
            g.fillRect(1, 0, width-2, height);
        }
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isSelected) {
            isSelected = true;
            gui.setSideMenuSelection(isSelected, text);
        }
    }
}
