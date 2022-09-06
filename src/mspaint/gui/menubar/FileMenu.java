package mspaint.gui.menubar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

import mspaint.action.file.FileMenuAction;
import mspaint.gui.GUIHandler;
import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.helper.TMenuSection;
import mspaint.style.Style;
import mspaint.theme.Theme;

public class FileMenu extends FileInvoker implements ActionListener {
    private FilePopupMenu menu;
    private FileButton newFile, openFile, saveFile, saveasFile;
    private FileButton print, setasDB;
    private FileButton about, exit;
    private FileButton pngSave, jpegSave, bmpSave, gifSave, otherSave;
    private GUIHandler gui;
    
    public FileMenu(String text, Style style, Theme theme, GUIHandler gui) {
        super(text);
        this.gui = gui;
        addActionListener(this);
        load_fileMenu();
    }




    public void load_fileMenu() {
        {
            FileInvoker invoker = new FileInvoker(this.getText());
            invoker.addActionListener(this);
            menu = new FilePopupMenu(invoker, getRecentOptionPanel());
        }
        newFile = new FileButton("New", ResourceLoader.getImageIcon("toolbar/file/new_file.png"), false);
        openFile = new FileButton("Open", ResourceLoader.getImageIcon("toolbar/file/open_file.png"), false);
        saveFile = new FileButton("Save", ResourceLoader.getImageIcon("toolbar/file/save_file.png"), false);
        saveasFile = new FileButton("Save as", ResourceLoader.getImageIcon("toolbar/file/saveas_file.png"), true);
        saveasFile.addSeparator();
        print = new FileButton("Print", ResourceLoader.getImageIcon("toolbar/file/print.png"), false);
        setasDB = new FileButton("Set as Desktop Background", ResourceLoader.getImageIcon("toolbar/file/setas_desktop_bg.png"), false);
        setasDB.addSeparator();

        about = new FileButton("About", ResourceLoader.getImageIcon("toolbar/file/about.png"), false);
        exit = new FileButton("Exit", ResourceLoader.getImageIcon("toolbar/file/exit.png"), false);

        FileMenuAction action = new FileMenuAction(gui, menu);
        newFile.addActionListener(action);
        openFile.addActionListener(action);
        saveFile.addActionListener(action);
        saveasFile.addActionListener(action);
        print.addActionListener(action);
        setasDB.addActionListener(action);
        about.addActionListener(action);
        exit.addActionListener(action);

        menu.add(newFile);
        menu.add(openFile);
        menu.add(saveFile);
        menu.add(saveasFile, getSaveasOptionPanel());
        menu.add(print);
        menu.add(setasDB);
        menu.add(about);
        menu.add(exit);

        pngSave.setName("png");
        jpegSave.setName("jpeg");
        bmpSave.setName("bmp");
        gifSave.setName("gif");
        otherSave.setName("other");

        pngSave.addActionListener(action);
        jpegSave.addActionListener(action);
        bmpSave.addActionListener(action);
        gifSave.addActionListener(action);
        otherSave.addActionListener(action);
    }

    private GridBagPanel getRecentOptionPanel() {
        GridBagPanel panel = new GridBagPanel();
        panel.setOpaque(false);
        TMenuSection recent = new TMenuSection("Recent pictures", true);
        recent.setPreferredSize(new Dimension(320, 20));
        panel.add(recent, 0, 0);
        panel.add(new TButton("1. 12998974.jpeg", TButton.Type.NOT_SELECTABLE), 0, 1);
        panel.add(new TButton("2. 12998975.png", TButton.Type.NOT_SELECTABLE), 0, 2);
        panel.add(new TButton("3. 12998976.jpeg", TButton.Type.NOT_SELECTABLE), 0, 3);
        panel.add(new TButton("4. 12998977.jpeg", TButton.Type.NOT_SELECTABLE), 0, 4);
        panel.add(new JLabel(), 0, 15, 1, 1, 1.0, 1.0);

        return panel;
    }

    private GridBagPanel getSaveasOptionPanel() {
        String text;
        text = "<HTML><strong>PNG picture</strong><br>" +
            "Save a photo or drawing with hight quality " +
            "and use it on your computer or on the web.";
        pngSave = new FileButton(text, ResourceLoader.getImageIcon("toolbar/file/png_file.png"), false);

        text = "<HTML><strong>JPEG picture</strong><br>" +
            "Save a photo with good quality and use it" +
            "on your computer, in email, or on the web.";
        jpegSave = new FileButton(text, ResourceLoader.getImageIcon("toolbar/file/jpeg_file.png"), false);

        text = "<HTML><strong>BMP picture</strong><br>" +
            "Save any kind of picture with hight quality" +
            "and use it on your computer.";
        bmpSave = new FileButton(text, ResourceLoader.getImageIcon("toolbar/file/bmp_file.png"), false);

        text = "<HTML><strong>GIF picture</strong><br>" +
            "Save a simple drawing with lower quality" +
            "and use it in email or on the web.";
        gifSave = new FileButton(text, ResourceLoader.getImageIcon("toolbar/file/gif_file.png"), false);

        text = "<HTML><strong>Other formats</strong><br>" +
            "Open the Save As dialog box to select from" +
            "all possible file types.";
        otherSave = new FileButton(text, ResourceLoader.getImageIcon("toolbar/file/other_file.png"), false);
        final int width = 320;
        final int height = 55;
        pngSave.setPreferredSize(new Dimension(width, height));
        jpegSave.setPreferredSize(new Dimension(width, height));
        bmpSave.setPreferredSize(new Dimension(width, height));
        gifSave.setPreferredSize(new Dimension(width, height));
        otherSave.setPreferredSize(new Dimension(width, height));

        GridBagPanel panel = new GridBagPanel();
        panel.setOpaque(false);
        panel.add(new TMenuSection("Save as", true), 0, 0);
        panel.add(pngSave, 0, 1);
        panel.add(jpegSave, 0, 2);
        panel.add(bmpSave, 0, 3);
        panel.add(gifSave, 0, 4);
        panel.add(otherSave, 0, 5);
        panel.add(new JLabel(), 0, 6, 1, 1, 1.0, 1.0);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(menu.isShowing()) {
            menu.setVisible(false);
        }
        else {
            menu.show(this, 0, 0);
        }
    }


    /**
     * OptionButton
     */
    public class FileButton extends TButton {
        private final Color INNER_BORDER_COLOR = new Color(220, 221, 222);
        private boolean hasOptions;
        private boolean hasSeparator;
        
        public FileButton(String text, ImageIcon icon, boolean hasOptions) {
            super(text, icon, TButton.Type.NOT_SELECTABLE);
            this.hasOptions = hasOptions;
            setPreferredSize(new Dimension(217, 44));
            setMargin(new Insets(3, 10, 3, 10));
            setIconTextGap(10);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(hasOptions) {
                final int gap = 10;
                int width = getWidth()- gap;
                final int height = getHeight()/2;

                g.setColor(new Color(75, 95, 120, 255));
                g.drawLine(width, height, width, height);
                --width;
                g.setColor(new Color(75, 95, 120, 235));
                g.drawLine(width, height-1, width, height+1);
                --width;
                g.setColor(new Color(75, 95, 120, 215));
                g.drawLine(width, height-2, width, height+2);
                --width;
                g.setColor(new Color(75, 95, 120, 195));
                g.drawLine(width, height-3, width, height+3);
            }
            if(hasSeparator) {
                final int gap = 50;
                g.setColor(INNER_BORDER_COLOR);
                g.drawLine(gap, getHeight()-1, getWidth(), getHeight()-1);
            }
        }

        public void addSeparator() {
            hasSeparator = true;
        }
    }

}
