package mspaint.action;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

import mspaint.gui.GUIHandler;
import mspaint.gui.menubar.SideMenuButton;
import mspaint.helper.DropTools;
import mspaint.helper.TButton;

import java.awt.event.ActionEvent;
/**
 * Action
 */
public class SideBarAction implements ActionListener {

    private SideMenuButton layerButton;
    private SideMenuButton filterButton;
    private JPanel sidePanel;
    private DropTools layersShortcut;
    private GUIHandler handler;

    public SideBarAction(SideMenuButton layerButton, SideMenuButton filterButton, JPanel sidePanel, DropTools layersShortcut, GUIHandler handler) {
        this.layerButton = layerButton;
        this.filterButton = filterButton;
        this.sidePanel = sidePanel;
        this.layersShortcut = layersShortcut;
        this.handler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TButton button = (TButton) e.getSource();
        final boolean isSelected = !button.isSelected();

        if(button.getName().equals("layer")) {
            if(isSelected) {
                if(!layerButton.isVisible()) {
                    layerButton.setVisible(true);
                    layersShortcut.setLeftSideArrow(false);
                    layersShortcut.repaint();
                }
                if(!sidePanel.isVisible()) {
                    sidePanel.setVisible(true);
                    layerButton.setSelected(true);
                    handler.setSideMenuSelection(true, "Layer");
                }
            }
            else {
                if(layerButton.isVisible()) {
                    layerButton.setVisible(false);
                    if(filterButton.isVisible()) {
                        filterButton.setSelected(true);
                        handler.setSideMenuSelection(true, "Filter");
                    }
                    layersShortcut.setLeftSideArrow(true);
                    layersShortcut.repaint();
                }
                if(!filterButton.isVisible() && sidePanel.isVisible()) {
                    sidePanel.setVisible(false);
                }
            }
        }
        else if(button.getName().equals("filter")) {
            if(isSelected) {
                if(!filterButton.isVisible()) {
                    filterButton.setVisible(true);
                }
                if(!sidePanel.isVisible()) {
                    sidePanel.setVisible(true);
                    filterButton.setSelected(true);
                    handler.setSideMenuSelection(true, "Filter");
                }
            }
            else {
                if(filterButton.isVisible()) {
                    filterButton.setVisible(false);
                    if(layerButton.isVisible()) {
                        layerButton.setSelected(true);
                        handler.setSideMenuSelection(true, "Layer");
                    }
                }
                if(!layerButton.isVisible() && sidePanel.isVisible()) {
                    sidePanel.setVisible(false);
                }
            }
        }
    }
}
