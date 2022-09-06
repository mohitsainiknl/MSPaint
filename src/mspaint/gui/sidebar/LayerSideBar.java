package mspaint.gui.sidebar;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import mspaint.gui.GUIHandler;
import mspaint.style.Style;
import mspaint.theme.Theme;

public class LayerSideBar extends JPanel {
    private GUIHandler gui;
    public LayerSettings layerSettings;
    public LayersArea layersArea;


    public LayerSideBar(GUIHandler gui, Style style, Theme theme) {
        super(new BorderLayout());
        this.gui = gui;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        layerSettings = new LayerSettings(gui);
        layersArea = new LayersArea(gui);

        add(layerSettings, BorderLayout.NORTH);
        add(layersArea, BorderLayout.CENTER);
    }
}
