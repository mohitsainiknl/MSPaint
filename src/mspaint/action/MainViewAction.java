package mspaint.action;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

import mspaint.action.home.ShapeAction;
import mspaint.gui.GUIHandler;
import mspaint.helper.Point;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.gui.toolbar.home.ColorPlateTool.ColorButton;
import mspaint.gui.toolbar.home.Image.RecSelectionTool;
import mspaint.gui.toolbar.home.brushes.AirBrushTool;
import mspaint.gui.toolbar.home.brushes.CalligraphyBrush1Tool;
import mspaint.gui.toolbar.home.brushes.CalligraphyBrush2Tool;
import mspaint.gui.toolbar.home.brushes.ColorPencilTool;
import mspaint.gui.toolbar.home.brushes.CrayonTool;
import mspaint.gui.toolbar.home.brushes.LineBrushTool;
import mspaint.gui.toolbar.home.brushes.MarkerTool;
import mspaint.gui.toolbar.home.brushes.OilBrushTool;
import mspaint.gui.toolbar.home.brushes.WatercolorBrushTool;
import mspaint.gui.toolbar.home.main.PencilTool;

/**
 * Action
 */
public class MainViewAction extends MouseAdapter implements ActionListener {
    private Point startPoint, endPoint;
    private GUIHandler gui;
    public boolean isDragging;
    public boolean isDraggingImage;
    public boolean isInSelection;
    public BufferedImage sImage;
    private Point imgLoc, temps, tempe;
    private TButton cutTool, copyTool, cropTool;

    public MainViewAction(GUIHandler gui) {
        this.gui = gui;
        startPoint = new Point(0, 0);
        endPoint = new Point(0, 0);
        cutTool = gui.homeToolBar.clipboardTools.cutTool;
        copyTool = gui.homeToolBar.clipboardTools.copyTool;
        cropTool = gui.homeToolBar.imageTools.cropTool;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        final int x = (int) (e.getX() * (1/gui.view.scale));
        final int y = (int) (e.getY() * (1/gui.view.scale));
        endPoint.resetPoint(x, y);

        if(!dragIsInBrushTools(gui.graphics, startPoint, endPoint) && !ShapeAction.mouseDragged(startPoint, endPoint)) {
            if(gui.activeToolName.equals("Rectangular Selection Tool") || gui.activeToolName.equals("Free-form Selection Tool")) {
                if(!isDragging && isInSelection) {
                    if(sImage != null) {
                        final int w = sImage.getWidth();
                        final int h = sImage.getHeight();
                        temps = new Point(imgLoc.x + (x - startPoint.x),imgLoc.y + (y - startPoint.y));
                        tempe = new Point(temps.x + w, temps.y + h);

                        clearImage(gui.tempImage);
                        RecSelectionTool.setTempImage(gui.tempImage);
                        RecSelectionTool.drawWithImage(sImage, temps, tempe);
                        isDraggingImage = true;
                    }
                }
                else {
                    clearImage(gui.tempImage);
                    RecSelectionTool.setTempImage(gui.tempImage);
                    RecSelectionTool.draw(startPoint, endPoint);
                    final int w = x - startPoint.x;
                    final int h = y - startPoint.y;
                    gui.selectionResoLabel.setText(w + " ," + h + " px");
                    isDragging = true;
                }
            }
        }

        gui.view.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        final int x = (int) (e.getX() * (1/gui.view.scale));
        final int y = (int) (e.getY() * (1/gui.view.scale));
        gui.pointerLocLabel.setText(x + "," + y + " px");

        if(!gui.activeToolName.equals("Rectangular Selection Tool") &&
           !gui.activeToolName.equals("Free-form Selection Tool") &&
           !moveIsInBrushTools(new Point(x, y))) {

            final int z_width = gui.mainImage.getWidth() / 4;
            final int z_height = gui.mainImage.getHeight() / 4;
            final int w_half = z_width / 2;
            final int h_half = z_height / 2;
            
            if(gui.activeToolName.equals("Zoom Tool")) {
                clearImage(gui.tempImage);
                Graphics2D g = gui.tempImage.createGraphics();
                g.setColor(Color.BLACK);
                g.drawRect(x - w_half, y - h_half, z_width, z_height);
                gui.view.repaint();
            }
        }
        if(gui.isSelectionActive) {
            if(pointInSelection(x, y)) {
                gui.view.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                isInSelection = true;
            }
            else {
                gui.view.setCursor(TButton.getCursor(ResourceLoader.getImage("cursors/null_cursor.png"), new Point(16, 16)));
                isInSelection = false;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        final int x = (int) (e.getX() * (1/gui.view.scale));
        final int y = (int) (e.getY() * (1/gui.view.scale));
        clickIsInBrushTools(new Point(x, y));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final int x = (int) (e.getX() * (1/gui.view.scale));
        final int y = (int) (e.getY() * (1/gui.view.scale));
        startPoint.resetPoint(x, y);
        if(!isInSelection) {
            drawImageSelected();
        }
        if(gui.activeToolName.equals("Color Picker Tool")) {
            int rgb = gui.mainImage.getRGB(x, y);
            Color c = new Color(rgb);
            c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
            ColorButton color1 = gui.homeToolBar.colorPlate.color1Button;
            ColorButton color2 = gui.homeToolBar.colorPlate.color2Button;
            if(color1.isSelected()) {
                gui.activeColor1 = c;
                color1.setColor(c);
                color1.repaint();
            }
            else {
                gui.activeColor2 = c;
                color2.setColor(c);
                color2.repaint();
            }
        }
        else if(gui.activeToolName.equals("Zoom Tool")) {
            if(SwingUtilities.isRightMouseButton(e)) {
                gui.viewToolBar.zoomTools.zoomOut.doClick();
            }
            else if(SwingUtilities.isLeftMouseButton(e)) {
                gui.viewToolBar.zoomTools.zoomIn.doClick();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!ShapeAction.mouseReleased(e)) {
            if(gui.activeToolName.equals("Rectangular Selection Tool") || gui.activeToolName.equals("Free-form Selection Tool")) {
                if(isDragging) {
                    final int x = startPoint.x;
                    final int y = startPoint.y;
                    final int w = endPoint.x - x;
                    final int h = endPoint.y - y;
                    setSelection(x, y, w, h, false);
                    isDragging = false;
                }
                else if(isDraggingImage) {
                    imgLoc = temps;
                    isDraggingImage = false;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gui.pointerLocLabel.setText("");
        if(!isDragging && !isDraggingImage && !gui.isSelectionActive) {
            clearImage(gui.tempImage);
            gui.view.repaint();
        }
    }


    private boolean pointInSelection(int x, int y) {
        final int x1 = temps.x;
        final int y1 = temps.y;
        final int x2 = tempe.x;
        final int y2 = tempe.y;

        if((x1 < x && x < x2) && (y1 < y && y < y2)) {
            return true;
        }
        return false;
    }

    public static void clearImage(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final Color color = new Color(0, 0, 0, 0);

        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){  
                image.setRGB(i, j, color.getRGB());
            }
        }
    }

    public static BufferedImage getAndClearSelection(final BufferedImage image, final int x, final int y, final int w, final int h) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final Color color = new Color(0, 0, 0, 0);
        BufferedImage subImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                if((x < i && i < (x+w)) && (y < j && j < (y+h))) {
                    subImage.setRGB(i, j, image.getRGB(i, j));
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
        try {
            return subImage.getSubimage(x, y, w, h);
        } catch (Exception e) {
            return null;
        }
    }

    public void pasteImage(BufferedImage image) {
        pasteImage(image, 0, 0);
    }

    public void pasteImage(BufferedImage image, final int x, final int y) {
        startPoint.x = x;
        startPoint.y = y;
        final int w = image.getWidth();
        final int h = image.getHeight();
        endPoint.x  = x + w;
        endPoint.y  = y + h;

        drawImageSelected();
        if(!gui.isSelectionActive) {
            imgLoc = new Point(x, y);
            temps = new Point(x, y);
            tempe = new Point(endPoint.x, endPoint.y);
        }
        clearImage(gui.tempImage);
        RecSelectionTool.setTempImage(gui.tempImage);
        RecSelectionTool.draw(startPoint, new Point(startPoint.x + w, startPoint.y + h));

        sImage = ClipboardAction.clone(image);
        gui.tempGraphics.drawImage(sImage, null, x, y);
        gui.view.repaint();
        gui.selectionResoLabel.setText(w + " ," + h + " px");
        gui.isSelectionActive = true;
        cutTool.setTEnabled(true);
        copyTool.setTEnabled(true);
        cropTool.setTEnabled(true);
    }

    public void setSelection(int x, int y, int w, int h, boolean isFull) {
        if(isFull) {
            x = startPoint.x = 0;
            y = startPoint.y = 0;
            w = endPoint.x  = gui.mainImage.getWidth();
            h = endPoint.y = gui.mainImage.getHeight();
        }

        if(!gui.isSelectionActive) {
            imgLoc = new Point(x, y);
            temps = new Point(x, y);
            tempe = new Point(endPoint.x, endPoint.y);
        }
        clearImage(gui.tempImage);
        RecSelectionTool.setTempImage(gui.tempImage);
        if(isFull) {
            RecSelectionTool.draw(startPoint, new Point(endPoint.x-1, endPoint.y-1));
        }
        else {
            RecSelectionTool.draw(startPoint, new Point(startPoint.x + w, startPoint.y + h));
        }

        sImage = getAndClearSelection(gui.mainImage, x, y, w, h);
        gui.tempGraphics.drawImage(sImage, null, x, y);
        gui.selectionResoLabel.setText(w + " ," + h + " px");
        gui.isSelectionActive = true;
        cutTool.setTEnabled(true);
        copyTool.setTEnabled(true);
        cropTool.setTEnabled(true);
    }

    public void drawImageSelected() {
        if(gui.isSelectionActive) {
            if(sImage != null) {
                gui.graphics.drawImage(sImage, null, imgLoc.x, imgLoc.y);
                clearImage(gui.tempImage);
                gui.view.repaint();
            }
            gui.isSelectionActive = false;
            gui.selectionResoLabel.setText("");
            cutTool.setTEnabled(false);
            copyTool.setTEnabled(false);
            cropTool.setTEnabled(false);
        }
    }

    private boolean clickIsInBrushTools(Point point) {
        boolean isIn;
        isIn = dragIsInBrushTools(gui.graphics, point, point);
        gui.view.repaint();
        return isIn;
    }

    private boolean moveIsInBrushTools(Point point) {
        clearImage(gui.tempImage);
        boolean isIn;
        if(!gui.activeToolName.equals("Eraser Tool")) {
            isIn = dragIsInBrushTools(gui.tempGraphics, point, point);
        }
        else {
            final int x = point.x;
            final int y = point.y;
            final int size = gui.activeSize;
            final int half = gui.activeSize / 2;
            gui.tempGraphics.setStroke(new BasicStroke(1));
            gui.tempGraphics.setColor(Color.WHITE);
            gui.tempGraphics.fillRect(x - half, y - half, size, size);
            gui.tempGraphics.setColor(Color.BLACK);
            gui.tempGraphics.drawRect(x - half, y - half, size, size);

            isIn = true;
        }
        gui.view.repaint();
        return isIn;
    }

    private boolean dragIsInBrushTools(Graphics2D graphics, Point startPoint, Point endPoint) {
        if(gui.activeToolName.equals("Pencil Tool")) {
            PencilTool.setGraphics(graphics);
            PencilTool.setColor(gui.activeColor1);
            PencilTool.setStrokeSize(gui.activeSize);
            PencilTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Eraser Tool")) {
            final int x = startPoint.x;
            final int y = startPoint.y;
            final int size = gui.activeSize;
            final int half = gui.activeSize / 2;

            clearImage(gui.tempImage);
            gui.tempGraphics.setStroke(new BasicStroke(1));
            gui.tempGraphics.setColor(Color.WHITE);
            gui.tempGraphics.fillRect(x - half, y - half, size, size);
            gui.tempGraphics.setColor(Color.BLACK);
            gui.tempGraphics.drawRect(x - half, y - half, size, size);

            getAndClearSelection(gui.mainImage, x - half, y - half, size, size);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Line Brush")) {
            LineBrushTool.setGraphics(graphics);
            LineBrushTool.setColor(gui.activeColor1);
            LineBrushTool.setStrokeSize(gui.activeSize);
            LineBrushTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Air Brush")) {
            AirBrushTool.setGraphics(graphics);
            AirBrushTool.setColor(gui.activeColor1);
            AirBrushTool.setStrokeSize(gui.activeSize);
            AirBrushTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Calligraphy Brush 1")) {
            CalligraphyBrush1Tool.setGraphics(graphics);
            CalligraphyBrush1Tool.setColor(gui.activeColor1);
            CalligraphyBrush1Tool.setStrokeSize(gui.activeSize);
            CalligraphyBrush1Tool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Calligraphy Brush 2")) {
            CalligraphyBrush2Tool.setGraphics(graphics);
            CalligraphyBrush2Tool.setColor(gui.activeColor1);
            CalligraphyBrush2Tool.setStrokeSize(gui.activeSize);
            CalligraphyBrush2Tool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Color Pencil")) {
            ColorPencilTool.setGraphics(graphics);
            ColorPencilTool.setColor(gui.activeColor1);
            ColorPencilTool.setStrokeSize(gui.activeSize);
            ColorPencilTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Oil Brush")) {
            OilBrushTool.setGraphics(graphics);
            OilBrushTool.setColor(gui.activeColor1);
            OilBrushTool.setStrokeSize(gui.activeSize);
            OilBrushTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Crayon")) {
            CrayonTool.setGraphics(graphics);
            CrayonTool.setColor(gui.activeColor1);
            CrayonTool.setStrokeSize(gui.activeSize);
            CrayonTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Marker")) {
            MarkerTool.setGraphics(graphics);
            MarkerTool.setColor(gui.activeColor1);
            MarkerTool.setStrokeSize(gui.activeSize);
            MarkerTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        else if(gui.activeToolName.equals("Watercolor Brush")) {
            WatercolorBrushTool.setGraphics(graphics);
            WatercolorBrushTool.setColor(gui.activeColor1);
            WatercolorBrushTool.setStrokeSize(gui.activeSize);
            WatercolorBrushTool.draw(startPoint, endPoint);
            startPoint.resetPoint(endPoint.x, endPoint.y);
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TButton button = (TButton)e.getSource();

        if(gui.activeToolName.equals("Rectangular Selection Tool") || gui.activeToolName.equals("Free-form Selection Tool")) {

            if(button.getText().equals("Select all")) {
                drawImageSelected();
                setSelection(0, 0, 0, 0, true);
            }
            else if(button.getText().equals("Delete")) {
                if(gui.isSelectionActive) {
                    clearImage(sImage);
                    clearImage(gui.tempImage);
                    gui.view.repaint();
                }
            }
        }
    }
}
