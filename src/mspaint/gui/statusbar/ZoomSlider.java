package mspaint.gui.statusbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mspaint.gui.GUIHandler;
import mspaint.helper.ResourceLoader;

public class ZoomSlider extends JSlider implements ChangeListener {
    public JLabel zoomStatus;
    public ZoomButton zoomInButton, zoomOutButton;
    private GUIHandler gui;
    
    public ZoomSlider(GUIHandler gui, Font font) {
        super(JSlider.HORIZONTAL, 0, 32, 4);
        this.gui = gui;
        {
            zoomStatus = new JLabel();
            zoomStatus.setFont(font);
            zoomStatus.setPreferredSize(new Dimension(32, 17));
            zoomStatus.setHorizontalAlignment(JLabel.RIGHT);
        }
        setPreferredSize(new Dimension(90, 17));
        setOpaque(false);
        setFocusable(false);
        addChangeListener(this);
        {
            zoomInButton = new ZoomButton("+", font.getSize(), this);
            zoomOutButton = new ZoomButton("-", font.getSize(), this);
        }

        setSnapToTicks(true);
        setValueIsAdjusting(true);
    }

    public int getRealValue() {
        final int key = getValue();
        final int value = key * 25;
        if(value == 0) {
            return 12;
        }
        return value;
    }

    public JLabel getZoomStatus() {
        return zoomStatus;
    }

    public ZoomButton getZoomInButton() {
        return zoomInButton;
    }

    public ZoomButton getZoomOutButton() {
        return zoomOutButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        zoomStatus.setText(((ZoomSlider)e.getSource()).getRealValue() + "%");
    }



    /**
     * 
     */
    public class ZoomButton extends JButton implements ActionListener {
        // private ZoomSlider slider;
        private boolean pressed;

        public ZoomButton(String text, int fontSize, ZoomSlider slider) {
            super();
            // this.slider = slider;
            setName(text);
            setFont(new Font("Lucida Console", Font.PLAIN, fontSize + 2));
            addActionListener(this);
            addMouseListener();
            setPreferredSize(new Dimension(16, 16));
            setMargin(new Insets(0, 0, 0, 0));
            setFocusable(false);
            setBorderPainted(false);
            setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            Image icon = null;
            if(getName().equals("+")) {
                icon = ResourceLoader.getImageIcon("statusbar/zoom_in_button.png").getImage();
            }
            else {
                icon = ResourceLoader.getImageIcon("statusbar/zoom_out_button.png").getImage();
            }
            g2d.drawImage(icon, 0, 0, getWidth(), getHeight(), null);

            if(pressed) {
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(getName().equals("+")) {
                gui.viewToolBar.zoomTools.zoomIn.doClick();
            }
            else {
                gui.viewToolBar.zoomTools.zoomOut.doClick();
            }
        }

        private void addMouseListener() {
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    pressed = false;
                }
            });
        }
    }
}
