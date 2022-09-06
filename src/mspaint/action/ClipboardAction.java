package mspaint.action;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import mspaint.gui.GUIHandler;
import mspaint.helper.TButton;
import mspaint.helper.ImageFilter;

public class ClipboardAction implements ActionListener {
    private GUIHandler gui;

    public ClipboardAction(GUIHandler gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TButton button = (TButton)e.getSource();

        if(button.getText().equals("Crop")) {
            if(gui.isSelectionActive) {
                if(gui.viewAction.sImage != null) {
                    // gui.setMainImage(gui.viewAction.sImage); //<-- bug
                }
                else {
                    System.out.println("null...");
                }
            }
        }
        else if(button.getText().equals("Cut")) {
            if(gui.isSelectionActive) {
                gui.cpyImage = clone(gui.viewAction.sImage);

                MainViewAction.clearImage(gui.viewAction.sImage);
                MainViewAction.clearImage(gui.tempImage);
                gui.view.repaint();
            }
        }
        else if(button.getText().equals("Copy")) {
            if(gui.isSelectionActive) {
                gui.cpyImage = clone(gui.viewAction.sImage);
            }
        }
        else if(button.getText().equals("Paste")) {
            if(gui.cpyImage != null) {
                gui.viewAction.pasteImage(gui.cpyImage, 100, 100);
            }
        }
        else if(button.getText().equals("Import Paste")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
            
            int value = fileChooser.showOpenDialog(gui.frame);
            if(value == JFileChooser.APPROVE_OPTION){
                gui.homeToolBar.imageTools.rectangularSecItem.doClick();

                File file = fileChooser.getSelectedFile();
                try {
                    gui.cpyImage = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                gui.viewAction.pasteImage(gui.cpyImage);
            }
        }
    }

    public static BufferedImage clone(BufferedImage image) {
        if(image != null) {
            final int w = image.getWidth();
            final int h = image.getHeight();
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = newImage.createGraphics();
            g.drawImage(image, 0, 0, w, h, null, null);
            g.dispose();
            return newImage;
        }
        return null;
    }

}