package mspaint.gui.toolbar.view;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;

public class FullScreenTool extends GridBagPanel{
    public TButton fullScreen;
    
    public FullScreenTool() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        String text = "<HTML><p style=\"text-align:center;\">Full<br>Screen</p>";
        fullScreen = new TButton(text, ResourceLoader.getImageIcon("toolbar/full_screen_tool.png"), TButton.Type.NOT_SELECTABLE);
        fullScreen.setMargin(new Insets(2, 5, 2, 5));
        fullScreen.setVerticalTextPosition(SwingConstants.BOTTOM);
        fullScreen.setHorizontalTextPosition(SwingConstants.CENTER);

        add(fullScreen, 0, 0);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 0, 1, 1, 1, 1.0, 1.0);
        }
    }
}
