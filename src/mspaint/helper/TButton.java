package mspaint.helper;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TButton extends JButton implements MouseListener, ActionListener {
    private static final Color SELECTED_BG_COLOR = new Color(201, 224, 247);
    private static final Color SELECTED_BORDER_COLOR = new Color(98, 162, 228);
    private static final Color HOVER_BG_COLOR = new Color(232, 239, 247);
    private static final Color HOVER_BORDER_COLOR = new Color(164, 206, 249);
    private ImageIcon icon;
    private ImageIcon bigIcon;
    private String buttonText = "";
    private Cursor cursor;
    protected Type type;
    protected boolean isSelected;
    protected boolean isEnabled = true;

    protected boolean mouseEntered;
    protected boolean mousePressed;
    protected boolean paintHoverBorder = true;
    
    public TButton(Type type) {
        this("", null, type);
    }

    public TButton(String text) {
        this(text, null, Type.NOT_SELECTABLE);
    }
    
    public TButton(String text, Type type) {
        this(text, null, type);
    }

    public TButton(ImageIcon icon, Type type) {
        this("", icon, type);
    }

    public TButton(String text, ImageIcon icon, Type type) {
        super(text, icon);
        this.icon = icon;
        this.type = type;
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(false);
        setMargin(new Insets(3, 5, 3, 5));
        setHorizontalAlignment(JButton.LEFT);
        addMouseListener(this);
        addActionListener(this);
        setContentAreaFilled(false);
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        if(isSelected == false) {
            mouseEntered = false;
            mousePressed = false;
        }
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setTEnabled(boolean enabled) {
        if(isEnabled != enabled) {
            isEnabled = enabled;
            if(isEnabled == false) {
                setIcon(getGrayScaleImage(icon));
            }
            else {
                setIcon(icon);
            }
        }
    }

    public boolean isTEnabled() {
        return isEnabled;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        this.setToolTipText(buttonText);
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setPaintHoverBorder(boolean b) {
        paintHoverBorder = b;
    }

    public void setBigIcon(ImageIcon bigIcon) {
        this.bigIcon = bigIcon;
    }

    public ImageIcon getBigIcon() {
        return bigIcon;
    }

    public TButton.Type getType() {
        return type;
    }

    public void setViewCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public static Cursor getCursor(String location, Point point) {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Image image = (new ImageIcon(location)).getImage();
        return toolkit.createCustomCursor(image, new java.awt.Point(point.x, point.y), "tool");
    }

    public void setViewCursor(String location, Point point) {
        this.cursor = getCursor(location, point);
    }

    public static Cursor getCursor(Image img, Point point) {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Image image = (new ImageIcon(img)).getImage();
        return toolkit.createCustomCursor(image, new java.awt.Point(point.x, point.y), "tool");
    }

    public void setViewCursor(Image img, Point point) {
        this.cursor = getCursor(img, point);
    }

    public Cursor getViewCursor() {
        return cursor;
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight();
        if(isEnabled) {
            if(!isSelected && mouseEntered) {
                g.setColor(HOVER_BG_COLOR);
                g.fillRect(1, 1, width-2, height-2);
                
                if(paintHoverBorder) {
                    g.setColor(HOVER_BORDER_COLOR);
                    g.drawRect(0, 0, width-1, height-1);
                }
            }
    
            if((type != Type.NOT_SELECTABLE && isSelected) || mousePressed) {
                g.setColor(SELECTED_BG_COLOR);
                g.fillRect(1, 1, width-2, height-2);
    
                if(paintHoverBorder) {
                    g.setColor(SELECTED_BORDER_COLOR);
                    g.drawRect(0, 0, width-1, height-1);
                }
            }
        }
        super.paintComponent(g);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(isEnabled) {
            mouseEntered = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(isEnabled) {
            mouseEntered = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isEnabled) {
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isEnabled) {
            mousePressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isEnabled) {
            if(type != Type.NOT_SELECTABLE && !isSelected) {
                setSelected(true);
            }
            else if(type == Type.SELECTABLE_AND_UNSELECTABLE && isSelected) {
                setSelected(false);
            }
        }
    }







    public static Icon getGrayScaleImage(ImageIcon icon) {
        final int width = icon.getIconWidth();
        final int height = icon.getIconHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();

        image = processImageIntoGrayScale(image);
        return new ImageIcon(image);
    }

    private static BufferedImage processImageIntoGrayScale(BufferedImage image) {
        // I am unable to process png image into gray scale image with transparency. 

        /*
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);// BufferedImage.TYPE_BYTE_GRAY specifies that this is a grayscale picture 
        for(int i= 0 ; i < width ; i++){  
                for(int j = 0 ; j < height; j++){  
                    int rgb = image.getRGB(i, j);  
                    grayImage.setRGB(i, j, rgb);  
            }  
        } 
        */

        return image;
    }

    /**
     * Type
     */
    public enum Type {
        SELECTABLE,
        NOT_SELECTABLE,
        SELECTABLE_AND_UNSELECTABLE
    }
}
