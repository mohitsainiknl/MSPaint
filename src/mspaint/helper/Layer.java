package mspaint.helper;

import java.awt.image.BufferedImage;

public class Layer {
    public BufferedImage image;
    public boolean isVisible;

    public Layer(BufferedImage image, boolean isVisible) {
        this.image = image;
        this.isVisible = isVisible;
    }
}
