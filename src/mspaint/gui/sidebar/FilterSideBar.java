package mspaint.gui.sidebar;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import mspaint.helper.TSeparator;
import mspaint.style.Style;
import mspaint.theme.Theme;

public class FilterSideBar extends JPanel {
    private final Style style;
    private final Theme theme;


    public FilterSideBar(Style style, Theme theme) {
        super(new BorderLayout());
        this.style = style;
        this.theme = theme;
        initialWork();
    }

    private void initialWork() {
        setOpaque(false);

        add(new FilterSettings(style, theme), BorderLayout.NORTH);
        add(new FiltersArea(), BorderLayout.CENTER);

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
