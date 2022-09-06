package mspaint.gui.toolbar.view;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

public class FloatCanvasTool extends GridBagPanel {
    public TButton floatCanvas;
    
    public FloatCanvasTool() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        String text = "<HTML><p style=\"text-align:center;\">Float<br>Canvas</p>";
        floatCanvas = new TButton(text, ResourceLoader.getImageIcon("toolbar/float_canvas_tool.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);
        floatCanvas.setMargin(new Insets(2, 5, 2, 5));
        floatCanvas.setVerticalTextPosition(SwingConstants.BOTTOM);
        floatCanvas.setHorizontalTextPosition(SwingConstants.CENTER);
        floatCanvas.setSelected(true);

        add(floatCanvas, 0, 0);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 0, 1, 1, 1, 1.0, 1.0);
        }
    }
}
