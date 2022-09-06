package mspaint.gui.toolbar.home.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import mspaint.helper.Point;

/**
 * OvalTool
 */
public class OvalTool {
    private static Graphics2D graphics;
    private static Color[] lineColor = new Color[2];
    private static Color[] fillColor = new Color[2];
    private static Paint paint;
    private static Stroke[] lineStroke = new Stroke[2];
    private static Stroke[] fillStroke = new Stroke[2];

    public static void setGraphics(Graphics2D graphics) {
        OvalTool.graphics = graphics;
    }

    public static void setLineColor(Color color, int lightness) {
        lineColor[0] = color;
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int light = lightness;
        lineColor[1] = new Color(
            (red+light > 255)? red-light : red+light,
            (green+light > 255)? green-light : green+light,
            (blue+light > 255)? blue-light : blue+light, 80);
    }

    public static void setFillColor(Color color, int lightness) {
        fillColor[0] = color;
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int light = lightness;
        fillColor[1] = new Color(
            (red+light > 255)? red-light : red+light,
            (green+light > 255)? green-light : green+light,
            (blue+light > 255)? blue-light : blue+light, 80);
    }

    public static void setPaint(Paint paint) {
        OvalTool.paint = paint;
    }

    public static void setLineStroke(Stroke[] lineStroke) {
        OvalTool.lineStroke = lineStroke;
    }

    public static void setFillStroke(Stroke[] fillStroke) {
        OvalTool.fillStroke = fillStroke;
    }

    public static void draw(Point startPoint, Point endPoint, final int size) {
        if(graphics != null) {
            graphics.setPaint(paint);
            final Point sizePoint = new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y);

            Color color = fillColor[0];
            if(fillStroke[0] != null) {
                graphics.setColor(color);
                graphics.setStroke(fillStroke[0]);
                graphics.fillOval(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y);
                color = fillColor[1];
            }
            if(fillStroke[1] != null) {
                graphics.setColor(color);
                graphics.setStroke(fillStroke[1]);
                int gap = size/2;
                while(gap < Math.min(sizePoint.x, sizePoint.y)) {
                    graphics.drawOval(startPoint.x + gap, startPoint.y + gap, sizePoint.x - 2*gap, sizePoint.y - 2*gap);
                    gap += size;
                }
            }
    
            if(lineStroke[0] != null) {
                graphics.setColor(lineColor[0]);
                graphics.setStroke(lineStroke[0]);
                graphics.drawOval(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y);
            }
            if(lineStroke[1] != null) {
                graphics.setColor(lineColor[1]);
                graphics.setStroke(lineStroke[1]);
                graphics.drawOval(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y);
            }
        }
    }
}