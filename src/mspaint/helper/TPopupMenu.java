package mspaint.helper;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class TPopupMenu extends JPopupMenu {
    private static final Color MENU_BORDER_COLOR = new Color(220, 221, 222);
    private final int width = 140;
    private GridBagPanel panel;
    private int length = 0;
    
    public TPopupMenu() {
        super();
        initialWork();
    }

    public void initialWork() {
        panel = new GridBagPanel(new Insets(0, 0, 0, 0));
        panel.setBackground(new Color(251, 252, 253));
        panel.setBorder(new LineBorder(new Color(0, 0, 0, 0), 1));
        setBorder(new LineBorder(MENU_BORDER_COLOR, 1));
        setWidth(width);
        super.add(panel);
    }

    public void setWidth(int width) {
        JLabel l = new JLabel();
        l.setPreferredSize(new Dimension(width, 0));
        panel.add(l, 0, 0);
    }

    @Override
    public Component add(Component comp) {
        panel.add(comp, 0, ++length);
        return comp;
    }
}
