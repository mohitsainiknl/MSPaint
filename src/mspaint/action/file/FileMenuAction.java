package mspaint.action.file;

import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mspaint.action.MainViewAction;
import mspaint.gui.GUIHandler;
import mspaint.gui.menubar.FilePopupMenu;
import mspaint.helper.ImageFilter;
import mspaint.helper.TButton;

/**
 * FileMenuAction
 */
public class FileMenuAction implements ActionListener {
    private GUIHandler gui;
    private FilePopupMenu menu;

    public FileMenuAction(GUIHandler gui, FilePopupMenu menu) {
        this.gui = gui;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TButton button = (TButton)e.getSource();

        if(menu.isShowing()) {
            menu.setVisible(false);
        }

        if(button.getText().equals("New")) {
            if(gui.isSelectionActive) {
                gui.viewAction.drawImageSelected();
            }
            MainViewAction.clearImage(gui.mainImage);
            MainViewAction.clearImage(gui.tempImage);
        }
        else if(button.getText().equals("Open")) {

        }
        else if(button.getText().equals("Save")) {
            saveImage();
        }
        else if(button.getText().equals("Save as")) {
            saveImage();
        }
        else if(button.getText().equals("print")) {
            PrinterJob pj = PrinterJob.getPrinterJob();
            if (pj.printDialog()) {
              try {
                pj.print();
              } catch (PrinterException e2) {
                System.out.println(e2);
              }
            }
        }
        else if(button.getText().equals("Set as Desktop Background")) {

        }
        else if(button.getText().equals("About")) {

        }
        else if(button.getText().equals("Exit")) {
            System.exit(0);
        }


        else if(button.getName().equals("png")) {
            saveImage("image.png", "PNG File", "png");
        }
        else if(button.getName().equals("jpeg")) {
            saveImage("image.jpeg", "JPEG File", "jpeg");
        }
        else if(button.getName().equals("bmp")) {
            saveImage("image.bmp", "BMP File", "bmp");
        }
        else if(button.getName().equals("gif")) {
            saveImage("image.gif", "GIF File", "gif");
        }
        else if(button.getName().equals("other")) {
            saveImage();
        }
    }
    
    private void saveImage(String defaultName, String description, String extension) {
        JFileChooser chooser = new JFileChooser();
        ImageFilter imgFilter = new ImageFilter();
        chooser.addChoosableFileFilter(imgFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        {
            chooser.setFileFilter(new FileNameExtensionFilter(description, extension));
            chooser.setSelectedFile(new File(defaultName));
        }
        int value = chooser.showSaveDialog(gui.frame);
        if(value == JFileChooser.APPROVE_OPTION){
            BufferedImage image;
            {
                image = gui.mainImage;
            }
            File file = chooser.getSelectedFile();
            String ext = imgFilter.getExtension(file);
            System.out.println("File = "+file);
            System.out.println("exten = "+ext);
            try {
                ImageIO.write(image, ext, file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void saveImage() {
        JFileChooser chooser = new JFileChooser();
        ImageFilter imgFilter = new ImageFilter();
        chooser.addChoosableFileFilter(imgFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        {
            chooser.setFileFilter(new FileNameExtensionFilter("BMP File","bmp"));
            chooser.setFileFilter(new FileNameExtensionFilter("JPG File","jpg"));
            chooser.setFileFilter(new FileNameExtensionFilter("JPEG File","jpeg"));
            chooser.setFileFilter(new FileNameExtensionFilter("PNG File","png"));
            chooser.setSelectedFile(new File("image.png"));
        }
        int value = chooser.showSaveDialog(gui.frame);
        if(value == JFileChooser.APPROVE_OPTION){
            BufferedImage image;
            {
                image = gui.mainImage;
            }
            File file = chooser.getSelectedFile();
            String ext = imgFilter.getExtension(file);
            System.out.println("File = "+file);
            System.out.println("exten = "+ext);
            try {
                ImageIO.write(image, ext, file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}