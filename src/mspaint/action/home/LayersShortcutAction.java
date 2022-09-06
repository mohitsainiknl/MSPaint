package mspaint.action.home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mspaint.helper.TButton;


public class LayersShortcutAction implements ActionListener {

    private TButton layers;

    public LayersShortcutAction(TButton layers) {
        this.layers = layers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        layers.doClick();
    }
}
