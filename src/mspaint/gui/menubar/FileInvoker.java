package mspaint.gui.menubar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;

public class FileInvoker extends JButton {
    
    FileInvoker(String text) {
        super(text);
        setFocusable(false);
        setPreferredSize(new Dimension(58, 23));
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight();
        g.setColor(new Color(25, 121, 202));
        g.fillRect(0, 0, width, height);
        super.paintComponent(g);
    }

}
