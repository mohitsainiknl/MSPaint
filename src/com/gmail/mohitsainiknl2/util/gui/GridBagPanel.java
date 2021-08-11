/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */
package com.gmail.mohitsainiknl2.util.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Component;
import java.io.Serial;

import javax.swing.JPanel;

import com.gmail.mohitsainiknl2.util.debug.Assertion;

/**
 * <code>GridBagPanel</code> is a convenient way of using <code>gridbaglayout</code> as the layout of the <code>JPanel</code>.
 * Basically, this class make a JPanel and set the gridbaglayout on it and take Constraints from the user at the time of
 * initialization or mutually with the help of object.
 * <p> This will help to reduce the length and complexity of the code, while making a layout with many no. of components on it.
 * Hence, This class makes the gridBagLayout more powerful in the java.
 * 
 * @author Mohit Saini
 */
public class GridBagPanel extends JPanel {

    /**
	 * This is the serialVersionUID just for the compatibility reasons.
	 */
    @Serial
	private static final long serialVersionUID = -8973520301847179288L;

    public static final int NORTH = GridBagConstraints.NORTH;
    public static final int SOUTH = GridBagConstraints.SOUTH;
    public static final int EAST = GridBagConstraints.EAST;
    public static final int WEST = GridBagConstraints.WEST;
    public static final int CENTER = GridBagConstraints.CENTER;
    public static final int BOTH = GridBagConstraints.BOTH;
    public static final int NONE = GridBagConstraints.NONE;
    public static final int VERTICAL = GridBagConstraints.VERTICAL;
    public static final int HORIZONTAL = GridBagConstraints.HORIZONTAL;
    private final GridBagConstraints gbc;
    private Insets insets;
    private int fill;
    private int anchor;
    private int ipadx;
    private int ipady;
    private int gridwidth;
    private int gridheight;
    private double weightx;
    private double weighty;

    /**
     * Creates a new GridBagPanel with zero insets, fill both, anchor center, ipadx zero and ipady is also zero
     */
    public GridBagPanel() {
        this(new Insets(0, 0, 0, 0), BOTH, CENTER, 0, 0);
    }

    /**
     * Creates a new GridBagPanel with fill both, anchor center, ipadx zero and ipady zero
     * @param insets the Insets among grids of the layout
     */
    public GridBagPanel(Insets insets) {
        this(insets, BOTH, CENTER, 0, 0);
    }

    /**
     * Creates a new GridBagPanel with ipadx zero and ipady also zero
     * @param insets the Insets among grids of the layout
     * @param fill the stretching behavior of the component inside the grid
     * @param anchor the position of the component inside the grid
     */
    public GridBagPanel(Insets insets, int fill, int anchor) {
        this(insets, fill, anchor, 0, 0);
    }

    /**
     * Creates a new GridBagPanel with the help of parameters
     * @param insets the Insets among grids of the layout
     * @param fill the stretching behavior of the component inside the grid
     * @param anchor the position of the component inside the grid
     * @param ipadx the padding X-axis of the grid to set component
     * @param ipady the padding Y-axis of the grid to set component
     */
    public GridBagPanel(Insets insets, int fill, int anchor, int ipadx, int ipady){ 
        super();
        if(insets != null) {
            this.insets = insets;
        }
        else {
            this.insets = new Insets(0, 0, 0, 0);
        }
        this.fill = fill;
        this.anchor = anchor;
        this.ipadx = ipadx;
        this.ipady = ipady;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0.0;
        weighty = 0.0;
        GridBagLayout gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
    }

    /**
     * This method is use to set the Insets of the grid in the layout
     * @param top the top side gap
     * @param left the left side gap
     * @param bottom the bottom side gap
     * @param right the right side gap
     */
    public void setInsets(int top, int left, int bottom, int right) {
        insets = new Insets(top, left, bottom, right);
    }

    /**
     * This method is use to set the fill behavior of the component inside the grid
     * @param fill the stretching side
     */
    public void setFill(int fill) {
        this.fill = fill;
    }

    /**
     * This method is use to set the anchor for the component in the grid
     * @param anchor the position of the component
     */
    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    /**
     * This method is use to set the internal padding of the grid
     * @param ipadx the internal padding along X-axis
     * @param ipady the internal padding along Y-axis
     */
    public void setIPadding(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
    }

    /**
     * This method is use to set the dimension of the grid
     * @param width the width of grid
     * @param height the height of grid
     */
    public void setGridDimension(int width, int height) {
        gridwidth = width;
        gridheight = height;
    }

    /**
     * This method is use to set the weight of the grid
     * @param weightx the weight of grid along X-axis
     * @param weighty the weight of grid along Y-axis
     */
    public void setGridWeight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
    }

    /**
     * This method is use to add Component on the GridBagPanel with the help of grid dimension
     * @param comp the component we need to add
     * @param gridx same as the GridBagConstraints.gridx
     * @param gridy same as the GridBagConstraints.gridy
     * @return the same component as in parameter
     */
    public Component add(Component comp, int gridx, int gridy) {
        return add(comp, gridx, gridy, gridwidth, gridheight, weightx, weighty);
    }

    /**
     * This method is use to add component on the GridBagPanel with the help of constraints
     * @param comp the component we need to add
     * @param gridx same as the GridBagConstraints.gridx
     * @param gridy same as the GridBagConstraints.gridy
     * @param gridwidth same as the GridBagConstraints.gridwidth
     * @param gridheight same as the GridBagConstraints.gridheight
     * @return the same component as in parameter
     */
    public Component add(Component comp, int gridx, int gridy, int gridwidth, int gridheight) {
        return add(comp, gridx, gridy, gridwidth, gridheight, weightx, weighty);
    }

    /**
     * This method is use to add component on the GridBagPanel with the help of constraints, as parameterized
     * @param comp the component we need to add
     * @param gridx same as the GridBagConstraints.gridx
     * @param gridy same as the GridBagConstraints.gridy
     * @param gridwidth same as the GridBagConstraints.gridwidth
     * @param gridheight same as the GridBagConstraints.gridheight
     * @param weightx same as the GridBagConstraints.weightx
     * @param weighty same as the GridBagConstraints.weighty
     * @return the same component as in parameter
     */
    public Component add(Component comp, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
        gbc.insets = insets;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridheight = gridheight;
        gbc.gridwidth = gridwidth;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        if(comp != null) {
            add(comp, gbc);
        }
        else {
            Assertion.throwErrorMessage(new Throwable("The added component is NULL, unable to add...."));
        }
        return comp;
    }
}