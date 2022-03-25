package ui;

import javax.swing.*;
import java.awt.*;

// Represents a label with specific styling attributes applied
public class Label extends JLabel {
    private static final Font LABEL_FONT = new Font("Avenir", 1, 20);

    // EFFECTS: constructs a new label with a given label
    public Label(String text) {
        super(text);
        setForeground(Color.white);
        setFont(LABEL_FONT);
    }

    // EFFECTS: constructs a new label with a given label
    public Label(String text, int fontSize) {
        super(text);
        setForeground(Color.white);
        setFont(new Font("Avenir", 1, fontSize));
    }
}
