package ui;

import javax.swing.*;
import java.awt.*;

public class PropertyList extends JList {

    public PropertyList(DefaultListModel list) {
        super(list);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFixedCellWidth(780);
        setBackground(new Color(45, 47, 48));
        setFont(new Font("Avenir", 1, 18));
        setForeground(Color.white);
    }
}
