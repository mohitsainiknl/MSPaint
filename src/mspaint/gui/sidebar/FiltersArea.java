package mspaint.gui.sidebar;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import mspaint.helper.TButton;
import mspaint.initial_info.InitialValues;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class FiltersArea extends JScrollPane {
    private GridBagPanel panel;

    public FiltersArea() {
        super();
        setOpaque(false);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        initialWork();
        setViewportView(panel);
    }
    
    private void initialWork() {
        panel = new GridBagPanel(new Insets(5, 5, 5, 5));
        panel.setOpaque(false);

        FilterButton filter3 = new FilterButton("Filter 3");
        panel.add(filter3, 0, 0, 1, 1, 1.0, 0);

        FilterButton filter2 = new FilterButton("Filter 2");
        panel.add(filter2, 0, 1, 1, 1, 1.0, 0);

        FilterButton filter1 = new FilterButton("Filter 1");
        panel.add(filter1, 0, 2, 1, 1, 1.0, 0);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panel.updateUI();
    }


    public class FilterButton extends TButton {

        public FilterButton() {
            this("", null);
        }
    
        public FilterButton(String text) {
            this(text, null);
        }
    
        public FilterButton(ImageIcon icon) {
            this("", icon);
        }
    
        public FilterButton(String text, ImageIcon icon) {
            super(text, icon, TButton.Type.SELECTABLE);
            setOpaque(true);
            setBorderPainted(true);
            setHorizontalAlignment(JButton.CENTER);
            resetSize();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            resetSize();
        }
        
        public void resetSize() {
            final int width = InitialValues.sidePanelWith - 30;
            final int height = (width * 2) / 3;
            setPreferredSize(new Dimension(width, height));
        }
    }
    
}
