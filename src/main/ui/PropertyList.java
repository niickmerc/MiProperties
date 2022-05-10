package ui;

import model.Portfolio;
import model.Property;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PropertyList extends JList {

    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);

    // EFFECTS: creates a new list for displaying a user's portfolio data
    public PropertyList(DefaultListModel list) {
        super(list);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFixedCellWidth(780);
        setVisibleRowCount(15);
        setBackground(BACKGROUND_COLOR);
        setFont(new Font("Avenir", 1, 18));
        setForeground(Color.white);
    }
}
