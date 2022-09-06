package mspaint.action.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mspaint.gui.GUIHandler;

/**
 * ZoomInAction
 */
public class ZoomAction implements ActionListener {
    private GUIHandler gui;
    private String name;

    public ZoomAction(GUIHandler gui, String name) {
        this.gui = gui;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final double scale = gui.view.scale;

        if(name.equalsIgnoreCase("In")) {
            if((scale + 0.25) <= 8.0) {
                gui.view.scale = scale + 0.25;
            }
        }
        else if(name.equalsIgnoreCase("Out")) {

            if((scale - 0.25) >= 0) {
                if((scale - 0.25) != 0) {
                    gui.view.scale = scale - 0.25;
                }
                else {
                    gui.view.scale = 0.12;
                }
            }
        }
        else if(name.equalsIgnoreCase("Default")) {
            gui.view.scale = 1.0;
        }
        gui.zoomSlider.setValue((int) ((gui.view.scale*100) / 25));
        gui.view.resetSize();
        gui.view.updateUI();
    }
}