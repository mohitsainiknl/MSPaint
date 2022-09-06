package mspaint.gui.toolbar.home.Image;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;

import mspaint.helper.Point;
import mspaint.helper.Texture;

public class RecSelectionTool {
    private static Graphics2D graphics;
    private static Paint paint = Texture.getSelectionPaint();

    public static void setTempImage(BufferedImage image) {
        RecSelectionTool.graphics = image.createGraphics();
    }

    public static void setGraphics(Graphics2D graphics) {
        RecSelectionTool.graphics = graphics;
    }

    public static void draw(Point startPoint, Point endPoint) {
        drawWithImage(null, startPoint, endPoint);
    }

    public static void drawWithImage(BufferedImage image, Point startPoint, Point endPoint) {
        if(graphics != null) {
            final int x1 = startPoint.x;
            final int y1 = startPoint.y;
            final int x2 = endPoint.x;
            final int y2 = endPoint.y;

            if(image != null) {
                graphics.drawImage(image, null, x1, y1);
            }
            graphics.setPaint(paint);
            graphics.drawRect(x1, y1, x2-x1, y2-y1);
        }
    }
}
