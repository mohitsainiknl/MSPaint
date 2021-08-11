package com.gmail.mohitsainiknl2.util.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Demo_StatusBarPanel {
    private StatusBarPanel statusbar;
    private JFrame frame;
    private JPanel mainPanel;

    public Demo_StatusBarPanel() {
        frame = new JFrame("StatusBar_Demo");
        mainPanel = new JPanel();
        statusbar = new StatusBarPanel();

        statusbar.setHeight(30);
        statusbar.setGap(20, 20);
        statusbar.setBackground(Color.LIGHT_GRAY);

        statusbar.addIt(new JLabel("Label : One"), StatusBarPanel.Align.LEFT, true);
        statusbar.addIt(new JLabel("Label : Two"), StatusBarPanel.Align.CENTER, true);
        statusbar.addIt(new JButton("Button"), StatusBarPanel.Align.RIGHT, true);
        statusbar.addIt(new JLabel("Label : 1"), StatusBarPanel.Align.LEFT, true);
        statusbar.addIt(new JLabel("Label : 2"), StatusBarPanel.Align.CENTER, true);
        statusbar.addIt(new JLabel("Label : 3"), StatusBarPanel.Align.RIGHT, true);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(statusbar, BorderLayout.SOUTH);

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Demo_StatusBarPanel();
    }
}
