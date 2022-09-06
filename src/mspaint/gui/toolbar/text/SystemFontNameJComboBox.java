package mspaint.gui.toolbar.text;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class SystemFontNameJComboBox extends JComboBox<String> {

    public SystemFontNameJComboBox() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
        for (String string : fontFamilyNames) {
            addItem(string);
        }
        setSelectedItem(0);
        setRenderer(new ComboRenderer());
        setSelectedItem(0);
        // getEditor().selectAll();
        setEditable(true);
        setPreferredSize(new Dimension(140, 22));
    }

    public static void main(String arg[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                SystemFontNameJComboBox fontsBox = new SystemFontNameJComboBox();
                panel.add(fontsBox);
                panel.add(new JButton("Button"));

                frame.add(panel, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(400, 60));
                frame.setLocation(200, 105);
                frame.setSize(300, 100);
                frame.setVisible(true);
            }
        });
    }

    private class ComboRenderer extends BasicComboBoxRenderer {

        private ComboRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            final Object fntObj = value;
            final String fontFamilyName = (String) fntObj;
            setFont(new Font(fontFamilyName, Font.PLAIN, 16));
            setBackground(new Color(255, 255, 255, 255));
            setForeground(Color.BLACK);
            return this;
        }
    }
}
