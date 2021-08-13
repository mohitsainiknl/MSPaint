/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.menubar;

import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JButton;

public class FileMenu extends JButton {
    public MenuBar menuBar;
    
    public FileMenu(MenuBar menuBar) {
        super("File");
        this.menuBar = menuBar;
        setPreferredSize(new Dimension(58, 23));
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(menuBar.handler.theme.fileMenuFgColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight() - 1;
        g.setColor(menuBar.handler.theme.fileMenuBgColor);
        g.fillRect(0, 0, width, height);
        super.paintComponent(g);
    }
}
