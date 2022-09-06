package mspaint.gui.toolbar.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.size.SizeButton;
import mspaint.helper.DropTools;
import mspaint.helper.GroupTButtons;
import mspaint.helper.Point;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TMenuItem;
import mspaint.helper.TPopupMenu;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class ShapeTools extends GridBagPanel {
    private final JLabel label = new JLabel("Shapes");
    private Theme theme;
    private GroupTButtons group;

    private JScrollPane scrollpane;
    private GridBagPanel listPanel;
    private ShapeButton lineTool, curveTool, ovalTool, rectangleTool, roundedRecTool, polygonTool, triangleTool;
    private ShapeButton rightTriangleTool, diamondTool, pentagonTool, hexagonTool, rightArrowTool, leftArrowTool, upArrowTool;
    private ShapeButton downArrowTool, fourPointStarTool, fivePointStarTool, sixPointStarTool, roundedRecCalloutTool, ovalCalloutTool, cloudCalloutTool;
    private ShapeButton heartTool, lighteningTool;

    public String activeOutline = "";
    private DropTools outline;
    private TPopupMenu menu1;
    private TMenuItem noOutline, solid1, brush1, oil1, watercolor1;

    public String activeFill = "";
    private DropTools fill;
    private TPopupMenu menu2;
    private TMenuItem noFill, solid2, brush2, oil2, watercolor2;

    private GUIHandler gui;
    
    public ShapeTools(GUIHandler gui,  Theme theme, GroupTButtons group) {
        super();
        this.gui = gui;
        this.theme = theme;
        this.group = group;
        initialWork();
    }

    public void initialWork() {
        setOpaque(false);
        setupShapeList();
        setupOutline();
        setupFill();

        add(scrollpane, 0, 1, 1, 3);
        add(outline, 1, 1, 2, 1);
        add(fill, 1, 2, 1, 1);
        {
            JLabel gapRight = new JLabel();
            gapRight.setPreferredSize(new Dimension(2, 5));
            add(gapRight, 2, 2, 1, 1);

            JLabel gapDown = new JLabel();
            gapDown.setPreferredSize(new Dimension(2, 25));
            add(gapDown, 3, 2, 2, 1);
        }

        add(label, 0, 5, 2, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapTop = new JLabel();
            gapTop.setPreferredSize(new Dimension(245, 3));
            add(gapTop, 0, 0, 3, 1);

            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(2, 10));
            add(gapBottom, 0, 4, 3, 1, 1.0, 1.0);
        }
    }

    private void setupShapeList() {
        listPanel = new GridBagPanel();
        listPanel.setBackground(Color.WHITE);

        lineTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/line_tool.png"), TButton.Type.SELECTABLE);
        curveTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/curve_tool.png"), TButton.Type.SELECTABLE);
        ovalTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/oval_tool.png"), TButton.Type.SELECTABLE);
        rectangleTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/rectangle_tool.png"), TButton.Type.SELECTABLE);
        roundedRecTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/rounded_rectangle_tool.png"), TButton.Type.SELECTABLE);
        polygonTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/polygon_tool.png"), TButton.Type.SELECTABLE);
        triangleTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/triangle_tool.png"), TButton.Type.SELECTABLE);
        lineTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        curveTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        ovalTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        rectangleTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        roundedRecTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        polygonTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        triangleTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        lineTool.setButtonText("Line");
        curveTool.setButtonText("Curve");
        ovalTool.setButtonText("Oval");
        rectangleTool.setButtonText("Rectangle");
        roundedRecTool.setButtonText("Round Rec");
        polygonTool.setButtonText("Polygon");
        triangleTool.setButtonText("Triangle");
        listPanel.add(lineTool, 0, 0);
        listPanel.add(curveTool, 1, 0);
        listPanel.add(ovalTool, 2, 0);
        listPanel.add(rectangleTool, 3, 0);
        listPanel.add(roundedRecTool, 4, 0);
        listPanel.add(polygonTool, 5, 0);
        listPanel.add(triangleTool, 6, 0);

        rightTriangleTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/right_triangle_tool.png"), TButton.Type.SELECTABLE);
        diamondTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/diamond_tool.png"), TButton.Type.SELECTABLE);
        pentagonTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/pentagon_tool.png"), TButton.Type.SELECTABLE);
        hexagonTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/hexagon_tool.png"), TButton.Type.SELECTABLE);
        rightArrowTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/right_arrow_tool.png"), TButton.Type.SELECTABLE);
        leftArrowTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/left_arrow_tool.png"), TButton.Type.SELECTABLE);
        upArrowTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/up_arrow_tool.png"), TButton.Type.SELECTABLE);
        rightTriangleTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        diamondTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        pentagonTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        hexagonTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        rightArrowTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        leftArrowTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        upArrowTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        rightTriangleTool.setButtonText("Right Triangle");
        diamondTool.setButtonText("Diamond");
        pentagonTool.setButtonText("Pentagon");
        hexagonTool.setButtonText("Hexagon");
        rightArrowTool.setButtonText("Right Arrow");
        leftArrowTool.setButtonText("Left Arrow");
        upArrowTool.setButtonText("Up Arrow");
        listPanel.add(rightTriangleTool, 0, 1);
        listPanel.add(diamondTool, 1, 1);
        listPanel.add(pentagonTool, 2, 1);
        listPanel.add(hexagonTool, 3, 1);
        listPanel.add(rightArrowTool, 4, 1);
        listPanel.add(leftArrowTool, 5, 1);
        listPanel.add(upArrowTool, 6, 1);

        downArrowTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/down_arrow_tool.png"), TButton.Type.SELECTABLE);
        fourPointStarTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/four_point_star_tool.png"), TButton.Type.SELECTABLE);
        fivePointStarTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/five_point_star_tool.png"), TButton.Type.SELECTABLE);
        sixPointStarTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/six_point_star_tool.png"), TButton.Type.SELECTABLE);
        roundedRecCalloutTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/rounded_rec_callout_tool.png"), TButton.Type.SELECTABLE);
        ovalCalloutTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/oval_callout_tool.png"), TButton.Type.SELECTABLE);
        cloudCalloutTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/cloud_callout_tool.png"), TButton.Type.SELECTABLE);
        downArrowTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        fourPointStarTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        fivePointStarTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        sixPointStarTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        roundedRecCalloutTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        ovalCalloutTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        cloudCalloutTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
       downArrowTool.setButtonText("Down Arrow");
        fourPointStarTool.setButtonText("4 Point Star");
        fivePointStarTool.setButtonText("5 Point Star");
        sixPointStarTool.setButtonText("6 Point Star");
        roundedRecCalloutTool.setButtonText("Round Rec Callout");
        ovalCalloutTool.setButtonText("Oval Callout");
        cloudCalloutTool.setButtonText("Cloud Callout");
        listPanel.add(downArrowTool, 0, 2);
        listPanel.add(fourPointStarTool, 1, 2);
        listPanel.add(fivePointStarTool, 2, 2);
        listPanel.add(sixPointStarTool, 3, 2);
        listPanel.add(roundedRecCalloutTool, 4, 2);
        listPanel.add(ovalCalloutTool, 5, 2);
        listPanel.add(cloudCalloutTool, 6, 2);

        heartTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/heart_tool.png"), TButton.Type.SELECTABLE);
        lighteningTool = new ShapeButton(ResourceLoader.getImageIcon("toolbar/shapes/lightening_tool.png"), TButton.Type.SELECTABLE);
        heartTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        lighteningTool.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        heartTool.setButtonText("Heart");
        lighteningTool.setButtonText("Lightening");
        listPanel.add(heartTool, 0, 3);
        listPanel.add(lighteningTool, 1, 3);

        setTButtonMargin(new Insets(0, 0, 0, 0));

        scrollpane = new JScrollPane(listPanel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(13, 40));
        scrollpane.getVerticalScrollBar().setUnitIncrement(22);
        scrollpane.setBorder(new LineBorder(theme.getBorderColor(), 1));
        // scrollpane.getknob
        // listPanel.setPreferredSize(new Dimension(142, 180));
        scrollpane.setPreferredSize(new Dimension(162, 64));
    }

    private void setTButtonMargin(Insets insets) {
        lineTool.setMargin(insets);
        curveTool.setMargin(insets);
        ovalTool.setMargin(insets);
        rectangleTool.setMargin(insets);
        roundedRecTool.setMargin(insets);
        polygonTool.setMargin(insets);
        triangleTool.setMargin(insets);

        rightTriangleTool.setMargin(insets);
        diamondTool.setMargin(insets);
        pentagonTool.setMargin(insets);
        hexagonTool.setMargin(insets);
        rightArrowTool.setMargin(insets);
        leftArrowTool.setMargin(insets);
        upArrowTool.setMargin(insets);

        downArrowTool.setMargin(insets);
        fourPointStarTool.setMargin(insets);
        fivePointStarTool.setMargin(insets);
        sixPointStarTool.setMargin(insets);
        roundedRecCalloutTool.setMargin(insets);
        ovalCalloutTool.setMargin(insets);
        cloudCalloutTool.setMargin(insets);

        heartTool.setMargin(insets);
        lighteningTool.setMargin(insets);
    }

    private void setupOutline() {
        outline = new DropTools("Outline", ResourceLoader.getImageIcon("toolbar/shape_outline_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.HORIZONTAL);
        menu1 = new TPopupMenu();
        noOutline = new TMenuItem("No Outline", ResourceLoader.getImageIcon("toolbar/shapes/no_outline.png"), TButton.Type.SELECTABLE, menu1);
        solid1 = new TMenuItem("Solid", ResourceLoader.getImageIcon("toolbar/shapes/solid.png"), TButton.Type.SELECTABLE, menu1);
        brush1 = new TMenuItem("Brush", ResourceLoader.getImageIcon("toolbar/shapes/brush.png"), TButton.Type.SELECTABLE, menu1);
        oil1 = new TMenuItem("Oil", ResourceLoader.getImageIcon("toolbar/shapes/oil.png"), TButton.Type.SELECTABLE, menu1);
        watercolor1 = new TMenuItem("Watercolor", ResourceLoader.getImageIcon("toolbar/shapes/watercolor.png"), TButton.Type.SELECTABLE, menu1);

        GroupTButtons group = new GroupTButtons();
        group.add(noOutline);
        group.add(solid1);
        group.add(brush1);
        group.add(oil1);
        group.add(watercolor1);
        noOutline.setSelected(true);
        activeOutline = noOutline.getText();

        Action action = new Action(true);
        noOutline.addActionListener(action);
        solid1.addActionListener(action);
        brush1.addActionListener(action);
        oil1.addActionListener(action);
        watercolor1.addActionListener(action);

        menu1.add(noOutline);
        menu1.add(solid1);
        menu1.add(brush1);
        menu1.add(oil1);
        menu1.add(watercolor1);
        
        outline.addPopUpMenu(menu1);
    }

    private void setupFill() {
        fill = new DropTools("Fill", ResourceLoader.getImageIcon("toolbar/shape_fill_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.HORIZONTAL);
        menu2 = new TPopupMenu();
        noFill = new TMenuItem("No Fill", ResourceLoader.getImageIcon("toolbar/shapes/no_outline.png"), TButton.Type.SELECTABLE, menu2);
        solid2 = new TMenuItem("Solid", ResourceLoader.getImageIcon("toolbar/shapes/solid.png"), TButton.Type.SELECTABLE, menu2);
        brush2 = new TMenuItem("Brush", ResourceLoader.getImageIcon("toolbar/shapes/brush.png"), TButton.Type.SELECTABLE, menu2);
        oil2 = new TMenuItem("Oil", ResourceLoader.getImageIcon("toolbar/shapes/oil.png"), TButton.Type.SELECTABLE, menu2);
        watercolor2 = new TMenuItem("Watercolor", ResourceLoader.getImageIcon("toolbar/shapes/watercolor.png"), TButton.Type.SELECTABLE, menu2);

        GroupTButtons group = new GroupTButtons();
        group.add(noFill);
        group.add(solid2);
        group.add(brush2);
        group.add(oil2);
        group.add(watercolor2);
        noFill.setSelected(true);
        activeOutline = noFill.getText();

        Action action = new Action(false);
        noFill.addActionListener(action);
        solid2.addActionListener(action);
        brush2.addActionListener(action);
        oil2.addActionListener(action);
        watercolor2.addActionListener(action);

        menu2.add(noFill);
        menu2.add(solid2);
        menu2.add(brush2);
        menu2.add(oil2);
        menu2.add(watercolor2);
        
        fill.addPopUpMenu(menu2);
    }

    private int[] getBrushSize(int i) {
        int[] size = { 2, 4, 8, 12, 16, 24, 30, 36};
        int[] data = {size[i], size.length};
        return data;
    }
    
    private void setupSizeList() {
        gui.homeToolBar.sizeTool.resetButtonList();

        final int length = getBrushSize(0)[1];
        for (int i = 0; i < length; i++) {
            
            final int strokeSize = getBrushSize(i)[0];
            SizeButton button = new SizeButton(strokeSize); 
            gui.homeToolBar.sizeTool.addSizeButton(button);

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
    private class Action implements ActionListener {
        private boolean isOutline;

        public Action(boolean isOutline) {
            this.isOutline = isOutline;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TButton button = (TButton) e.getSource();
            if(isOutline) {
                activeOutline = button.getText();
            }
            else {
                activeFill = button.getText();
            }
        }
    }

    /**
     * ShapeButton
     */
    public class ShapeButton extends TButton {

        public ShapeButton(ImageIcon icon, Type type) {
            super(icon, type);
            group.add(this);
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    TButton button = (TButton) e.getSource();
                    gui.activeToolName = button.getName();
                    setupSizeList();
                } 
            });
        }
    }
}
