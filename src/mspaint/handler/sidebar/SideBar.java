/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mspaint.handler.Handler;

public class SideBar extends JPanel {

    public SideBar(Handler handler) {
        super();
        setBackground(new Color(249, 216, 214));
        setBorder(new LineBorder(new Color(242, 173, 170), 1));
        setPreferredSize(new Dimension(140, 25));
    }
}
