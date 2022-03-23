package ui;

import javax.swing.*;
import java.awt.*;


public class PropertyList extends JList {

    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);

    public PropertyList(DefaultListModel list) {
        super(list);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFixedCellWidth(780);
        setBackground(BACKGROUND_COLOR);
        setFont(new Font("Avenir", 1, 18));
        setForeground(Color.white);
    }
}
