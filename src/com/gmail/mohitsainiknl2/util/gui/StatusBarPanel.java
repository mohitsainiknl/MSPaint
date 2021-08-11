/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package com.gmail.mohitsainiknl2.util.gui;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JPanel;

/**
 * <p> StatusBarPanel class help to make a status bar for any type of Project.
 * You can add any type of component on this bar, whether it is JLabel,
 * JButton, JProgressBar, JSlider, etc. </p>
 * <p> Following are the Coustomizations you can done in StatusBarPanel, externally:
 * <ul>
 *      <li> You can set the Height with "setHeight(int)" method. </li>
 *      <li> You can set left-right gap of component with "setGap(int, int)" method. </li>
 *      <li> You set whether you need Line saparater with "setLineNeed(boolean)" method. </li>
 *      <li> And, You can set the Color of line with "setLineColor(Color)" method. </li>
 *      <li> You can set the color of StatusBarPanel, simply, by "setBackground(Color)" method. </li>
 *      <li>  </li>
 * </ul></p>
 * @author Mohit Saini
 */
public class StatusBarPanel extends JPanel {
    private LayoutManager layout;
    private GridBagConstraints Constrants;
    private boolean drawLine = true;
    private Color lineColor = new Color(0, 0, 0, 50);
    private int leftGap;
    private int rightGap;
    private JPanel leftPanel, centerPanel, rightPanel;
    private int leftPanelSize;
    private int centerPanelSize;
    private int rightPanelSize;

    /**
     * This constuctor help to initialize the field with their respective default
     * values.And, set the different internal panels on the main panel.
     */
    public StatusBarPanel() {
        layout = new GridBagLayout();
        Constrants = new GridBagConstraints();

        leftPanel = new JPanel(layout);
        centerPanel = new JPanel(layout);
        rightPanel = new JPanel(layout);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        leftPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        rightPanel.setOpaque(false);
 
        ItemPanel.lineColor = lineColor;
        ItemPanel.drawLine = drawLine;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if(leftPanel != null) {
            leftPanel.setBackground(bg);
            centerPanel.setBackground(bg);
            rightPanel.setBackground(bg);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(drawLine) {
            g.setColor(lineColor);
            g.drawLine(0, 0, getWidth(), 0); //<--- Top Line on StatusBarPanel
        }
    }

    /**
     * setHeight method help to set the height of the statusBar
     * with the help of the parameter passed.
     * @param height the height of the statusBar.
     */
    public void setHeight(int height) {
        setPreferredSize(new Dimension(getWidth(), height));
    }

    /**
     * setGap method help to set left and right of valuedItems on the statusBar
     * with the help of the parameter passed.
     * @param leftGap the left-side-gap of valuedItems.
     * @param rightGap the right-side-gap of valuedItems.
     */
    public void setGap(int leftGap, int rightGap) {
        this.leftGap = leftGap;
        this.rightGap = rightGap;
    }

    /**
     * setLineNeed method help to set the need of Line betweeen components on the statusBar,
     * @param drawLine false if you don't need to draw.
     */
    public void setLineNeed(boolean drawLine) {
        this.drawLine = drawLine;
        ItemPanel.drawLine = drawLine;
    }

    /**
     * setLineColor method help to set the Color of the line betweeen components 
     * @param lineColor the color of line.
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        ItemPanel.lineColor = lineColor;
    }

    /**
     * This add method help to add any component on the statusBar, with it's own customization.
     * @param comp      the component you want to add on the statusBar.
     * @param alignment the alignment of the text on the statusBar.
     */
    public void addIt(Component comp, Align alignment, boolean drawThisLine) {
        setItemOnBar(alignment, comp, drawThisLine);
    }

    /**
     * setOnBar method help to set the item on the statusBar according to the
     * alignment passed as parameter.
     * @param alignment the alignment of the component.
     * @param comp the component you want set.
     */
    private void setItemOnBar(Align alignment, Component comp, boolean drawThisLine) {
        Constrants.fill = GridBagConstraints.VERTICAL;
        Constrants.weighty = 1.0;

        if(alignment == Align.LEFT) {
            ItemPanel compPanel = new ItemPanel(++ leftPanelSize, alignment, drawThisLine);
            compPanel.add(comp, leftGap, rightGap);
            leftPanel.add(compPanel, Constrants);
        }
        else if(alignment == Align.CENTER) {
            ItemPanel compPanel = new ItemPanel(++ centerPanelSize, alignment, drawThisLine);
            compPanel.add(comp, leftGap, rightGap);
            centerPanel.add(compPanel, Constrants);
        }
        else if(alignment == Align.RIGHT) {
            ItemPanel compPanel = new ItemPanel(++ rightPanelSize, alignment, drawThisLine);
            compPanel.add(comp, leftGap, rightGap);
            rightPanel.add(compPanel, Constrants);
            ItemPanel.rightPanelSize = rightPanelSize;
        }
    }

    /**
     * <strong> Don't use this method </strong>, you can't add
     * components on statusBar like that.
     * @param comp ---
     * @return ---
     * {@inheritDoc}
     */
    @Override
    public Component add(Component comp) {
        // Do-Nothing.
        return comp;
    }
    
    /**
     * <strong> Don't use this method </strong>, you can't add
     * components on statusBar like that.
     * @param comp ---
     * @return ---
     * {@inheritDoc}
     */
    @Override
    public Component add(Component comp, int index) {
        // Do-Nothing.
        return comp;
    }
    
    /**
     * <strong> Don't use this method </strong>, you can't add
     * components on statusBar like that.
     * @param comp ---
     * @return ---
     * {@inheritDoc}
     */
    @Override
    public void add(Component comp, Object constraints) {
        super.add(comp, constraints);
    }
    
    /**
     * <strong> Don't use this method </strong>, you can't add
     * components on statusBar like that.
     * @param comp ---
     * @return ---
     * {@inheritDoc}
     */
    @Override
    public void add(Component comp, Object obj, int index) {
        // Do-Nothing.
    }

    /**
     * ItemPanel class is an Internal class which help to make gap and draw line
     * between the components. Every Component is Hold by this ItemPanel.
     */
    private static class ItemPanel extends JPanel{
        private final int itemID;
        private final Align itemAlign;
        private static GridBagLayout layout = new GridBagLayout();
        private static GridBagConstraints constraints = new GridBagConstraints();
        private boolean lineBothSide;
        private boolean drawThisLine;
        static boolean drawLine;
        static Color lineColor;
        static int rightPanelSize;

        /**
         * This constuctor help to initialize the field and setting some styles.
         * @param itemID this the no. on which the component is added on their respective panel (leftPanel/centerPanel/rightPanel).
         * @param itemAlign the type of panel where component is added.(Ex- Align.LEFT, if added on leftPanel).
         */
        public ItemPanel(int itemID, Align itemAlign, boolean drawThisLine) {
            super();
            this.itemID = itemID;
            this.itemAlign = itemAlign;
            this.drawThisLine = drawThisLine;
            setOpaque(false);    //<--- Comment this if you want to see ItemPanel.
            setBackground(new Color(0, 0, 0, 20));
            setLayout(layout);
        }

        /**
         * add method is used to add the component on the ItemPanel, with the help of gaps.
         * @param comp the component you want add on ItemPanel.
         * @param leftGap the left-side-Gap of the component.
         * @param rightGap the right-side-Gap of the component.
         */
        public void add(Component comp, int leftGap, int rightGap) {
            constraints.insets = new Insets(0, leftGap, 0, rightGap);
            add(comp, constraints);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            final int upDownGap = 2;

            if(drawLine && drawThisLine && isNeedLine()) {
                g.setColor(lineColor);
                g.drawLine(getWidth() - 1, upDownGap, getWidth() - 1, getHeight() - upDownGap);
            }
            if(lineBothSide) {
                g.setColor(lineColor);
                g.drawLine(0, upDownGap, 0, getHeight() - upDownGap);
            }
        }

        /**
         * isNeedLine method help to find whether we need to draw line for the
         * component of this ItemPanel.
         * @return true, if we need to draw line.
         */
        private boolean isNeedLine() {
            if(itemAlign == Align.LEFT) {
                return true;
            }
            else if(itemAlign == Align.CENTER) {
                if(itemID == 1) {
                    lineBothSide = true;
                }
                return true;
            }
            else {
                if(itemID == 1) {
                    lineBothSide = true;
                }
                return (itemID == rightPanelSize)? false : true;
            }
        }
    }

    /**
     * Align enumeration help make a data type which will only accept
     * the values LEFT, CENTER or RIGHT.
     */
    public enum Align {
        LEFT,
        CENTER,
        RIGHT
    }
}
