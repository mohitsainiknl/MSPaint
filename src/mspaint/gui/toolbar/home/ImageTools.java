package mspaint.gui.toolbar.home;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import mspaint.gui.GUIHandler;
import mspaint.helper.DropTools;
import mspaint.helper.GroupTButtons;
import mspaint.helper.Point;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TButtonAndDropTools;
import mspaint.helper.TMenuItem;
import mspaint.helper.TMenuSection;
import mspaint.helper.TPopupMenu;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class ImageTools extends GridBagPanel {
    private final JLabel label = new JLabel("Image");
    private GroupTButtons group, innerGroup;
    private TButtonAndDropTools selectTool;
    public TButton cropTool, resizeTool;

    private TPopupMenu rotateMenu;
    private DropTools rotateTool;
    public TMenuItem rotateRight_90, rotateLeft_90, rotate_180, flipHorizonal, flipVertical;

    private TPopupMenu selectMenu;
    private TMenuSection shapeSection, optionSection;
    public TMenuItem rectangularSecItem, free_fromSecItem;
    public TMenuItem selectAllItem, deleteItem;
    private GUIHandler gui;

    public ImageTools(GUIHandler gui, GroupTButtons group) {
        super();
        this.gui = gui;
        this.group = group;
        innerGroup = new GroupTButtons(gui);
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        setupSelectTool();
        cropTool = new TButton("Crop", ResourceLoader.getImageIcon("toolbar/crop_tool.png"), TButton.Type.NOT_SELECTABLE);
        resizeTool = new TButton("Resize", ResourceLoader.getImageIcon("toolbar/resize_tool.png"), TButton.Type.NOT_SELECTABLE);
        cropTool.setTEnabled(false);
        setupRatateTool();
        group.add(selectTool.getTButton());
        add(selectTool, 0, 0, 1, 3);
        add(cropTool, 1, 0, 1, 1);
        add(resizeTool, 1, 1, 1, 1);
        add(rotateTool, 1, 2, 1, 1);
        add(label, 0, 4, 2, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(2, 1));
            add(gapBottom, 0, 3, 5, 1, 1.0, 1.0);
        }
    }

    private void setupRatateTool() {
        rotateMenu = new TPopupMenu();
        rotateRight_90 = new TMenuItem("Rotate Right 90 dgree", ResourceLoader.getImageIcon("toolbar/rotate/rotate_right_tool.png"), TButton.Type.NOT_SELECTABLE, rotateMenu);
        rotateLeft_90 = new TMenuItem("Rotate Left 90 dgree", ResourceLoader.getImageIcon("toolbar/rotate/rotate_left_tool.png"), TButton.Type.NOT_SELECTABLE, rotateMenu);
        rotate_180 = new TMenuItem("Rotate 180 dgree", ResourceLoader.getImageIcon("toolbar/rotate/rotate_180_tool.png"), TButton.Type.NOT_SELECTABLE, rotateMenu);
        flipVertical = new TMenuItem("Flip Vertical", ResourceLoader.getImageIcon("toolbar/rotate/flip_v_tool.png"), TButton.Type.NOT_SELECTABLE, rotateMenu);
        flipHorizonal = new TMenuItem("Flip Horizonal", ResourceLoader.getImageIcon("toolbar/rotate/flip_h_tool.png"), TButton.Type.NOT_SELECTABLE, rotateMenu);
        rotateMenu.add(rotateRight_90);
        rotateMenu.add(rotateLeft_90);
        rotateMenu.add(rotate_180);
        rotateMenu.add(flipVertical);
        rotateMenu.add(flipHorizonal);
        rotateTool = new DropTools("Rotate", ResourceLoader.getImageIcon("toolbar/rotate_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.HORIZONTAL);
        rotateTool.addPopUpMenu(rotateMenu);
    }

    private void setupSelectTool() {
        selectMenu = new TPopupMenu();
        shapeSection = new TMenuSection("Section Shapes", true);    //<--- Section One

        rectangularSecItem = new TMenuItem("Rectangular selection", ResourceLoader.getImageIcon("toolbar/menu/rec_sec_tool.png"), TButton.Type.SELECTABLE, selectMenu);
        rectangularSecItem.setBigIcon(ResourceLoader.getImageIcon("toolbar/rec_selection_tool.png"));
        free_fromSecItem = new TMenuItem("Free-from selection", ResourceLoader.getImageIcon("toolbar/menu/free_sec_tool.png"), TButton.Type.SELECTABLE, selectMenu);
        free_fromSecItem.setBigIcon(ResourceLoader.getImageIcon("toolbar/free_selection_tool.png"));

        rectangularSecItem.setButtonText("Rectangular Selection Tool");
        free_fromSecItem.setButtonText("Free-form Selection Tool");
        rectangularSecItem.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));
        free_fromSecItem.setViewCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16));

        innerGroup.add(rectangularSecItem);
        innerGroup.add(free_fromSecItem);
        rectangularSecItem.addActionListener(new Action());
        free_fromSecItem.addActionListener(new Action());

        optionSection = new TMenuSection("Section Options", false);     //<--- Section Two
        selectAllItem = new TMenuItem("Select all", ResourceLoader.getImageIcon("toolbar/menu/select_all_tool.png"), TButton.Type.NOT_SELECTABLE, selectMenu);
        deleteItem = new TMenuItem("Delete", ResourceLoader.getImageIcon("toolbar/menu/delete_tool.png"), TButton.Type.NOT_SELECTABLE, selectMenu);

        selectMenu.add(shapeSection);
        selectMenu.add(rectangularSecItem);
        selectMenu.add(free_fromSecItem);
        selectMenu.add(optionSection);
        selectMenu.add(selectAllItem);
        selectMenu.add(deleteItem);

        selectTool = new TButtonAndDropTools("Select", true, rectangularSecItem, selectMenu);
        selectTool.getTButton().setMargin(new Insets(2, 4, 2, 4));
    }


    /**
     * Action
     */
    public class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TButton button = (TButton) e.getSource(); 
            selectTool.setActiveButton(button);
            gui.activeToolName = button.getButtonText();
        }
    }
}
