package mspaint.gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Graphics2D; 
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import mspaint.action.SideBarAction;
import mspaint.action.SideBarResizer;
import mspaint.action.home.LayersShortcutAction;
import mspaint.action.home.ShapeAction;
import mspaint.action.view.FloatCanvasAction;
import mspaint.action.view.FullScreenAction;
import mspaint.action.view.ShowAndHideAction;
import mspaint.action.view.ZoomAction;
import mspaint.action.ClipboardAction;
import mspaint.action.MainViewAction;
import mspaint.gui.menubar.FileMenu;
import mspaint.gui.menubar.SideMenuButton;
import mspaint.gui.menubar.TopMenuButton;
import mspaint.gui.sidebar.FilterSideBar;
import mspaint.gui.sidebar.LayerSideBar;
import mspaint.gui.statusbar.ZoomSlider;
import mspaint.gui.toolbar.HomeToolBar;
import mspaint.gui.toolbar.TextToolBar;
import mspaint.gui.toolbar.ViewToolBar;
import mspaint.helper.DropTools;
import mspaint.helper.Layer;
import mspaint.helper.ResourceLoader;
import mspaint.helper.View;
import mspaint.helper.ViewHolder;
import mspaint.helper.TButton;
import mspaint.initial_info.InitialValues;
import mspaint.style.Style;
import mspaint.theme.Theme;
import others.Rule;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;
import com.gmail.mohitsainiknl2.util.gui.StatusBarPanel;

/**
 * GUIHandler
 */
public class GUIHandler {
    public final Style style;
    public final Theme theme;
    public JFrame frame;

    public Rule clmRule;
    public Rule rowRule;
    public JPanel viewport;
    public ViewHolder holder; 
    public JPanel imageViewPort;
    public JScrollPane scrollpane;
    public BufferedImage mainImage;
    public List<Layer> layers;
    public BufferedImage tempImage, shapeImage;
    
    public int activeSize;
    public Color activeColor1;
    public Color activeColor2;
    public String activeToolName;
    public Graphics2D graphics, tempGraphics, shapeGraphics;
    public View view;
    public BufferedImage cpyImage;
    public MainViewAction viewAction;
    public boolean isSelectionActive;

    private JPanel topPanel;
    private JPanel topMenubar;
    private FileMenu fileMenu;
    private TopMenuButton homeButton, viewButton, textButton;
    private JPanel toolbar;
    private CardLayout toolCard;
    public HomeToolBar homeToolBar;
    public ViewToolBar viewToolBar;
    public TextToolBar textToolBar;

    private JPanel sidePanel;
    private GridBagPanel sideMenubar;
    private JPanel sidebar;
    private CardLayout sideCard;
    private SideMenuButton layerButton, filterButton;
    public LayerSideBar layerSideBar;
    private FilterSideBar filterSideBar;

    public StatusBarPanel statusbar;
    public JLabel pointerLocLabel, selectionResoLabel;
    private JLabel imageResoLabel, imageSizeLabel;
    public ZoomSlider zoomSlider;

    public GUIHandler(Style style, Theme theme) {
        this.style = style;
        this.theme = theme;
        UIManager.put("Button.font", style.getFont());
        UIManager.put("Label.font", style.getFont());
    }

    public void handle() {
        frame = new JFrame("MSPaint");

        prepareViewport();
        prepareTopPanel();
        prepareStatusbar();
        {
            frame.getContentPane().add(topPanel, BorderLayout.NORTH);
            frame.getContentPane().add(viewport, BorderLayout.CENTER);
            frame.getContentPane().add(statusbar, BorderLayout.SOUTH);
        }
        frame.setSize(InitialValues.frameSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void prepareViewport() {
        layers = new ArrayList<>(1);

        mainImage = new BufferedImage(InitialValues.newImageWidth, InitialValues.newImageHeight, BufferedImage.TYPE_INT_ARGB);
        graphics = mainImage.createGraphics();
        layers.add(new Layer(mainImage, true));

        tempImage = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        tempGraphics = tempImage.createGraphics();
        shapeImage = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        shapeGraphics = shapeImage.createGraphics();
        view = new View(this);

        holder = new ViewHolder(view);
        imageViewPort = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        imageViewPort.add(holder, gbc);
        imageViewPort.setBackground(theme.getViewportBgColor());

        scrollpane = new JScrollPane(imageViewPort);
		scrollpane.setBorder(new LineBorder(Color.WHITE, 0));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.getVerticalScrollBar().setUnitIncrement(20);
        {
            clmRule = new Rule(Rule.HORIZONTAL, true);
            rowRule = new Rule(Rule.VERTICAL, true);
            clmRule.setPreferredWidth(5);
            rowRule.setPreferredHeight(5);
        }
        viewport = new JPanel(new BorderLayout());
        viewport.setOpaque(false);
        viewport.add(scrollpane, BorderLayout.CENTER);
        prepareSidePanel();
        viewport.add(sidePanel, BorderLayout.EAST);
    }

    public void setMainImage(BufferedImage image) {
        mainImage = ClipboardAction.clone(image);
        graphics = mainImage.createGraphics();
        tempImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        shapeImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        view = new View(this);
        holder.removeAll();
        holder.add(view);
        holder.updateUI();
    }

    private void prepareTopPanel() {
        prepareToolbar();
        prepareMenubar();
        topPanel = new JPanel(new BorderLayout());
        topPanel.add(topMenubar, BorderLayout.NORTH);
        topPanel.add(toolbar, BorderLayout.CENTER);
    }

    private void prepareToolbar() {
        homeToolBar = new HomeToolBar(this, style, theme, view);
        viewToolBar = new ViewToolBar(style, theme);
        textToolBar = new TextToolBar(this, style, theme);
        {
            viewAction =  new MainViewAction(this);
            view.addMouseListener(viewAction);
            view.addMouseMotionListener(viewAction);
            new ShapeAction(this, homeToolBar.shapeTools);
        }
        TButton layers = viewToolBar.showAndHideTools.layers;
        TButton filters = viewToolBar.showAndHideTools.filters;
        DropTools layersShortcut = homeToolBar.layersToolShortcut.layersShortcut;

        layers.addActionListener(new SideBarAction(layerButton, filterButton, sidePanel, layersShortcut, this));
        filters.addActionListener(new SideBarAction(layerButton, filterButton, sidePanel, null, this));
        layersShortcut.addActionListener(new LayersShortcutAction(layers));
        viewToolBar.floatCanvasTool.floatCanvas.addActionListener(new FloatCanvasAction(this));
        viewToolBar.showAndHideTools.gridlines.addActionListener(new ShowAndHideAction(this));
        viewToolBar.showAndHideTools.statusbar.addActionListener(new ShowAndHideAction(this));
        viewToolBar.showAndHideTools.rulers.addActionListener(new ShowAndHideAction(this));
        viewToolBar.zoomTools.zoomIn.addActionListener(new ZoomAction(this, "In"));
        viewToolBar.zoomTools.zoomOut.addActionListener(new ZoomAction(this, "Out"));
        viewToolBar.zoomTools.defaultZoom.addActionListener(new ZoomAction(this, "default"));
        viewToolBar.fullScreenTool.fullScreen.addActionListener(new FullScreenAction(this));

        homeToolBar.imageTools.selectAllItem.addActionListener(viewAction);
        homeToolBar.imageTools.deleteItem.addActionListener(viewAction);
        homeToolBar.imageTools.cropTool.addActionListener(new ClipboardAction(this));
        homeToolBar.clipboardTools.cutTool.addActionListener(new ClipboardAction(this));
        homeToolBar.clipboardTools.copyTool.addActionListener(new ClipboardAction(this));
        homeToolBar.clipboardTools.pasteTool.getTButton().addActionListener(new ClipboardAction(this));
        homeToolBar.clipboardTools.pasteItem.addActionListener(new ClipboardAction(this));
        homeToolBar.clipboardTools.pasteImportItem.addActionListener(new ClipboardAction(this));

        toolCard = new CardLayout();
        toolbar = new JPanel(toolCard);
        toolbar.add("home", homeToolBar);
        toolbar.add("view", viewToolBar);
        toolbar.add("text", textToolBar);
    }

    private void prepareMenubar() {
        fileMenu = new FileMenu("File", style, theme, this);
        homeButton = new TopMenuButton("Home", style, theme, this);
        viewButton = new TopMenuButton("View", style, theme, this);
        textButton = new TopMenuButton("Text", style, theme, this);
        {
            topMenubar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            topMenubar.setBackground(Color.WHITE);
            topMenubar.add(fileMenu);
            topMenubar.add(homeButton);
            {
                JPanel hold = new JPanel(new FlowLayout(1, 2, 0));
                hold.setBackground(Color.WHITE);
                hold.add(viewButton);
                hold.add(textButton);
                topMenubar.add(hold);
            }
        }
        homeButton.setSelected(true);
        setTopMenuSelection(true, "Home");
    }

    public void setTopMenuSelection(boolean isSelected, String menuName) {
        toolCard.show(toolbar, menuName.toLowerCase()); //<--- Flip the ToolBar Card

        if(menuName.equals("Home")) {
            if(isSelected) {
                viewButton.setSelected(false);
                textButton.setSelected(false);
            }
        }
        else if(menuName.equals("View")) {
            if(isSelected) {
                homeButton.setSelected(false);
                textButton.setSelected(false);
            }
        }
        else if(menuName.equals("Text")) {
            if(isSelected) {
                viewButton.setSelected(false);
                homeButton.setSelected(false);
            }
        }
    }

    
    private void prepareSidePanel() {
        layerSideBar = new LayerSideBar(this, style, theme); 
        filterSideBar = new FilterSideBar(style, theme); 
        sideCard = new CardLayout();
        sidebar = new JPanel(sideCard);
        sidebar.setOpaque(false);
        sidebar.setPreferredSize(new Dimension(InitialValues.sidePanelWith, sidebar.getHeight()));
        sidebar.add("layer", layerSideBar);
        sidebar.add("filter", filterSideBar);

        layerButton = new SideMenuButton("Layer", style, theme, this);
        filterButton = new SideMenuButton("Filter", style, theme, this);
        {
            sideMenubar = new GridBagPanel(){
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(theme.getBorderColor());
                    g.drawLine(0, 0, 0, getHeight());
                    g.setColor(theme.getBorderColor());
                    g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
                }
            };
            sideMenubar.setBackground(Color.WHITE);
            sideMenubar.setGridWeight(1.0, 1.0);
            sideMenubar.add(layerButton, 0, 0);
            sideMenubar.add(filterButton, 1, 0);
        }
        layerButton.setSelected(true);
        setSideMenuSelection(true, "Layer");

        sidePanel = new JPanel(new BorderLayout());
        sidePanel.add(sideMenubar, BorderLayout.NORTH);
        {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(theme.getBgColor());
            panel.add(getResizer(), BorderLayout.WEST);
            panel.add(sidebar, BorderLayout.CENTER);

            sidePanel.add(panel, BorderLayout.CENTER);
            sidePanel.setVisible(false);
            layerButton.setVisible(false);
            filterButton.setVisible(false);
        }
    }
    
    public void setSideMenuSelection(boolean isSelected, String menuName) {
        sideCard.show(sidebar, menuName.toLowerCase()); //<--- Flip the SideBar Card

        if(menuName.equals("Layer")) {
            if(isSelected) {
                filterButton.setSelected(false);
            }
        }
        else if(menuName.equals("Filter")) {
            if(isSelected) {
                layerButton.setSelected(false);
            }
        }
    }

    public JPanel getResizer() {
        JButton resizer = new JButton(){
            @Override
            public void paintComponent(Graphics g) {
                final int y = getHeight()/2;
                int x = getWidth()/2;
                int opacity = 250;
                --x;
                g.setColor(new Color(75, 95, 120, opacity -= 30));
                g.drawLine(x, y, x, y);
                ++x;
                g.setColor(new Color(75, 95, 120, opacity -= 30));
                g.drawLine(x, y-1, x, y+1);
                ++x;
                g.setColor(new Color(75, 95, 120, opacity -= 30));
                g.drawLine(x, y-2, x, y+2);
                ++x;
                g.setColor(new Color(75, 95, 120, opacity -= 30));
                g.drawLine(x, y-3, x, y+3);
            }
        };
        resizer.setFocusable(false);
        resizer.setBorderPainted(false);
        resizer.setOpaque(false);
        resizer.setMargin(new Insets(10, 3, 10, 3));
        resizer.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        resizer.addMouseMotionListener(new SideBarResizer(sidePanel));

        JPanel panel = new JPanel(new GridBagLayout()){
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(theme.getBorderColor());
                g.drawLine(0, 0, 0, getHeight());
            }
        };
        panel.add(resizer);
        panel.setOpaque(false);
        return panel;
    }

    private void setupStatusBar() {
        final int height = 25;
        statusbar.setHeight(height);
        statusbar.setGap(5, 60);
        statusbar.setLineColor(theme.getBorderColor());
        statusbar.setBackground(theme.getBgColor());
    }

    private void prepareStatusbar() {
        final Font zoomFont = new Font("00", Font.PLAIN, 12);
        pointerLocLabel = new JLabel("00", ResourceLoader.getImageIcon("statusbar/pointer_location_symbol.png"), JLabel.CENTER);
        selectionResoLabel = new JLabel("00", ResourceLoader.getImageIcon("statusbar/selection_reso_symbol.png"), JLabel.CENTER);
        imageResoLabel = new JLabel("00", ResourceLoader.getImageIcon("statusbar/image_reso_symbol.png"), JLabel.CENTER);
        imageSizeLabel = new JLabel("00", ResourceLoader.getImageIcon("statusbar/image_size_symbol.png"), JLabel.CENTER);
        {
            pointerLocLabel.setFont(zoomFont);
            selectionResoLabel.setFont(zoomFont);
            imageResoLabel.setFont(zoomFont);
            imageSizeLabel.setFont(zoomFont);
        }
        statusbar = new StatusBarPanel();
        setupStatusBar();
        statusbar.addIt(pointerLocLabel, StatusBarPanel.Align.LEFT, true);
        statusbar.addIt(selectionResoLabel, StatusBarPanel.Align.LEFT, true);
        statusbar.addIt(imageResoLabel, StatusBarPanel.Align.LEFT, true);
        statusbar.addIt(imageSizeLabel, StatusBarPanel.Align.LEFT, true);

        zoomSlider = new ZoomSlider(this, zoomFont);
        statusbar.setGap(30, 5);
        GridBagPanel panel = new GridBagPanel(new Insets(0, 7, 0, 0), GridBagPanel.HORIZONTAL, GridBagPanel.CENTER);
        panel.setBackground(theme.getBgColor());
        {
            panel.add(zoomSlider.getZoomStatus(), 0, 0);
            panel.add(zoomSlider.getZoomOutButton(), 1, 0);
            panel.add(zoomSlider, 2, 0);
            panel.add(zoomSlider.getZoomInButton(), 3, 0);
        }
        statusbar.addIt(panel, StatusBarPanel.Align.RIGHT, true);
        {
            final int width = 80;
            final int height = 25;
            pointerLocLabel.setPreferredSize(new Dimension(width, height));
            selectionResoLabel.setPreferredSize(new Dimension(width, height));
            imageResoLabel.setPreferredSize(new Dimension(width, height));
            imageSizeLabel.setPreferredSize(new Dimension(width, height));

            pointerLocLabel.setHorizontalAlignment(JLabel.LEFT);
            selectionResoLabel.setHorizontalAlignment(JLabel.LEFT);
            imageResoLabel.setHorizontalAlignment(JLabel.LEFT);
            imageSizeLabel.setHorizontalAlignment(JLabel.LEFT);
        }
    }

}