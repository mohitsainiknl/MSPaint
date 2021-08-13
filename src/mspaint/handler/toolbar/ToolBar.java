/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.toolbar;

import java.awt.Dimension;
import javax.swing.JPanel;

import mspaint.handler.Handler;

public class ToolBar extends JPanel {
    
    public ToolBar(Handler handler) {
        super();
        setBackground(handler.theme.toolbarBgColor);
        setPreferredSize(new Dimension(10, 110));
    }
}
