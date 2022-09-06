package mspaint.gui.toolbar.home;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;
import com.pump.swing.JColorPicker;

import mspaint.gui.GUIHandler;
import mspaint.helper.GroupTButtons;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

public class ColorPlateTool extends GridBagPanel {
    private final JLabel label = new JLabel("Colors");
    public ColorButton color1Button;
    public ColorButton color2Button;

    private GridBagPanel colorPanel;
    private Color[] color;
    private ColorPlateButton[] button;

    private TButton colorChooser;
    private GUIHandler gui;
    
    public ColorPlateTool(GUIHandler gui) {
        super();
        this.gui = gui;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setFill(GridBagPanel.VERTICAL);
        setupActiveColor();
        setupColorPanel();
        setColorChooser();

        setInsets(7, 7, 5, 7);
        add(color1Button, 0, 0);
        add(color2Button, 1, 0);
        setInsets(0, 0, 0, 0);
        add(colorPanel, 2, 0);
        add(colorChooser, 3, 0);
        add(label, 0, 2, 4, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 0, 1, 3, 1, 1.0, 1.0);
        }
    }

    private void setupActiveColor() {
        String text = "<HTML><p style=\"text-align:center;\">Color<br>1</p>";
        color1Button = new ColorButton(text, Color.BLACK);
        gui.activeColor1 = color1Button.getColor();
        color1Button.setSelected(true);

        text = "<HTML><p style=\"text-align:center;\">Color<br>2</p>";
        color2Button = new ColorButton(text, Color.WHITE);
        gui.activeColor2 = color2Button.getColor();

        GroupTButtons colorGroup = new GroupTButtons();
        colorGroup.add(color1Button);
        colorGroup.add(color2Button);
    }

    private void setupColorPanel() {
        color = new Color[30];
        color[0] = Color.BLACK;
        color[1] = Color.GRAY;
        color[2] = new Color(136, 0, 21);
        color[3] = Color.RED;
        color[4] = new Color(255, 127, 39);

        color[5] = Color.YELLOW;
        color[6] = Color.GREEN;
        color[7] = new Color(0, 162, 232);
        color[8] = new Color(63, 72, 204);
        color[9] = new Color(163, 74, 164);

        color[10] = Color.WHITE;
        color[11] = Color.LIGHT_GRAY;
        color[13] = new Color(185, 122, 87);
        color[16] = new Color(252, 158, 160);
        color[14] = new Color(255, 201, 14);
        
        color[15] = new Color(239, 228, 176);
        color[17] = new Color(181, 230, 29);
        color[12] = Color.CYAN;
        color[18] = new Color(112, 146, 190);
        color[19] = new Color(200, 191, 230);

        button = new ColorPlateButton[30];
        for (int i = 0; i < button.length; i++) {
            final int num = i;
            button[i] = new ColorPlateButton(i) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    final int width = getWidth();
                    final int height = getHeight();
                    if(color[num] != null) {
                        final int gap = 2;
                        g.setColor(color[num]);
                        g.fillRect(gap, gap, width-2*gap, height-2*gap);
                    }
                }
            };
        }
        colorPanel = new GridBagPanel(new Insets(1, 1, 1, 1));
        colorPanel.setOpaque(false);
        int i = 0;
        for (int row = 0; row < 3; row++) {
            for (int colm = 0; colm < 10; colm++) {
                colorPanel.add(button[i], colm, row);
                ++i;
            }
        }
    }

    private void setColorChooser() {
        String text = "<HTML><p style=\"text-align:center;\">Edit<br>Color</p>";
        colorChooser = new TButton(text, ResourceLoader.getImageIcon("toolbar/color_chooser_tool.png"), TButton.Type.NOT_SELECTABLE);
        colorChooser.setMargin(new Insets(2, 5, 2, 5));
        colorChooser.setVerticalTextPosition(SwingConstants.BOTTOM);
        colorChooser.setHorizontalTextPosition(SwingConstants.CENTER);
        colorChooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final boolean color1Selected = color1Button.isSelected();
                final Color initialColor = (color1Selected)? color1Button.color : color2Button.color;

                // Color c = JColorChooser.showDialog(null, "Edit Color", activeColor, false);
                Color c = JColorPicker.showDialog(null, "Edit Color", initialColor, false);
                if(c != null) {
                    if(color1Selected) {
                        color1Button.setColor(c);
                        color1Button.repaint();
                        gui.activeColor1 = c;
                    }
                    else {
                        color2Button.setColor(c);
                        color2Button.repaint();
                        gui.activeColor2 = c;
                    }
                }
            }
        });
    }

    private ImageIcon getColorButtonImage() {
        BufferedImage colorBImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcolor = colorBImage.createGraphics();
        final int imgWidth = colorBImage.getWidth();
        final int imgHeight = colorBImage.getHeight();
        gcolor.setColor(Color.BLACK);
        gcolor.drawRect(0, 0, imgWidth-1, imgHeight-1);

        return new ImageIcon(colorBImage);
    }



    /**
     * ColorButton
     */
    public class ColorButton extends TButton {
        protected Color color;
        private int imageHeight;
        private int imageWidth;

        public ColorButton(String text, Color color) {
            super(text, getColorButtonImage(), TButton.Type.SELECTABLE);
            this.color = color;
            ImageIcon image = getColorButtonImage();
            imageWidth = image.getIconWidth();
            imageHeight = image.getIconHeight();
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setHorizontalTextPosition(SwingConstants.CENTER);
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            final int startX = 9;
            final int startY = 7;
            g.setColor(color);
            g.fillRect(startX, startY, imageWidth-4, imageHeight-4);
        }
    }


    protected class ColorPlateButton extends JButton implements MouseListener {
        private final Color BORDER_COLOR = new Color(100, 165, 231);
        private final Color BACK_COLOR = new Color(203, 228, 253);
        private final int width = 20;
        private final int height = 20;
        private BufferedImage backImg;  
        private int colorIndex;
        private boolean isEntered;

        public ColorPlateButton(int colorIndex) {
            super();
            this.colorIndex = colorIndex;
            setFocusable(false);
            setBorderPainted(false);
            setPreferredSize(new Dimension(width, height));
            backImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics gImg = backImg.createGraphics();
            gImg.setColor(new Color(160, 160, 160));
            gImg.drawRect(0, 0, width-1, height-1);
            gImg.setColor(Color.WHITE);
            gImg.fillRect(1, 1, width-2, height-2);
            setMargin(new Insets(0, 0, 0, 0));
            addMouseListener(this);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(new ImageIcon(backImg).getImage(), 0, 0, null);
            if(isEntered) {
                g.setColor(BORDER_COLOR);
                g.drawRect(0, 0, width-1, height-1);
                g.setColor(BACK_COLOR);
                g.drawRect(1, 1, width-3, height-3);
            }
        }        
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if(color[colorIndex] != null) {
                isEntered = true;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(color[colorIndex] != null) {
                isEntered = false;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Color c = color[colorIndex];
            if(c != null) {
                if(color1Button.isSelected()) {
                    color1Button.setColor(c);
                    color1Button.repaint();
                    gui.activeColor1 = c;
                }
                else {
                    color2Button.setColor(c);
                    color2Button.repaint();
                    gui.activeColor2 = c;
                }
            }
    }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }

}
