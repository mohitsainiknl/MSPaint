package mspaint.gui.toolbar.home.brushes;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;

import mspaint.helper.Point;
import com.pump.awt.BrushStroke;

public class WatercolorBrushTool {
    private static Graphics2D graphics;
    private static Color color_bg, color_fg;
    private static Paint paint;
    private static int size;

    public static void setGraphics(Graphics2D graphics) {
        WatercolorBrushTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        WatercolorBrushTool.color_bg = color;
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int light = 20;
        WatercolorBrushTool.color_fg = new Color(
            (red+light > 255)? red-light : red+light,
            (green+light > 255)? green-light : green+light,
            (blue+light > 255)? blue-light : blue+light, 80);
    }

    public static void setPaint(Paint paint) {
        WatercolorBrushTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        WatercolorBrushTool.size = size;
    }

    public static void draw(Point startPoint, Point endPoint) {
        if(graphics != null) {
            graphics.setPaint(paint);
 
            graphics.setColor(color_bg);
            graphics.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
 
            graphics.setColor(color_fg);
            graphics.setStroke(new BrushStroke(size+2, 0.78f));
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}
