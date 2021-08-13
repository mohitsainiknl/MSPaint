/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.statusbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mspaint.handler.Handler;

public class StatusBar extends JPanel {

    public StatusBar(Handler handler) {
        super();
        setBackground(new Color(214, 205, 234));
        setBorder(new LineBorder(new Color(176, 159, 215), 1));
        setPreferredSize(new Dimension(10, 25));
    }
}
