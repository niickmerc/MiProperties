package ui;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private static final Font LABEL_FONT = new Font("Avenir", 1, 20);

    public Label(String text) {
        super(text);
        setForeground(Color.white);
        setFont(LABEL_FONT);
    }

    public Label(String text, int fontSize) {
        super(text);
        setForeground(Color.white);
        setFont(new Font("Avenir", 1, fontSize));
    }
}
