package mspaint.gui.sidebar;

import java.awt.Dimension;
import javax.swing.JButton;

import mspaint.helper.TButton;
import mspaint.helper.TSeparator;
import mspaint.style.Style;
import mspaint.theme.Theme;
import com.gmail.mohitsainiknl2.util.gui.GridBagPanel;

public class FilterSettings extends GridBagPanel {
    private Theme theme;
    
    public FilterSettings(Style style, Theme theme) {
        super();
        this.theme = theme;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);


        TButton b = new TButton("Settings", TButton.Type.NOT_SELECTABLE);
        b.setHorizontalAlignment(JButton.CENTER);
        b.setEnabled(false);
        b.setPreferredSize(new Dimension(100, 100));
        add(b, 0, 0, 1, 1, 1.0, 1.0);
    }

    
    /**
     * Appends a separator of a specified size to the end
     * of the tool bar.
     *
     * @param size the <code>Dimension</code> of the separator
     */
    public void addSeparator(int gridx, int gridy) {
        TSeparator s = new TSeparator(9, 92, 0, theme.getBorderColor(), TSeparator.VERTICAL);
        add(s, gridx, gridy);
    }
}
