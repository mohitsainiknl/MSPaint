package mspaint.helper;

import java.awt.TexturePaint;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Texture {
    
    public static TexturePaint getTransparentPaint() {
        final int size = 20;
        return getGridLinePaint(size);
    }
    
    public static TexturePaint getTransparentPaint(final int size) {
        final Color DARK_BLOCK_COLOR = new Color(238, 238, 238);
        final Color LIGHT_BLOCK_COLOR = new Color(255, 255, 255);

        BufferedImage patternImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D pattren = patternImage.createGraphics();
        Rectangle r = new Rectangle(0, 0, size, size);

        pattren.setColor(LIGHT_BLOCK_COLOR);

        pattren.fillRect(0, 0, size/2, size/2);
        pattren.fillRect(size/2, size/2, size, size);

        pattren.setColor(DARK_BLOCK_COLOR);

        pattren.fillRect(size/2, 0, size, size/2);
        pattren.fillRect(0, size/2, size/2, size);

        return new TexturePaint(patternImage, r);
    }

    public static TexturePaint getGridLinePaint(final int size) {
        final Color DARK_BLOCK_COLOR = new Color(127, 127, 127);

        BufferedImage patternImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D pattren = patternImage.createGraphics();
        Rectangle r = new Rectangle(0, 0, size, size);

        pattren.setPaint(getSelectionPaint(2, DARK_BLOCK_COLOR));
        pattren.drawLine(0, 0, 0, size);
        pattren.drawLine(0, 0, size, 0);

        return new TexturePaint(patternImage, r);
    }

    public static TexturePaint getSelectionPaint() {
        final Color DARK_BLOCK_COLOR = new Color(0, 120, 215);
        final int size = 10;
        return getSelectionPaint(size, DARK_BLOCK_COLOR);
    }

    public static TexturePaint getSelectionPaint(final int size, final Color darkBlockColor) {
        final Color LIGHT_BLOCK_COLOR = new Color(255, 255, 255);

        BufferedImage patternImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D pattren = patternImage.createGraphics();
        Rectangle r = new Rectangle(0, 0, size, size);

        pattren.setColor(LIGHT_BLOCK_COLOR);

        pattren.fillRect(0, 0, size/2, size/2);
        pattren.fillRect(size/2, size/2, size, size);

        pattren.setColor(darkBlockColor);

        pattren.fillRect(size/2, 0, size, size/2);
        pattren.fillRect(0, size/2, size/2, size);

        return new TexturePaint(patternImage, r);
    }

}
