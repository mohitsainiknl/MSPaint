package mspaint.gui.toolbar.text;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.helper.GroupTButtons;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

public class BackgroundTools extends GridBagPanel {
    private final JLabel label = new JLabel("Background");
    private TButton transparent, opaque;
    private GroupTButtons group;
    
    public BackgroundTools() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        transparent = new TButton("Transparent", ResourceLoader.getImageIcon("toolbar/text/transparent_font_tool.png"), TButton.Type.SELECTABLE);
        opaque = new TButton("Opaque", ResourceLoader.getImageIcon("toolbar/text/opaque_font_tool.png"), TButton.Type.SELECTABLE);

        group = new GroupTButtons();
        group.add(transparent);
        group.add(opaque);

        ButtonGroup group = new ButtonGroup();
        group.add(transparent);
        group.add(opaque);

        add(transparent, 0, 0);
        add(opaque, 0, 1);
        add(label, 0, 3);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 0, 2, 1, 1, 1.0, 1.0);
        }
    }
}
