package mspaint.gui.toolbar.home;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.size.SizeButton;
import mspaint.helper.GroupTButtons;
import mspaint.helper.Point;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class MainTools extends GridBagPanel {
    private final JLabel label = new JLabel("Tools");
    private TButton pencilTool, fillTool, textTool;
    private TButton eraserTool, colorPickerTool, zoomTool;
    private GroupTButtons group;
    private SizeTool sizeTool;
    private GUIHandler gui;


    public MainTools(GUIHandler gui, SizeTool sizeTool, GroupTButtons group) {
        super();
        this.gui = gui;
        this.sizeTool = sizeTool;
        this.group = group;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        pencilTool = new TButton(ResourceLoader.getImageIcon("toolbar/pencil_tool.png"), TButton.Type.SELECTABLE);
        fillTool = new TButton(ResourceLoader.getImageIcon("toolbar/fill_tool.png"), TButton.Type.SELECTABLE);
        textTool = new TButton(ResourceLoader.getImageIcon("toolbar/text_tool.png"), TButton.Type.SELECTABLE);
        eraserTool = new TButton(ResourceLoader.getImageIcon("toolbar/eraser_tool.png"), TButton.Type.SELECTABLE);
        colorPickerTool = new TButton(ResourceLoader.getImageIcon("toolbar/color_picker_tool.png"), TButton.Type.SELECTABLE);
        zoomTool = new TButton(ResourceLoader.getImageIcon("toolbar/zoom_tool.png"), TButton.Type.SELECTABLE);
        
        pencilTool.setButtonText("Pencil Tool");
        pencilTool.addActionListener(new Action());
        colorPickerTool.setButtonText("Color Picker Tool");
        colorPickerTool.addActionListener(new Action());
        eraserTool.setButtonText("Eraser Tool");
        eraserTool.addActionListener(new Action());
        zoomTool.setButtonText("Zoom Tool");
        zoomTool.addActionListener(new Action());

        pencilTool.setViewCursor(ResourceLoader.getImage("cursors/pencil_cursor2.png"), new Point(0, 31));
        fillTool.setViewCursor(ResourceLoader.getImage("cursors/fill_cursor2.png"), new Point(0, 31));
        textTool.setViewCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        eraserTool.setViewCursor("", new Point(0, 0));
        colorPickerTool.setViewCursor(ResourceLoader.getImage("cursors/picker_cursor2.png"), new Point(0, 31));
        zoomTool.setViewCursor(ResourceLoader.getImage("cursors/zoom_cursor2.png"), new Point(12, 12));

        pencilTool.setMargin(new Insets(2, 2, 2, 2));
        fillTool.setMargin(new Insets(2, 2, 2, 2));
        textTool.setMargin(new Insets(2, 2, 2, 2));
        eraserTool.setMargin(new Insets(2, 2, 2, 2));
        colorPickerTool.setMargin(new Insets(2, 2, 2, 2));
        zoomTool.setMargin(new Insets(2, 2, 2, 2));

        pencilTool.setSelected(true);
        gui.activeToolName = pencilTool.getButtonText();
        gui.view.setCursor(pencilTool.getViewCursor());
        setupSizeList("Pencil");

        group.add(pencilTool);
        group.add(eraserTool);
        group.add(textTool);
        group.add(fillTool);
        group.add(colorPickerTool);
        group.add(zoomTool);

        add(pencilTool, 0, 1);
        add(fillTool, 1, 1);
        add(textTool, 2, 1);
        add(eraserTool, 0, 2);
        add(colorPickerTool, 1, 2);
        add(zoomTool, 2, 2);
        add(label, 0, 4, 3, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapTop = new JLabel();
            gapTop.setPreferredSize(new Dimension(2, 10));
            add(gapTop, 0, 0, 3, 1);

            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(2, 13));
            add(gapBottom, 0, 3, 3, 1, 1.0, 1.0);
        }
    }

    String activeName;
    private int[] getBrushSize(int i) {
        if(activeName.equals("Pencil")) {
            int[] size = { 1, 2, 3, 4};
            int[] data = {size[i], size.length};
            return data;
        }
        if(activeName.equals("Eraser")) {
            int[] size = { 2, 8, 16, 32, 42, 50, 60, 100};
            int[] data = {size[i], size.length};
            return data;
        }

        int[] empty = {0, 0};
        return empty;
    }
    
    private void setupSizeList(String ToolName) {
        sizeTool.resetButtonList();
        activeName = ToolName;

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
    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((TButton) e.getSource()).getButtonText();

            if(name.equals("Pencil Tool")) {
                setupSizeList("Pencil");
            }
            else if(name.equals("Eraser Tool")) {
                setupSizeList("Eraser");
            }

            else if(name.equals("Color Picker Tool")) {
            }
            else if(name.equals("Zoom Tool")) {
            }
        }
    }
}
