package mspaint.gui.toolbar.home.brushes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;

import mspaint.helper.Point;
import com.pump.awt.BristleStroke;

/**
 * LineTool
 */
public class AirBrushTool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static BristleStroke stroke;

    public static void setGraphics(Graphics2D graphics) {
        AirBrushTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        AirBrushTool.color = color;
    }

    public static void setPaint(Paint paint) {
        AirBrushTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        AirBrushTool.stroke = new BristleStroke(size, 0.2f);
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
