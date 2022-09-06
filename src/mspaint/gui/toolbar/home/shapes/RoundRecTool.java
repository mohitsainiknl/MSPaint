package mspaint.gui.toolbar.home.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import mspaint.helper.Point;

/**
 * RoundRecTool
 */
public class RoundRecTool {
    private static Graphics2D graphics;
    private static Color[] lineColor = new Color[2];
    private static Color[] fillColor = new Color[2];
    private static Paint paint;
    private static Stroke[] lineStroke = new Stroke[2];
    private static Stroke[] fillStroke = new Stroke[2];

    public static void setGraphics(Graphics2D graphics) {
        RoundRecTool.graphics = graphics;
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
        RoundRecTool.paint = paint;
    }

    public static void setLineStroke(Stroke[] lineStroke) {
        RoundRecTool.lineStroke = lineStroke;
    }

    public static void setFillStroke(Stroke[] fillStroke) {
        RoundRecTool.fillStroke = fillStroke;
    }

    public static void draw(Point startPoint, Point endPoint, final int size) {
        if(graphics != null) {
            graphics.setPaint(paint);
            final Point sizePoint = new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y);

            Color color = fillColor[0];
            final int radius = Math.min(sizePoint.x, sizePoint.y)/4 + size;
            if(fillStroke[0] != null) {
                graphics.setColor(color);
                graphics.setStroke(fillStroke[0]);
                graphics.fillRoundRect(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y, radius, radius);
                color = fillColor[1];
            }
            if(fillStroke[1] != null) {
                graphics.setColor(color);
                graphics.setStroke(fillStroke[1]);
                int gap = size/2;
                int r = radius;
                while(gap < Math.min(sizePoint.x, sizePoint.y)) {
                    graphics.drawRoundRect(startPoint.x + gap, startPoint.y + gap, sizePoint.x - 2*gap, sizePoint.y - 2*gap, r, r);
                    r = r-size;
                    gap += size;
                }
            }
    
            if(lineStroke[0] != null) {
                graphics.setColor(lineColor[0]);
                graphics.setStroke(lineStroke[0]);
                graphics.drawRoundRect(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y, radius, radius);
            }
            if(lineStroke[1] != null) {
                graphics.setColor(lineColor[1]);
                graphics.setStroke(lineStroke[1]);
                graphics.drawRoundRect(startPoint.x, startPoint.y, sizePoint.x, sizePoint.y, radius, radius);
            }
        }
    }
}