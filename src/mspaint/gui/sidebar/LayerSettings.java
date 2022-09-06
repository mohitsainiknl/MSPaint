package mspaint.gui.sidebar;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import mspaint.gui.GUIHandler;
import mspaint.helper.Layer;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TSeparator;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class LayerSettings extends JPanel {
    private GUIHandler gui;
    private Theme theme;
    private GridBagPanel panel;
    private TButton addLayer, delLayer, hideLayer;
    
    public LayerSettings(GUIHandler gui) {
        super(new FlowLayout(FlowLayout.CENTER, 10, 8));
        this.gui = gui;
        this.theme = gui.theme;
        setBorder(new MatteBorder(5, 0, 0, 0, new Color(0, 0, 0, 0)));
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        panel = new GridBagPanel(new Insets(0, 0, 1, 0));
        panel.setOpaque(false);
        final int leftm = 8;
        final int tgap = 9;

        addLayer = new TButton("Add Layer", ResourceLoader.getImageIcon("toolbar/menu/plus_icon.png"), TButton.Type.NOT_SELECTABLE);
        addLayer.setMargin(new Insets(0, leftm, 0, 0));
        addLayer.setIconTextGap(tgap);
        panel.add(addLayer, 0, 0, 1, 1, 1.0, 1.0);

        delLayer = new TButton("Delete Layer", ResourceLoader.getImageIcon("toolbar/menu/minus_icon.png"), TButton.Type.NOT_SELECTABLE);
        delLayer.setMargin(new Insets(0, leftm, 0, 0));
        delLayer.setIconTextGap(tgap);
        panel.add(delLayer, 0, 1, 1, 1, 1.0, 1.0);

        hideLayer = new TButton("Show/Hide Layer", ResourceLoader.getImageIcon("toolbar/menu/eye_icon.png"), TButton.Type.NOT_SELECTABLE);
        panel.add(hideLayer, 0, 2, 1, 1, 1.0, 1.0);

        addLayer.addActionListener(new Action());
        delLayer.addActionListener(new Action());
        hideLayer.addActionListener(new Action());

        add(panel);
    }

    
    /**
     * Appends a separator of a specified size to the end
     * of the tool bar.
     *
     * @param size the <code>Dimension</code> of the separator
     */
    public void addSeparator(int gridx, int gridy) {
        TSeparator s = new TSeparator(9, 92, 0, theme.getBorderColor(), TSeparator.VERTICAL);
        add(s, gridx, gridy);
    }


    /**
     * Action
     */
    public class Action implements ActionListener {

        public Action() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TButton button = (TButton) e.getSource();

            if(button.getText().equals("Add Layer")) {
                BufferedImage img = gui.layers.get(0).image;
                gui.layers.add(new Layer(new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB), true));
                gui.layerSideBar.layersArea.addLayerButton();                
                gui.layerSideBar.layersArea.updateUI();
            }
            else if(button.getText().equals("Delete Layer")) {
                
            }
            else if(button.getText().equals("Show/Hide Layer")) {
                final int activeLayer = LayersArea.activeLayer;
                gui.layers.get(activeLayer).isVisible = !gui.layers.get(activeLayer).isVisible;
                gui.view.repaint();
            }
        }
    }
}
