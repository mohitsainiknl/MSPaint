/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint;

import javax.swing.UIManager;

import mspaint.handler.Handler;
import mspaint.values.InitValues;
import mspaint.values.Theme;

/**
 * MainLauncher class is the stating point of this MSPaint application.
 * It first try to load the System Look and Feel.
 */
public class MainLauncher {
    final Handler handler;
    final Theme theme;
    final InitValues initValues;

    private MainLauncher() {
        theme = new Theme();
        initValues = new InitValues();
        handler = new Handler(theme, initValues);
        handler.handle();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        new MainLauncher();
    }
}