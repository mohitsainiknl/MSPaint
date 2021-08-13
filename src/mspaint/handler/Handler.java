/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import mspaint.values.Theme;
import mspaint.handler.menubar.MenuBar;
import mspaint.handler.sidebar.SideBar;
import mspaint.handler.statusbar.StatusBar;
import mspaint.handler.toolbar.ToolBar;
import mspaint.handler.viewport.Viewport;
import mspaint.values.InitValues;

/**
 * GUIHandler class is on the top of all the application handler class.
 * This class also handles the basic layout of this app.
 */
public class Handler {
    private JFrame frame;
    public final Theme theme;
    public final InitValues initValues;

    public MenuBar menuBar;
    public ToolBar toolBar;
    public SideBar sideBar;
    public Viewport viewport;
    public StatusBar statusBar;

    public Handler(Theme theme, InitValues initValues) {
        this.theme = theme;
        this.initValues = initValues;
        UIManager.put("Button.font", initValues.font);
        UIManager.put("Label.font", initValues.font);
    }

    public void handle() {
        menuBar = new MenuBar(this);
        toolBar = new ToolBar(this);
        sideBar = new SideBar(this);
        viewport = new Viewport(this);
        statusBar = new StatusBar(this);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(toolBar, BorderLayout.CENTER);
        
        frame = new JFrame("MSPaint");
        {
            frame.getContentPane().add(topPanel, BorderLayout.NORTH);
            frame.getContentPane().add(viewport, BorderLayout.CENTER);
            frame.getContentPane().add(sideBar, BorderLayout.EAST);
            frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
        }
        frame.setSize(initValues.frameSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}