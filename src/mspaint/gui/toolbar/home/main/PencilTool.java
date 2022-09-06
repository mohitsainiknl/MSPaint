package mspaint.gui.toolbar.home.main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;
import java.awt.BasicStroke;

import mspaint.helper.Point;

public class PencilTool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static BasicStroke stroke;

    public static void setGraphics(Graphics2D graphics) {
        PencilTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        PencilTool.color = color;
    }

    public static void setPaint(Paint paint) {
        PencilTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        PencilTool.stroke = new BasicStroke(size);
    }

    public static void draw(Point startPoint, Point endPoint) {
        if(graphics != null) {
            graphics.setColor(color);
            graphics.setPaint(paint);
            graphics.setStroke(stroke);
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}
