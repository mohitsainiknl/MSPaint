package mspaint.gui.toolbar.home.brushes;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;

import mspaint.helper.Point;
import com.pump.awt.BristleStroke;

public class CrayonTool {
    private static Graphics2D graphics;
    private static Color color;
    private static Paint paint;
    private static int size;

    public static void setGraphics(Graphics2D graphics) {
        CrayonTool.graphics = graphics;
    }

    public static void setColor(Color color) {
        CrayonTool.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128);
    }

    public static void setPaint(Paint paint) {
        CrayonTool.paint = paint;
    }

    public static void setStrokeSize(int size) {
        CrayonTool.size = size;
    }

    public static void draw(Point startPoint, Point endPoint) {
        if(graphics != null) {
            graphics.setColor(color);
            graphics.setPaint(paint);
            graphics.setStroke(new BristleStroke(size, 0.78f));
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            graphics.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}
