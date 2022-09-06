package mspaint.helper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import mspaint.gui.GUIHandler;
import mspaint.gui.sidebar.LayersArea;

public class View extends JLabel {
    private BufferedImage image;
    private GUIHandler gui;
    private BufferedImage tempImage;
    private BufferedImage shapeImage;
    public boolean showGridLines;
    public double scale = 1.0;


    public View(GUIHandler gui) {
        super();
        this.gui = gui;
        this.image = gui.layers.get(0).image;
        this.tempImage = gui.tempImage;
        this.shapeImage = gui.shapeImage;
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }
    
    public void resetSize() {
        final int width = (int)(image.getWidth() * this.scale);
        final int height = (int)(image.getHeight() * this.scale);
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D back = (Graphics2D) g.create();
        final int width = getWidth();
        final int height = getHeight();
        {
            back.setPaint(Texture.getTransparentPaint(20));
            back.fillRect(0, 0, width, height);
        }
        {
            for(int i = 0; i < gui.layers.size(); i++) {
                if(gui.layers.get(i).isVisible) {
                    back.drawImage(gui.layers.get(i).image, 0, 0, width, height, null, null);
                }
            }
        }
        back.drawImage(tempImage, 0, 0, width, height, null, null);
        back.drawImage(shapeImage, 0, 0, width, height, null, null);
        if(showGridLines) {
            back.setPaint(Texture.getGridLinePaint((int) (10 * this.scale)));
            back.fillRect(0, 0, width, height);
        }
        gui.layerSideBar.layersArea.repaintLayerButton(LayersArea.activeLayer);
        back.dispose();
    }
}
