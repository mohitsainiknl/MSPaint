package mspaint.helper;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";
    public final static String PNG = "png";
    public final static String BMP = "bmp";
    
    @Override
    public boolean accept(File f) {
       if (f.isDirectory()) {
          return true;
       }
 
       String extension = getExtension(f);
       if (extension != null) {
          if (extension.equals(TIFF) ||
             extension.equals(TIF) ||
             extension.equals(GIF) ||
             extension.equals(JPEG) ||
             extension.equals(JPG) ||
             extension.equals(BMP) ||
             extension.equals(PNG)) {
             return true;
          } else {
             return false;
          }
       }
       return false;
    }
 
    @Override
    public String getDescription() {
       return "Image Only";
    }
 
    public String getExtension(File f) {
       String ext = null;
       String s = f.getName();
       int i = s.lastIndexOf('.');
    
       if (i > 0 &&  i < s.length() - 1) {
          ext = s.substring(i+1).toLowerCase();
       }
       return ext;
    } 
 }