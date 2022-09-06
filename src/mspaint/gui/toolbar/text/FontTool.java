package mspaint.gui.toolbar.text;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import mspaint.helper.ResourceLoader;
import mspaint.helper.TButton;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class FontTool extends GridBagPanel {
    private final JLabel label = new JLabel("Font");
    private final Theme theme;
    private SystemFontNameJComboBox nameBox;
    private JComboBox<Integer> sizeBox;
    private TButton bold, italic, underline, strike;
    
    public FontTool(Theme theme) {
        super();
        this.theme = theme;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);
        setupSize();
        nameBox = new SystemFontNameJComboBox();
        nameBox.setBorder(new LineBorder(theme.getBorderColor(), 1));
        bold = new TButton(ResourceLoader.getImageIcon("toolbar/text/bold_style_font.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);
        italic = new TButton(ResourceLoader.getImageIcon("toolbar/text/italic_style_font.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);
        underline = new TButton(ResourceLoader.getImageIcon("toolbar/text/underline_style_font.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);
        strike = new TButton(ResourceLoader.getImageIcon("toolbar/text/strike_style_font.png"), TButton.Type.SELECTABLE_AND_UNSELECTABLE);

        setInsets(10, 0, 7, 0);
        add(nameBox, 0, 1, 5, 1);

        setInsets(0, 0, 0, 0);
        add(sizeBox, 0, 2, 1, 1);
        add(bold, 1, 2, 1, 1);
        add(italic, 2, 2, 1, 1);
        add(underline, 3, 2, 1, 1);
        add(strike, 4, 2, 1, 1);

        add(label, 0, 4, 5, 1);
        label.setHorizontalAlignment(JLabel.CENTER);
        {
            JLabel gapBottom = new JLabel();
            gapBottom.setPreferredSize(new Dimension(2, 1));
            add(gapBottom, 0, 3, 5, 1, 1.0, 1.0);
        }
    }

    private void setupSize() {
        Integer[] size = new Integer[] {10, 12, 14, 16, 18, 20, 24, 28, 36, 72};
        sizeBox = new JComboBox<>(size);
        sizeBox.setSelectedItem(0);
        sizeBox.setEditable(true);
        sizeBox.setBorder(new LineBorder(theme.getBorderColor(), 1));
        sizeBox.setPreferredSize(new Dimension(43, 22));
    }


}
