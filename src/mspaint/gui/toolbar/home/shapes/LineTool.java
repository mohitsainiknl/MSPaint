package mspaint.gui.toolbar.home.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import mspaint.helper.Point;

/**
 * LineTool
 */
public class LineTool {
    private static Graphics2D graphics;
    private static Color[] lineColor = new Color[2];
    private static Paint paint;
    private static Stroke[] lineStroke = new Stroke[2];

    public static void setGraphics(Graphics2D graphics) {
        LineTool.graphics = graphics;
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

    public static void setPaint(Paint paint) {
        LineTool.paint = paint;
    }

    public static void setLineStroke(Stroke[] lineStroke) {
        LineTool.lineStroke = lineStroke;
    }

    public static void draw(Point startPoint, Point endPoint) {
        if(graphics != null) {
            graphics.setPaint(paint);
    
            if(lineStroke[0] != null) {
                graphics.setColor(lineColor[0]);
                graphics.setStroke(lineStroke[0]);
                graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
            if(lineStroke[1] != null) {
                graphics.setColor(lineColor[1]);
                graphics.setStroke(lineStroke[1]);
                graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
        }
    }
}