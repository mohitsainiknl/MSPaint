package mspaint.gui.toolbar.home.brushes;


import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;

import com.pump.awt.BrushStroke;
import mspaint.helper.Point;

/**
 * LineTool
 */
public class LineBrushTool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static BrushStroke stroke;

    public static void setGraphics(Graphics2D graphics) {
        LineBrushTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        LineBrushTool.color = color;
    }

    public static void setPaint(Paint paint) {
        LineBrushTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        LineBrushTool.stroke = new BrushStroke(size, 0.3f);
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