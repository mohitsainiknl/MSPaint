package mspaint.gui.toolbar.view;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

/**
 * ZoomTool
 */
public class ZoomTools extends GridBagPanel {
    private final JLabel label = new JLabel("Zoom");
    public TButton zoomIn, zoomOut, defaultZoom;

    public ZoomTools() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        String text = "<HTML><p style=\"text-align:center;\">Zoom<br>In</p>";
        zoomIn = new TButton(text, ResourceLoader.getImageIcon("toolbar/zoom_in_tool.png"), TButton.Type.NOT_SELECTABLE);
        text = "<HTML><p style=\"text-align:center;\">Zoom<br>Out</p>";
        zoomOut = new TButton(text, ResourceLoader.getImageIcon("toolbar/zoom_out_tool.png"), TButton.Type.NOT_SELECTABLE);
        text = "<HTML><p style=\"text-align:center;\">100<br>%</p>";
        defaultZoom = new TButton(text, ResourceLoader.getImageIcon("toolbar/zoom_default_tool.png"), TButton.Type.NOT_SELECTABLE);
        setupTButton(zoomIn);
        setupTButton(zoomOut);
        setupTButton(defaultZoom);

        add(zoomIn, 0, 0);
        add(zoomOut, 1, 0);
        add(defaultZoom, 2, 0);
        add(label, 0, 2, 3, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 1, 0, 3, 0, 1.0, 1.0);
        }
    }

    private void setupTButton(TButton button) {
        button.setMargin(new Insets(2, 5, 2, 5));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
    }
}