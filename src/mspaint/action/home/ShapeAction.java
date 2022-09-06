package mspaint.action.home;

import java.awt.BasicStroke;
import java.awt.Stroke;

import com.pump.awt.BrushStroke;
import java.awt.event.MouseEvent;

import mspaint.action.MainViewAction;
import mspaint.gui.GUIHandler;
import mspaint.gui.toolbar.home.ShapeTools;
import mspaint.gui.toolbar.home.shapes.LineTool;
import mspaint.gui.toolbar.home.shapes.OvalTool;
import mspaint.gui.toolbar.home.shapes.RectangleTool;
import mspaint.gui.toolbar.home.shapes.RoundRecTool;
import mspaint.helper.Point;

public class ShapeAction {
    private static GUIHandler gui;
    private static ShapeTools shapeTools;
    private static boolean isDragging;
    private static Point imgLoc, imgSize;

    public ShapeAction(GUIHandler gui, ShapeTools shapeTools) {
        ShapeAction.gui = gui;
        ShapeAction.shapeTools = shapeTools;
        imgLoc = new Point(0, 0);
        imgSize = new Point(0, 0);
        shapeTools.activeOutline = "No Outline";
        shapeTools.activeFill = "No Outline";
    }

    public static boolean mouseDragged(Point startPoint, Point endPoint) {
        
        if(gui.activeToolName.equals("Line")) {
            imgLoc.resetPoint(startPoint.x, startPoint.y);
            MainViewAction.clearImage(gui.shapeImage);
            Stroke[] lineStroke = getLineStroke(shapeTools.activeOutline, gui.activeSize);

            LineTool.setGraphics(gui.shapeGraphics);
            LineTool.setLineColor(gui.activeColor1, getLightness(shapeTools.activeOutline));
            LineTool.setLineStroke(lineStroke);
            LineTool.draw(startPoint, endPoint);
            isDragging = true;
            return true;
        }
        else if(gui.activeToolName.equals("Oval")) {
            imgLoc.resetPoint(startPoint.x, startPoint.y);
            MainViewAction.clearImage(gui.shapeImage);
            Stroke[] lineStroke;
            if(shapeTools.activeOutline.equals("No Outline")) {
                lineStroke = getLineStroke("", gui.activeSize);
            }
            lineStroke = getLineStroke(shapeTools.activeOutline, gui.activeSize);
            Stroke[] fillStroke = getFillStroke(shapeTools.activeFill, gui.activeSize);

            OvalTool.setGraphics(gui.shapeGraphics);
            OvalTool.setLineColor(gui.activeColor1, getLightness(shapeTools.activeOutline));
            OvalTool.setLineStroke(lineStroke);
            OvalTool.setFillColor(gui.activeColor2, getLightness(shapeTools.activeFill));
            OvalTool.setFillStroke(fillStroke);
            OvalTool.draw(startPoint, endPoint, gui.activeSize);
            isDragging = true;
            return true;
        }
        else if(gui.activeToolName.equals("Rectangle")) {
            imgLoc.resetPoint(startPoint.x, startPoint.y);
            MainViewAction.clearImage(gui.shapeImage);
            Stroke[] lineStroke;
            if(shapeTools.activeOutline.equals("No Outline")) {
                lineStroke = getLineStroke("", gui.activeSize);
            }
            lineStroke = getLineStroke(shapeTools.activeOutline, gui.activeSize);
            Stroke[] fillStroke = getFillStroke(shapeTools.activeFill, gui.activeSize);

            RectangleTool.setGraphics(gui.shapeGraphics);
            RectangleTool.setLineColor(gui.activeColor1, getLightness(shapeTools.activeOutline));
            RectangleTool.setLineStroke(lineStroke);
            RectangleTool.setFillColor(gui.activeColor2, getLightness(shapeTools.activeFill));
            RectangleTool.setFillStroke(fillStroke);
            RectangleTool.draw(startPoint, endPoint, gui.activeSize);
            isDragging = true;
            return true;
        }
        else if(gui.activeToolName.equals("Round Rec")) {
            imgLoc.resetPoint(startPoint.x, startPoint.y);
            MainViewAction.clearImage(gui.shapeImage);
            Stroke[] lineStroke;
            if(shapeTools.activeOutline.equals("No Outline")) {
                lineStroke = getLineStroke("", gui.activeSize);
            }
            lineStroke = getLineStroke(shapeTools.activeOutline, gui.activeSize);
            Stroke[] fillStroke = getFillStroke(shapeTools.activeFill, gui.activeSize);

            RoundRecTool.setGraphics(gui.shapeGraphics);
            RoundRecTool.setLineColor(gui.activeColor1, getLightness(shapeTools.activeOutline));
            RoundRecTool.setLineStroke(lineStroke);
            RoundRecTool.setFillColor(gui.activeColor2, getLightness(shapeTools.activeFill));
            RoundRecTool.setFillStroke(fillStroke);
            RoundRecTool.draw(startPoint, endPoint, gui.activeSize);
            isDragging = true;
            return true;
        }
        return false;
    }

    private static int getLightness(String activeOutlineOrFill) {
        if(activeOutlineOrFill.equals("Oil")) {
            return 60;
        }
        else if(activeOutlineOrFill.equals("Watercolor")) {
            return 30;
        }
        return 0;
    }

    private static Stroke[] getLineStroke(String activeOutline, int size) {
        Stroke[] stroke = new Stroke[2];

        if(activeOutline.equals("No Outline")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = null;
        }
        else if(activeOutline.equals("Solid")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = null;
        }
        else if(activeOutline.equals("Brush")) {
            stroke[0] = new BrushStroke(size, 0.87f);
            stroke[1] = null;
        }
        else if(activeOutline.equals("Oil")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = new BrushStroke(size+2, 0.78f);
        }
        else if(activeOutline.equals("Watercolor")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = new BrushStroke(size+2, 0.78f);
        }

        return stroke;
    }

    private static Stroke[] getFillStroke(String activeFill, int size) {
        Stroke[] stroke = new Stroke[2];

        if(activeFill.equals("No Fill")) {
            stroke[0] = new BasicStroke(size);
            stroke[1] = null;
        }
        else if(activeFill.equals("Solid")) {
            stroke[0] = new BasicStroke(size);
            stroke[1] = null;
        }
        else if(activeFill.equals("Brush")) {
            stroke[0] = null;
            stroke[1] = new BrushStroke(size, 0.87f);
        }
        else if(activeFill.equals("Oil")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = new BrushStroke(size+2, 0.78f);
        }
        else if(activeFill.equals("Watercolor")) {
            stroke[0] = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            stroke[1] = new BrushStroke(size+2, 0.78f);
        }

        return stroke;
    }

    public static boolean mouseReleased(MouseEvent e) {
        final int x1 = (int) (e.getX() * (1/gui.view.scale));
        final int y1 = (int) (e.getY() * (1/gui.view.scale));
        imgSize.resetPoint(x1 - imgLoc.x, y1 - imgLoc.y);

        final int sideGap = gui.activeSize;
        if(isDragging) {
            final int x = Math.abs(imgLoc.x) - sideGap;
            final int y = Math.abs(imgLoc.y) - sideGap;
            final int w = Math.abs(imgSize.x) + 2*sideGap;
            final int h = Math.abs(imgSize.y) + 2*sideGap;
            gui.homeToolBar.imageTools.rectangularSecItem.doClick();

            boolean inBound;
            int gap = 0;
            do {
                inBound = true;
                try {
                    gui.cpyImage = gui.shapeImage.getSubimage(x+gap, y+gap, w-2*gap, h-2*gap);
                } catch (Exception except) {
                    inBound = false;
                    gap++;
                }
            } while(!inBound);

            gui.viewAction.pasteImage(gui.cpyImage, x+gap, y+gap);
            MainViewAction.clearImage(gui.shapeImage);
            isDragging = false;
            return true;
        }
        return false;
    }
}
