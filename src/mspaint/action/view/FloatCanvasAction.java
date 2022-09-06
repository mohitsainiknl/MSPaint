package mspaint.action.view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mspaint.gui.GUIHandler;
import mspaint.helper.TButton;

public class FloatCanvasAction implements ActionListener {
    private GUIHandler gui;

    public FloatCanvasAction(GUIHandler gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((TButton) e.getSource()).isSelected()) {
            gui.imageViewPort.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        }
        else {
            gui.imageViewPort.setLayout(new GridBagLayout());
        }
        gui.scrollpane.updateUI();
    }
    
}
