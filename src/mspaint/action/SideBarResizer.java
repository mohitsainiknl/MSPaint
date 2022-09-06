package mspaint.action;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import mspaint.initial_info.InitialValues;

/**
 * SideBarResizer
 */
public class SideBarResizer implements MouseMotionListener {
    private JPanel sidePanel;
    private int originX;

    public SideBarResizer(JPanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        InitialValues.sidePanelWith = InitialValues.sidePanelWith - (x - originX);
        sidePanel.setPreferredSize(new Dimension(InitialValues.sidePanelWith, sidePanel.getHeight()));
        sidePanel.updateUI();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        originX = e.getX();
    }
}
