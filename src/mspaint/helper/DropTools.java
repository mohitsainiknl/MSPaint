package mspaint.helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * ToolDropDown
 */
public class DropTools extends TButton {
    private TPopupMenu menu;
    private Alignment align;
    private ImageIcon icon;
    private boolean leftSideArrow;
    private boolean staySelected;

    public DropTools(String name, TButton.Type type, Alignment align) {
        this(name, null, type, align);
    }

    public DropTools(String name, ImageIcon icon, TButton.Type type, Alignment align) {
        super(icon, type);
        this.icon = icon;
        this.align = align;
        initialWork(name);
    }


    private void initialWork(String name) {
        if(align == Alignment.HORIZONTAL) {
            setMargin(new Insets(3, 5, 3, 15));
        }
        else {
            setMargin(new Insets(3, 5, 13, 5));
            if(icon != null) {
                name = "<HTML><p style=\"text-align:center;\">" + name + "</p>";
                setVerticalTextPosition(SwingConstants.BOTTOM);
                setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }
        setText(name);
    }

    public void setLeftSideArrow(boolean leftSideArrow) {
        this.leftSideArrow = leftSideArrow;
    }

    public boolean isLeftSideArrow() {
        return leftSideArrow;
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth();
        final int height = getHeight();

        super.paintComponent(g);

        int x, y;
        if(align == Alignment.HORIZONTAL) {
            x = width - 10;
            y = height / 2;
        }
        else {
            x = width / 2;
            y = height - 10;
        }
        g.setColor(Color.BLACK);
        if(leftSideArrow) {
            g.drawLine(x+0, y+2, x+0, y-2);
            g.drawLine(x+1, y+1, x+1, y-1);
            g.drawLine(x+2, y+0, x+2, y-0);
        }
        else {
            g.drawLine(x-2, y+0, x+2, y+0);
            g.drawLine(x-1, y+1, x+1, y+1);
            g.drawLine(x-0, y+2, x+0, y+2);
        }
    }

    public void setStaySelected(boolean selected) {
        staySelected = selected;
    }

    public void addPopUpMenu(TPopupMenu menu) {
        this.menu = menu;
        menu.addPopupMenuListener(new PopupMenuListener(){

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                mousePressed = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if(!staySelected) {
                    mousePressed = false;
                    repaint();
                }
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(menu != null) {
            menu.show(this, 0, getHeight());
        }
    }

    /**
     * Alignment
     */
    public enum Alignment {
        HORIZONTAL,
        VERTICAL
    }






    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Checking...ToolDropMenu.java");
        JPanel mainPanel = new JPanel();

        // DropTools tool = new DropTools("Paste", false, false);
        DropTools tool = new DropTools("Rotate", ResourceLoader.getImageIcon("toolbar/rotate_tool.png"), TButton.Type.NOT_SELECTABLE, DropTools.Alignment.HORIZONTAL);

        TPopupMenu menu = new TPopupMenu();
        TMenuItem newItem = new TMenuItem("Cut", ResourceLoader.getImageIcon("toolbar/cut_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        TMenuItem openItem = new TMenuItem("Copy", ResourceLoader.getImageIcon("toolbar/copy_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        menu.add(new TMenuSection("Section This", true));
        menu.add(newItem);
        menu.add(openItem);
        TMenuItem newItem2 = new TMenuItem("Cut", ResourceLoader.getImageIcon("toolbar/cut_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        TMenuItem openItem2 = new TMenuItem("Copy", ResourceLoader.getImageIcon("toolbar/copy_tool.png"), TButton.Type.NOT_SELECTABLE, menu);
        menu.add(new TMenuSection("Section Other", false));
        menu.add(newItem2);
        menu.add(openItem2);

        tool.addPopUpMenu(menu);

        mainPanel.add(tool);

        frame.add(mainPanel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}