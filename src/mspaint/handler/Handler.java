/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler;

import javax.swing.JFrame;
import javax.swing.UIManager;

import mspaint.values.Theme;
import mspaint.values.InitValues;

/**
 * GUIHandler class is on the top of all the application handler class.
 * This class also handles the basic layout of this app.
 */
public class Handler {
    private JFrame frame;
    public final Theme theme;
    public final InitValues initValues;

    public Handler(Theme theme, InitValues initValues) {
        this.theme = theme;
        this.initValues = initValues;
        UIManager.put("Button.font", initValues.font);
        UIManager.put("Label.font", initValues.font);
    }

    public void handle() {
        frame = new JFrame("MSPaint");

        frame.setSize(initValues.frameSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}