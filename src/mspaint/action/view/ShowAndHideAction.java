package mspaint.action.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import mspaint.gui.GUIHandler;

public class ShowAndHideAction implements ActionListener {
    private GUIHandler gui;

    public ShowAndHideAction(GUIHandler gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox checkBox = (JCheckBox) e.getSource();
        final boolean isSelected = checkBox.isSelected();
        String name = checkBox.getText();

        if(name.equals("Gridlines")) {
            if(isSelected) {
                gui.view.showGridLines = true;
            }
            else {
                gui.view.showGridLines = false;
            }
            gui.view.repaint();
        }
        else if(name.equals("Status bar")) {
            if(isSelected) {
                gui.statusbar.setVisible(true);
            }
            else {
                gui.statusbar.setVisible(false);
            }
        }
        else if(name.equals("Rulers")) {
            if(isSelected) {
                gui.scrollpane.setColumnHeaderView(gui.clmRule);
                gui.scrollpane.setRowHeaderView(gui.rowRule);
            }
            else {
                gui.scrollpane.setColumnHeaderView(null);
                gui.scrollpane.setRowHeaderView(null);
            }
        }

    }
    
}
