package mspaint.helper;

import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;


public class TMenuItem extends TButton {
    private TPopupMenu parent;


    public TMenuItem(String text, TButton.Type type, TPopupMenu parent) {
        this(text, null, type, parent);
    }

    public TMenuItem(ImageIcon icon, TButton.Type type, TPopupMenu parent) {
        this("", icon, type, parent);
    }

    public TMenuItem(String text, ImageIcon icon, TButton.Type type, TPopupMenu parent) {
        super(text, icon, type);
        this.parent = parent;
        initializeWork();
    }

    private void initializeWork() {
        setMargin(new Insets(3, 8, 3, 5));
        setIconTextGap(16);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(parent != null && parent.isShowing()) {
            mouseEntered = false;
            parent.setVisible(false);
        }
    }
}
