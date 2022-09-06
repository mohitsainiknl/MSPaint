package mspaint.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class TMenuSection extends JLabel {
    private static final Color BACK_COLOR = new Color(245, 246, 247);
    private static final Color BORDER_COLOR = new Color(217, 218, 219);
    private boolean isFirst;
    
    public TMenuSection(String text, boolean isFirst) {
        super(text);
        this.isFirst = isFirst;
        setBorder(new EmptyBorder(0, 10, 0, 0));
        setFont(new Font("", Font.BOLD, getFont().getSize()));
        setPreferredSize(new Dimension(getWidth(), getFont().getSize()+16 ));
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight()-1;

        g.setColor(BACK_COLOR);
        g.fillRect(0, 0, width, height);

        g.setColor(BORDER_COLOR);
        if(!isFirst) {
            g.drawLine(0, 0, width, 0);
        }
        g.drawLine(0, height, width, height);

        super.paintComponent(g);
    }
}
