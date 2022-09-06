package mspaint.gui.toolbar.view;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.helper.DropTools;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

public class ShowAndHideTools extends GridBagPanel {
    private final JLabel label = new JLabel("Show and Hide");
    public TButton layers, filters;
    public TCheckBox rulers, gridlines, statusbar;
    
    public ShowAndHideTools() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);

        String text = "<HTML><p style=\"text-align:center;\">Layers</p>";
        layers = new TButton(text, ResourceLoader.getImageIcon("toolbar/layers_tool.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);

        text = "<HTML><p style=\"text-align:center;\">Filters</p>";
        filters = new TButton(text, ResourceLoader.getImageIcon("toolbar/filters_tool.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);

        rulers = new TCheckBox("Rulers", false);
        gridlines = new TCheckBox("Gridlines", false);
        statusbar = new TCheckBox("Status bar", true);

        setupTButton(layers);
        setupTButton(filters);
        layers.setName("layer");
        filters.setName("filter");

        add(layers, 1, 0, 1, 3);
        {
            JLabel gap = new JLabel();
            gap.setPreferredSize(new Dimension(4, 0));
            add(gap, 2 , 0, 1, 3);
        }
        add(filters,3, 0, 1, 3);
        add(rulers, 0, 0);
        add(gridlines, 0, 1);
        add(statusbar, 0, 2);

        add(label, 0, 4, 3, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel bottomGap = new JLabel();
            bottomGap.setPreferredSize(new Dimension(0, 0));
            add(bottomGap, 0 , 3, 3, 1, 1.0, 1.0);
        }
    }

    private void setupTButton(TButton button) {
        button.setMargin(new Insets(2, 5, 2, 5));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
    }



    public static class LayersToolShortcut extends GridBagPanel {
        public DropTools layersShortcut;
 
        public LayersToolShortcut() {
            super();
            initializeWork();
        }

        private void initializeWork() {
            setOpaque(false);
            {
                JLabel topGap = new JLabel();
                topGap.setPreferredSize(new Dimension(0, 0));
                add(topGap, 0, 0, 2, 1, 1.0, 1.0);

                JLabel leftGap = new JLabel();
                leftGap.setPreferredSize(new Dimension(0, 0));
                add(leftGap, 0, 1, 1, 1, 1.0, 0.0);
            }
            layersShortcut = new DropTools("Layers", ResourceLoader.getImageIcon("toolbar/show_layers_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.HORIZONTAL);
            layersShortcut.setLeftSideArrow(true);
            add(layersShortcut, 1, 1, 1, 1, 0.0, 0.0);
        }
    }


    public class TCheckBox extends JCheckBox {

        public TCheckBox(String text, boolean checked) {
            super(text, checked);
            setFocusable(false);
            setOpaque(false);
        }
    }
}
