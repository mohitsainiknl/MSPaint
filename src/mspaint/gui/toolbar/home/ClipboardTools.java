package mspaint.gui.toolbar.home;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TButtonAndDropTools;
import mspaint.helper.TMenuItem;
import mspaint.helper.TPopupMenu;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class ClipboardTools extends GridBagPanel {
    private final JLabel label = new JLabel("Clipboard");
    private TPopupMenu menu;
    public TButtonAndDropTools pasteTool;
    public TButton cutTool, copyTool;
    public TMenuItem pasteItem, pasteImportItem;

    public ClipboardTools() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        setupPasteTool();
        cutTool = new TButton("Cut", ResourceLoader.getImageIcon("toolbar/cut_tool.png"), TButton.Type.NOT_SELECTABLE);
        copyTool = new TButton("Copy", ResourceLoader.getImageIcon("toolbar/copy_tool.png"), TButton.Type.NOT_SELECTABLE);

        cutTool.setTEnabled(false);
        copyTool.setTEnabled(false);

        add(pasteTool, 0, 0, 1, 3);
        add(cutTool, 1, 0, 1, 1);
        add(copyTool, 1, 1, 1, 1);
        add(label, 0, 4, 2, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(0, 0));
            add(gapBottom, 0, 3, 5, 1, 1.0, 1.0);
        }
    }

    private void setupPasteTool() {
        menu = new TPopupMenu();
        pasteItem = new TMenuItem("Paste", ResourceLoader.getImageIcon("toolbar/menu/paste_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        pasteItem.setBigIcon(ResourceLoader.getImageIcon("toolbar/paste_tool.png"));
        pasteImportItem = new TMenuItem("Import Paste", ResourceLoader.getImageIcon("toolbar/menu/import_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        menu.add(pasteItem);
        menu.add(pasteImportItem);

        pasteTool = new TButtonAndDropTools("Paste", false, pasteItem, menu);
        pasteTool.getTButton().setMargin(new Insets(3, 6, 3, 5));
    }
}
