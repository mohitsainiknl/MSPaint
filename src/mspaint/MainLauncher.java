package mspaint;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mspaint.gui.GUIHandler;
import mspaint.style.DefaultStyle;
import mspaint.style.Style;
import mspaint.theme.DefaultTheme;
import mspaint.theme.Theme;

/**
 * MainLauncher
 */
public class MainLauncher {
    GUIHandler gui;
    Style style;
    Theme theme;

    private MainLauncher() {
        style = new DefaultStyle();
        theme = new DefaultTheme();
        gui = new GUIHandler(style, theme);
        gui.handle();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
        }
        new MainLauncher();
    }
}