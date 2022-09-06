package mspaint.helper;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceLoader {
    static ResourceLoader rl = new ResourceLoader();

    public static Image getImage(String fileName) {
        Image image = null;
        try {
//            System.out.println(rl.getClass().getClassLoader().getResource(fileName) + "  <---" + fileName);
            image =  ImageIO.read(rl.getClass().getResource("/" + fileName));
        }
        catch (Exception e) {
            try {
                 image =  ImageIO.read(new File("res/" + fileName));
            } catch (Exception err) {
                System.out.println("Image not found!");
            }
        }
        return image;
    }

    public static ImageIcon getImageIcon(String fileName) {
        return new ImageIcon(getImage(fileName));
    }
}
