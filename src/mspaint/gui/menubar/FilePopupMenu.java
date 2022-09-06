package mspaint.gui.menubar;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.MatteBorder;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class FilePopupMenu extends JPopupMenu {
    private static final Color INNER_BORDER_COLOR = new Color(220, 221, 222);
    private static final Color OUTER_BORDER_COLOR = new Color(132, 146, 166);
    private static final Color ITEM_PANEL_BG_COLOR = new Color(251, 252, 253);
    private static final Color OPTION_PANEL_BG_COLOR = new Color(245, 246, 247);
    private Component invoker;
    private Component options;
    private GridBagPanel itemPanel;
    private JPanel optionPanel;
    private CardLayout optionCard;
    private int length = 0;
    
    public  FilePopupMenu(Component invoker, Component options) {
        super();
        this.invoker = invoker;
        this.options = options;
        initialWork();
    }

    public void initialWork() {
        setBorder(new MatteBorder(1, 0, 1, 1, OUTER_BORDER_COLOR));

        itemPanel = new GridBagPanel(new Insets(0, 0, 0, 0));
        itemPanel.setBackground(ITEM_PANEL_BG_COLOR);
        itemPanel.add(new JLabel(), 0, 15, 1, 1, 1.0, 1.0);


        optionCard = new CardLayout();
        optionPanel = new JPanel(optionCard);
        optionPanel.setBackground(OPTION_PANEL_BG_COLOR);
        optionPanel.setBorder(new MatteBorder(0, 1, 0, 1, INNER_BORDER_COLOR));
        optionPanel.add("0", options);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, -1, 0));
        topPanel.setBackground(OPTION_PANEL_BG_COLOR);
        topPanel.setBorder(new MatteBorder(0, 0, 1, 0, OUTER_BORDER_COLOR));
        topPanel.add(invoker);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(OPTION_PANEL_BG_COLOR);
        bottomPanel.setBorder(new MatteBorder(1, 0, 0, 0, INNER_BORDER_COLOR));
        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getWidth(), 18));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(itemPanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public Component add(JButton item) {
        itemPanel.add(item, 0, ++length);
        item.addMouseListener(new Action(0, this.options));
        return item;
    }

    public Component add(JButton item, Component options) {
        itemPanel.add(item, 0, ++length);
        optionPanel.add(length+"", options);

        item.addMouseListener(new Action(length, options));
        return item;
    }


    /**
     * Action
     */
    public class Action extends MouseAdapter {
        private int length;

        public Action(int length, Component options) {
            this.length = length;
            options.addMouseListener(this);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            optionCard.show(optionPanel, length+"");
        }
    }
}
