package mspaint.helper;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import mspaint.gui.GUIHandler;
import mspaint.gui.sidebar.LayersArea;

import com.gmail.mohitsainiknl2.util.debug.Assertion;

public class GroupTButtons {
    private int length = -1;
    private List<TButton> buttons;
    private GUIHandler gui;
    private LayersArea layersArea;

    public GroupTButtons() {
        buttons = new ArrayList<>(1);
    }
    
    public GroupTButtons(LayersArea layersArea) {
        this.layersArea = layersArea ;
        buttons = new ArrayList<>(1);
    }
    
    public GroupTButtons(GUIHandler gui) {
        this.gui = gui;
        buttons = new ArrayList<>(1);
    }

    public void add(TButton button) {
        buttons.add(button);
        ++length;

        if(button.getType() != TButton.Type.NOT_SELECTABLE) {
            button.addActionListener(new Action(length));
        }
        else {
            Assertion.throwErrorMessage(new Throwable("TButton is not selectable, Unable to group!"));
        }
    }

    private class Action  implements ActionListener {
        private int length;

        public Action(int length) {
            this.length = length;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TButton button = (TButton) e.getSource();
            if(gui != null) {
                gui.activeToolName = button.getButtonText();
                final Cursor cursor = button.getViewCursor();
                if(cursor != null) {
                    gui.view.setCursor(cursor);
                }
                if(gui.isSelectionActive) {
                    gui.viewAction.drawImageSelected();
                }
            }

            if(layersArea != null) {
                LayersArea.LayerButton b = (LayersArea.LayerButton) e.getSource();
                layersArea.activeLayer = b.index;
            }

            if(!button.isSelected()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if(i != length) {
                        TButton b = buttons.get(i);
                        b.setSelected(false);
                        b.repaint();
                    }
                }
            }
        }
    }
}
