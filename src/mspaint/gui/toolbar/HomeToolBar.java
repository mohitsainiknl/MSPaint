package mspaint.gui.toolbar;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.BrushTools;
import mspaint.gui.toolbar.home.ClipboardTools;
import mspaint.gui.toolbar.home.ColorPlateTool;
import mspaint.gui.toolbar.home.ImageTools;
import mspaint.gui.toolbar.home.MainTools;
import mspaint.gui.toolbar.home.ShapeTools;
import mspaint.gui.toolbar.home.SizeTool;
import mspaint.gui.toolbar.view.ShowAndHideTools;
import mspaint.gui.toolbar.view.ShowAndHideTools.LayersToolShortcut;
import mspaint.helper.GroupTButtons;
import mspaint.helper.TSeparator;
import mspaint.style.Style;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class HomeToolBar extends GridBagPanel {
    private Theme theme;
    private GUIHandler gui;
    private GroupTButtons group;
    public ClipboardTools clipboardTools;
    public ImageTools imageTools;
    public MainTools mainTools;
    public BrushTools brushTools;
    public ShapeTools shapeTools;
    public SizeTool sizeTool;
    public ColorPlateTool colorPlate;
    public LayersToolShortcut layersToolShortcut;

    public HomeToolBar(GUIHandler gui, Style style, Theme theme, JLabel view) {
        super();
        this.gui = gui;
        this.theme = theme;
        setBorder(new EmptyBorder(5, 5, 3, 5));
        setBackground(theme.getBgColor());
        load_homeToolBar();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int width = getWidth();
        final int height = getHeight()-1;

        g.setColor(theme.getBorderColor());
        g.drawLine(0, 0, width, 0);
        g.drawLine(0, height, width, height);

        g.setColor(theme.getBgColor());
        g.drawLine(59, 0, 114, 0);
    }

    public void load_homeToolBar() {
        group = new GroupTButtons(gui);

        clipboardTools = new ClipboardTools();
        imageTools = new ImageTools(gui, group);
        sizeTool = new SizeTool();
        colorPlate = new ColorPlateTool(gui);
        mainTools = new MainTools(gui, sizeTool, group);
        brushTools = new BrushTools(gui, sizeTool, group);
        shapeTools = new ShapeTools(gui, theme, group);
        layersToolShortcut = new ShowAndHideTools.LayersToolShortcut();

        add(clipboardTools, 0, 0);
        addSeparator(1, 0);

        add(imageTools, 2, 0);
        addSeparator(3, 0);

        add(mainTools, 4, 0);
        addSeparator(5, 0);

        add(brushTools, 6, 0);
        addSeparator(7, 0);

        add(shapeTools, 8, 0);
        addSeparator(9, 0);

        add(sizeTool, 10, 0);
        addSeparator(11, 0);

        add(colorPlate, 12, 0);
        addSeparator(13, 0);

        add(layersToolShortcut, 14, 0, 1, 1, 1.0, 1.0);
    }

    /**
     * Appends a separator of a specified size to the end
     * of the tool bar.
     *
     * @param gridx the <code>Dimension</code> of the separator
     */
    public void addSeparator(int gridx, int gridy) {
        TSeparator s = new TSeparator(9, 92, 0, theme.getBorderColor(), TSeparator.VERTICAL);
        add(s, gridx, gridy);
    }

}
