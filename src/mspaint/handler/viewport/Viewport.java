/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package mspaint.handler.viewport;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mspaint.handler.Handler;

public class Viewport extends JPanel {

    public Viewport(Handler handler) {
        super();
        setBackground(new Color(249, 235, 223));
        setBorder(new LineBorder(new Color(234, 183, 140), 1));
    }
}
