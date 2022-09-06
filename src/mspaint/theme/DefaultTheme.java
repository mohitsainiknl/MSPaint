package mspaint.theme;

import java.awt.Color;

public class DefaultTheme implements Theme {
    private Color bgColor = new Color(245, 246, 247);
    private Color sidebarBgColor = new Color(228, 232, 235);
    private Color viewportBgColor = new Color(209 ,219 ,233);
    private Color borderColor = new Color(218, 219, 220);


    public Color getViewportBgColor() {
        return viewportBgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getSidebarBgColor() {
        return sidebarBgColor;
    }
}
