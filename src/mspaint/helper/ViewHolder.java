package mspaint.helper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import com.pump.image.thumbnail.BasicThumbnail.Shadow;

public class ViewHolder extends JPanel {
    private final int[] opacity = {10, 10, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 0, 0};
    private final int sLength = opacity.length-10;
    public double scale = 1.0;

    public ViewHolder(View view) {
        super();
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, sLength, sLength));
        add(view);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Shadow shadow = new Shadow(opacity, 0, 1);
        shadow.paint(g2d, 0, 0, getWidth(), getHeight());
    }
}
