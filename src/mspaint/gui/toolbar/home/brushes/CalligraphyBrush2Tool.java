package mspaint.gui.toolbar.home.brushes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;

import mspaint.helper.Point;
import com.pump.awt.CalligraphyStroke;

/**
 * LineTool
 */
public class CalligraphyBrush2Tool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static CalligraphyStroke stroke;

    public static void setGraphics(Graphics2D graphics) {
        CalligraphyBrush2Tool.graphics = graphics;
    }

    public static void setColor(Color color) {
        CalligraphyBrush2Tool.color = color;
    }

    public static void setPaint(Paint paint) {
        CalligraphyBrush2Tool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        CalligraphyBrush2Tool.stroke = new CalligraphyStroke(size, (float)Math.toRadians(45));
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
