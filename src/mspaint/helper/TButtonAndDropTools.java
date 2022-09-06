package mspaint.helper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.gmail.mohitsainiknl2.util.debug.Assertion;

/**
 * ToolButtonAndList
 */
public class TButtonAndDropTools extends JPanel {
    private static final Color SELECTED_BG_COLOR = new Color(201, 224, 247);
    private static final Color HOVER_BG_COLOR = new Color(232, 239, 247);
    private static final Color SELECTED_BORDER_COLOR = new Color(98, 162, 228);
    private static final Color HOVER_BORDER_COLOR = new Color(164, 206, 249);
    private boolean isSelectable;
    private boolean isSelected;
    private boolean paintUrgent;
    private boolean mouseEntered;
    private boolean activeButtonChanged;
    private TButton button;
    private TButton activeButton;
    private DropTools dropMenu;

    public TButtonAndDropTools(String name, boolean isSelectable, TButton activeButton, TPopupMenu menu) {
        super(new BorderLayout());
        this.isSelectable = isSelectable;
        this.activeButton = activeButton;
        initialWork(name, menu);
    }

    private void initialWork(String name, TPopupMenu menu) {
        setOpaque(false);
        {
            TButton.Type type = (isSelectable)? TButton.Type.SELECTABLE : TButton.Type.NOT_SELECTABLE;
            button = new TButton(activeButton.getBigIcon(), type) {
                @Override
                public void setSelected(boolean selected) {
                    super.setSelected(selected);
                    if(selected == false) {
                        setUnSelected();
                    }
                }
            };
            dropMenu = new DropTools(name, type, DropTools.Alignment.VERTICAL);
        }
        button.setPaintHoverBorder(false);
        dropMenu.setPaintHoverBorder(false);

        button.setName("button");
        dropMenu.setName("dropMenu");
        dropMenu.addPopUpMenu(menu);

        button.addMouseListener(new Action());
        dropMenu.addMouseListener(new Action());
        button.addActionListener(new Action());
        menu.addPopupMenuListener(new Action());

        add(button, BorderLayout.NORTH);
        add(dropMenu, BorderLayout.CENTER);
    }

    public TButton getTButton() {
        return button;
    }

    public TButton getDropTools() {
        return dropMenu;
    }

    public void setUnSelected() {
        if(isSelectable && isSelected) {
            isSelected = false;
            repaint();
            dropMenu.setSelected(false);
            dropMenu.repaint();
            activeButton.setSelected(false);
            activeButton.repaint();
        }
    }

    public void setActiveButton(TButton activeButton) {
        if(isSelectable) {
            if(this.activeButton != activeButton) {
                this.activeButton = activeButton;
                Icon icon = activeButton.getBigIcon();
                if(icon == null) {
                    icon = activeButton.getIcon();
                }
                button.setIcon(icon);
            }
            if(activeButton.getType() != TButton.Type.NOT_SELECTABLE) {
                activeButtonChanged = true;
                button.doClick();
                isSelected = true;
                dropMenu.setSelected(true);
                dropMenu.setStaySelected(true);
                dropMenu.repaint();
            }
        }
        else {
            Assertion.throwErrorMessage(new Throwable("the button here is Not Selectable"));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        final int width = getWidth() - 1;
        final int height = getHeight() - 1;

        super.paintComponent(g);
        if((isSelectable && isSelected) || paintUrgent) {
            if(!paintUrgent) {
                g.setColor(SELECTED_BG_COLOR);
                g.drawLine(0, button.getHeight()-1, width, button.getHeight()-1);
            }

            g.setColor(SELECTED_BORDER_COLOR);
            g.drawRect(0, 0, width, height);
            g.drawLine(0, button.getHeight(), width, button.getHeight());
        }
        else if(mouseEntered) {
            g.setColor(HOVER_BG_COLOR);
            g.drawLine(0, button.getHeight()-1, width, button.getHeight()-1);

            g.setColor(HOVER_BORDER_COLOR);
            g.drawRect(0, 0, width, height);
            g.drawLine(0, button.getHeight(), width, button.getHeight());
        }
    }

    public void repaintTheChild(MouseEvent e) {
        String name = ((Component) e.getSource()).getName();

        if(!name.equals("button")) {
            button.repaint();
        }
        else if(!name.equals("dropMenu")) {
            dropMenu.repaint();
        }
    }

    /**
     * Action
     */
    private class Action extends MouseAdapter implements ActionListener, PopupMenuListener {

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseEntered = true;
            repaintTheChild(e);
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
            mouseEntered = false;
            repaintTheChild(e);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!activeButtonChanged) {
                activeButton.doClick();
            }
            activeButtonChanged = false;
        }

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            isSelected = true;
            if(!isSelectable) {
                paintUrgent = true;
            }
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            if(!button.isSelected()) {
                isSelected = false;
                repaint();
                dropMenu.setSelected(false);
                dropMenu.repaint();
            }
            if(!isSelectable) {
                paintUrgent = false;
            }
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
    }





    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Checking ToolDropDown");
        JPanel mainPanel = new JPanel();

        TPopupMenu menu = new TPopupMenu();
        TMenuItem cutItem = new TMenuItem("Cut", ResourceLoader.getImageIcon("toolbar/cut_tool.png"), TButton.Type.SELECTABLE, menu);
        TMenuItem pasteItem = new TMenuItem("Paste", ResourceLoader.getImageIcon("toolbar/paste_tool.png"), TButton.Type.SELECTABLE, menu);
        pasteItem.setBigIcon(ResourceLoader.getImageIcon("toolbar/paste_tool.png"));
        TMenuItem button = new TMenuItem("Button", ResourceLoader.getImageIcon("toolbar/cut_tool.png"), TButton.Type.SELECTABLE, menu);

        menu.add(new TMenuSection("Section This", true));
        menu.add(cutItem);
        menu.add(pasteItem);

        TButtonAndDropTools tool = new TButtonAndDropTools("Paste", true, pasteItem, menu);
        mainPanel.add(tool);
        mainPanel.add(button);

        GroupTButtons group = new GroupTButtons();
        group.add(cutItem);
        group.add(pasteItem);

        GroupTButtons group2 = new GroupTButtons();
        group2.add(tool.button);
        group2.add(button);


        cutItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setActiveButton(cutItem);
            }
        });
        pasteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tool.setActiveButton(pasteItem);
            }
        });


        frame.add(mainPanel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}