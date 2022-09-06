package mspaint.gui.sidebar;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.gui.GUIHandler;
import mspaint.helper.GroupTButtons;
import mspaint.helper.TButton;
import mspaint.helper.Texture;
import mspaint.initial_info.InitialValues;

public class LayersArea extends JPanel {
    private JScrollPane scroll;
    private GridBagPanel panel;
    private static GUIHandler gui;
    private GroupTButtons group;
    public static int activeLayer;
    private int length = 0;
    private List<LayerButton> layerButton;

    public LayersArea(GUIHandler gui) {
        super(new BorderLayout());
        LayersArea.gui = gui;
        setBorder(new MatteBorder(0, 0, 0, 11, gui.theme.getBgColor()));
        initialWork();
    }
    
    private void initialWork() {
        setupScrollPane();

        panel = new GridBagPanel(new Insets(5, 16, 5, 16));
        panel.setOpaque(false);

        group = new GroupTButtons();
        layerButton = new ArrayList<>(1);
        LayerButton mainLayer = new LayerButton(0, "Main Layer");
        addLayerButton(mainLayer);
        mainLayer.setSelected(true);

        scroll.setViewportView(panel);
        add(scroll, BorderLayout.CENTER);
    }
    
    private void setupScrollPane() {
        scroll = new JScrollPane() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                panel.updateUI();
            }
        };
        scroll.setOpaque(false);
		scroll.setBorder(new LineBorder(gui.theme.getBorderColor(), 1));
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.getVerticalScrollBar().setSize(5, scroll.getHeight());
        scroll.getVerticalScrollBar().setOpaque(false);
        // scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void addLayerButton(LayerButton layer) {
        layerButton.add(layer);
        readdLayersOnPanel();
        group.add(layer);
        layer.setSelected(true);
        ++length;
    }

    public void addLayerButton() {
        LayerButton layer = new LayerButton(length);
        layerButton.add(layer);
        readdLayersOnPanel();
        group.add(layer);
        layer.doClick();
        ++length;
    }

    private void readdLayersOnPanel() {
        int index = 0;
        for (int i = layerButton.size()-1; i >= 0 ; i--, index++) {
            panel.add(layerButton.get(index), 0, i, 1, 1, 1.0, 0);
        }
    }

    public void repaintLayerButton(int index) {
        layerButton.get(index).repaint();
    }


    public static class LayerButton extends TButton {
        public int index;

        public LayerButton(int index) {
            this(index, "", null);
        }
    
        public LayerButton(int index, String text) {
            this(index, text, null);
        }
    
        public LayerButton(int index, ImageIcon icon) {
            this(index, "", icon);
        }
    
        public LayerButton(int index, String text, ImageIcon icon) {
            super(text, icon, TButton.Type.SELECTABLE);
            this.index = index;
            setOpaque(false);
            setMargin(new Insets(0, 0, 0, 0));
            setHorizontalAlignment(JButton.CENTER);
            resetSize();
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.graphics = gui.layers.get(index).image.createGraphics();
                    activeLayer = index;
                }
            });
        }
        
        @Override
        public void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            resetSize();
            final int gapx = 10;
            final int gapy = 10;
            final int width = getWidth() - 2*gapx;
            final int height = getHeight() - 2*gapy;
            {
                g.setPaint(Texture.getTransparentPaint(10));
                g.fillRect(gapx, gapy, width, height);
            }
            
            g.drawImage(gui.layers.get(index).image, gapx, gapy, width, height, null, null);
            if(! gui.layers.get(index).isVisible) {
                g.setColor(new Color(100, 100, 100, 100));
                g.fillRect(gapx, gapy, width, height);
            }
            g.setColor(gui.theme.getBorderColor());
            g.drawRect(gapx, gapy, width, height);
        }
        
        public void resetSize() {
            final int width = InitialValues.sidePanelWith - 30 - 16;
            final int height = (width * 2) / 3;
            setPreferredSize(new Dimension(width-11, height));
        }
    }
    
}
