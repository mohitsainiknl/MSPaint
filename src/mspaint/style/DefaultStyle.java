package mspaint.style;

import java.awt.Font;

public class DefaultStyle implements Style {
    private Font font = new Font("Segoe UI", Font.PLAIN, 11);

    public Font getFont() {
        return font;
    }
}
