package mspaint.gui.toolbar;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.ClipboardTools;
import mspaint.gui.toolbar.home.ColorPlateTool;
import mspaint.gui.toolbar.text.BackgroundTools;
import mspaint.gui.toolbar.text.FontTool;
import mspaint.helper.TSeparator;
import mspaint.style.Style;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class TextToolBar extends GridBagPanel {
    private Theme theme;
    private GUIHandler gui;
    
    public TextToolBar(GUIHandler gui, Style style, Theme theme) {
        super();
        this.gui = gui;
        this.theme = theme;
        setBorder(new EmptyBorder(5, 5, 3, 5));
        setBackground(theme.getBgColor());
        load_textToolBar();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = getWidth();
        final int height = getHeight()-1;
        
        g.setColor(new Color(25, 121, 202));
        g.drawLine(0, 0, width, 0);
        g.setColor(theme.getBorderColor());
        g.drawLine(0, height, width, height);

        g.setColor(theme.getBgColor());
        g.drawLine(179, 0, 234, 0);
    }


    public void load_textToolBar() {
        add(new ClipboardTools(), 0, 0);
        addSeparator(1, 0);

        add(new FontTool(theme), 2, 0);
        addSeparator(3, 0);

        add(new BackgroundTools(), 4, 0);
        addSeparator(5, 0);
        
        add(new ColorPlateTool(gui), 6, 0);
        addSeparator(7, 0);

        {
            JPanel p = new JPanel();
            p.setBackground(new Color(0, 0, 0, 0));
            setGridWeight(1.0, 1.0);
            add(p, 14, 0);
        }
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
}
