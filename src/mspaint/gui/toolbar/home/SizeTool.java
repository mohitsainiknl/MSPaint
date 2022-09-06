package mspaint.gui.toolbar.home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import mspaint.gui.toolbar.home.size.SizeButton;
import mspaint.helper.DropTools;
import mspaint.helper.GroupTButtons;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TPopupMenu;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class SizeTool extends GridBagPanel{
    private GroupTButtons group;
    private int length;
    private DropTools sizeTool;
    private GridBagPanel panel;
    private TPopupMenu menu;
    
    public SizeTool() {
        super();
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        menu = new TPopupMenu();
        length = 0;
        group = new GroupTButtons();
        panel = new GridBagPanel();
        panel.setGridWeight(1.0, 1.0);
        menu.add(panel);

        sizeTool = new DropTools ("Size", ResourceLoader.getImageIcon("toolbar/size_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.VERTICAL);
        sizeTool.addPopUpMenu(menu);
        add(sizeTool, 0, 1);
        {
            JLabel gapTop = new JLabel();
            gapTop.setPreferredSize(new Dimension(2, 0));
            add(gapTop, 0, 0);

            JLabel gapBottom = new JLabel();
            setGridWeight(1.0, 1.0);
            add(gapBottom, 0, 2);
        }
    }

    public void resetButtonList() {
        length = 0;
        group = new GroupTButtons();
        panel.removeAll();
        panel = new GridBagPanel();
        panel.setGridWeight(1.0, 1.0);
        menu.add(panel);
    }

    public void addSizeButton(SizeButton button) {
        panel.add(button, 0, length++);
        group.add(button);
        button.addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.setVisible(false);
        }

    }
}
