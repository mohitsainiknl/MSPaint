package mspaint.gui.toolbar.home;

import java.awt.Dimension;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.size.SizeButton;
import mspaint.helper.GroupTButtons;
import mspaint.helper.Point;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TButtonAndDropTools;
import mspaint.helper.TPopupMenu;

public class BrushTools extends GridBagPanel {
    private String activeBrushName;
    private GroupTButtons group, innerGroup;
    private TButtonAndDropTools brushTool;
    private TPopupMenu menu;
    private GridBagPanel menuPanel;
    private TButton lineBrushTool, calligraphyBrush1Tool, calligraphyBrush2Tool, airBrushTool;
    private TButton oilBrushTool, crayonTool, markerTool, colorPencilTool;
    private TButton waterColorBrushTool;
    private GUIHandler gui;
    private SizeTool sizeTool;
    
    public BrushTools(GUIHandler gui, SizeTool sizeTool, GroupTButtons group) {
        super();
        this.gui = gui;
        this.sizeTool = sizeTool;
        this.group = group;
        innerGroup = new GroupTButtons(gui);
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        menu = new TPopupMenu();
        menuPanel = new GridBagPanel(new Insets(0, 0, 0, 0));

        lineBrushTool = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/thin_brush_tool.png"), TButton.Type.SELECTABLE);
        lineBrushTool.setBigIcon(ResourceLoader.getImageIcon("toolbar/brushes/thin_brush_tool.png"));

        calligraphyBrush1Tool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/thick_old_pen_tool.png"), TButton.Type.SELECTABLE);
        calligraphyBrush2Tool = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/thin_old_pen_tool.png"), TButton.Type.SELECTABLE);
        airBrushTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/spray_tool.png"), TButton.Type.SELECTABLE);
        oilBrushTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/thick_brush_tool.png"), TButton.Type.SELECTABLE);
        crayonTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/wax_tool.png"), TButton.Type.SELECTABLE);
        markerTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/highlighter_tool.png"), TButton.Type.SELECTABLE);
        colorPencilTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/pencil_tool.png"), TButton.Type.SELECTABLE);
        waterColorBrushTool  = new TButton(ResourceLoader.getImageIcon("toolbar/brushes/light_brush_tool.png"), TButton.Type.SELECTABLE);

        lineBrushTool.setButtonText("Line Brush");
        calligraphyBrush1Tool.setButtonText("Calligraphy Brush 1");
        calligraphyBrush2Tool.setButtonText("Calligraphy Brush 2");
        airBrushTool.setButtonText("Air Brush");
        oilBrushTool.setButtonText("Oil Brush");
        crayonTool.setButtonText("Crayon");
        markerTool.setButtonText("Marker");
        colorPencilTool.setButtonText("Color Pencil");
        waterColorBrushTool.setButtonText("Watercolor Brush");

        lineBrushTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        calligraphyBrush1Tool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        calligraphyBrush2Tool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        airBrushTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        oilBrushTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        crayonTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        markerTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        colorPencilTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        waterColorBrushTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));

        innerGroup.add(lineBrushTool);
        innerGroup.add(calligraphyBrush1Tool);
        innerGroup.add(calligraphyBrush2Tool);
        innerGroup.add(airBrushTool);
        innerGroup.add(oilBrushTool);
        innerGroup.add(crayonTool);
        innerGroup.add(markerTool);
        innerGroup.add(colorPencilTool);
        innerGroup.add(waterColorBrushTool);

        lineBrushTool.addActionListener(new Action());
        calligraphyBrush1Tool.addActionListener(new Action());
        calligraphyBrush2Tool.addActionListener(new Action());
        airBrushTool.addActionListener(new Action());
        oilBrushTool.addActionListener(new Action());
        crayonTool.addActionListener(new Action());
        markerTool.addActionListener(new Action());
        colorPencilTool.addActionListener(new Action());
        waterColorBrushTool.addActionListener(new Action());

        menuPanel.add(lineBrushTool, 0, 0);
        menuPanel.add(calligraphyBrush1Tool, 1, 0);
        menuPanel.add(calligraphyBrush2Tool, 2, 0);
        menuPanel.add(airBrushTool, 3, 0);
        menuPanel.add(oilBrushTool, 0, 1);
        menuPanel.add(crayonTool, 1, 1);
        menuPanel.add(markerTool, 2, 1);
        menuPanel.add(colorPencilTool, 3, 1);
        menuPanel.add(waterColorBrushTool, 0, 2);
        
        menu.add(menuPanel);
        brushTool = new TButtonAndDropTools("Brushes", true, lineBrushTool, menu);
        brushTool.getDropTools().setMargin(new Insets(3, 2, 13, 2));
        brushTool.getTButton().setMargin(new Insets(2, 6, 1, 5));
        group.add(brushTool.getTButton());
        
        
        add(brushTool, 0, 1);
        {
            JLabel gapTop = new JLabel();
            gapTop.setPreferredSize(new Dimension(2, 0));
            add(gapTop, 0, 0);

            JLabel gapBottom = new JLabel();
            setGridWeight(1.0, 1.0);
            add(gapBottom, 0, 2);
        }
    }

    private int[] getBrushSize(int i) {
        if(activeBrushName.equals("Line Brush")) {
            int[] size = {1, 4, 8, 12, 16};
            int[] data = {size[i], size.length};
            return data;
        }
        if(activeBrushName.equals("Calligraphy Brush 1") || activeBrushName.equals("Calligraphy Brush 2")) {
            int[] size = {3, 5, 8, 10, 13, 16};
            int[] data = {size[i], size.length};
            return data;
        }
        if(activeBrushName.equals("Air Brush")) {
            int[] size = {4, 8, 16, 24, 30, 40};
            int[] data = {size[i], size.length};
            return data;
        }
        if (activeBrushName.equals("Oil Brush") || activeBrushName.equals("Crayon") ||
            activeBrushName.equals("Marker") || activeBrushName.equals("Watercolor Brush")) {
            int[] size = {8, 16, 25, 30, 40};
            int[] data = {size[i], size.length};
            return data;
        }
        if(activeBrushName.equals("Color Pencil")) {
            int[] size = {4, 6, 8, 10};
            int[] data = {size[i], size.length};
            return data;
        }

        int[] empty = {0, 0};
        return empty;
    }
    
    private void setupBrushSizeList(String brushName) {
        activeBrushName = brushName;
        sizeTool.resetButtonList();

        final int length = getBrushSize(0)[1];
        for (int i = 0; i < length; i++) {
            
            final int strokeSize = getBrushSize(i)[0];
            SizeButton button = new SizeButton(strokeSize); 
            sizeTool.addSizeButton(button);

            if(i == 0 && gui.activeSize == 0) {
                button.setSelected(true);
                button.repaint();
                gui.activeSize = strokeSize;
            }
            else if(gui.activeSize == strokeSize) {
                button.setSelected(true);
                button.repaint();
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.activeSize = strokeSize;
                }
            });
        }
    }


    /**
     * Action
     */
    public class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TButton button = (TButton) e.getSource();
            brushTool.setActiveButton(button);
            setupBrushSizeList(button.getButtonText());
            menu.setVisible(false);
        }
    }
}
