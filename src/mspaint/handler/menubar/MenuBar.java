/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.menubar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import mspaint.handler.Handler;

public class MenuBar extends JPanel {
    public final Handler handler;
    public FileMenu fileMenu;
    public MenuButton homeButton;
    public MenuButton viewButton;
    public MenuButton textButton;
    public boolean isTextLine;

    public MenuBar(Handler handler) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.handler = handler;
        setBackground(handler.theme.menubarBgColor);
        initialWork();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = getWidth();
        final int height = getHeight() - 1;
        final Color color = (isTextLine)? handler.theme.textLineColor : handler.theme.borderColor;
        g.setColor(color);
        g.drawLine(0, height, width, height);
    }

    private void initialWork() {
        fileMenu = new FileMenu(this);
        homeButton = new MenuButton("Home", this);
        viewButton = new MenuButton("View", this);
        textButton = new MenuButton("Text", this);

        JPanel hold = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        hold.setOpaque(false);
        hold.add(viewButton);
        hold.add(textButton);

        add(fileMenu);
        add(homeButton);
        add(hold);

        homeButton.setSelected(true);
        setTopMenuSelection(true, "Home");
    }

    public void setTopMenuSelection(boolean isSelected, String menuName) {
        // handler.toolbar.toolCard.show(handler.toolbar, menuName.toLowerCase());  //<--- Flip the ToolBar Card

        if(menuName.equals("Home")) {
            if(isSelected) {
                viewButton.setSelected(false);
                textButton.setSelected(false);
            }
        }
        else if(menuName.equals("View")) {
            if(isSelected) {
                homeButton.setSelected(false);
                textButton.setSelected(false);
            }
        }
        else if(menuName.equals("Text")) {
            if(isSelected) {
                viewButton.setSelected(false);
                homeButton.setSelected(false);
            }
        }
    }
}
