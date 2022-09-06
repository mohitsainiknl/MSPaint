package mspaint.action.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JDialog;

import mspaint.gui.GUIHandler;

public class FullScreenAction implements ActionListener {
    private GUIHandler gui;
    private JDialog dialog;

    public FullScreenAction(GUIHandler gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog = new JDialog(gui.frame);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setSize(size);
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);
        dialog.setLayout(new BorderLayout());
        dialog.add(gui.viewport, BorderLayout.CENTER);
        dialog.setVisible(true);
        gui.viewport.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
        });
    }

}
