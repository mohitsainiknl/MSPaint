package mspaint.gui.toolbar.home.brushes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;

import mspaint.helper.Point;
import com.pump.awt.CalligraphyStroke;

public class MarkerTool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static CalligraphyStroke stroke;

    public static void setGraphics(Graphics2D graphics) {
        MarkerTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        MarkerTool.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128);
    }

    public static void setPaint(Paint paint) {
        MarkerTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        MarkerTool.stroke = new CalligraphyStroke(size, (float)Math.toRadians(90));
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
