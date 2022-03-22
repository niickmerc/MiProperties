package ui;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private static final Font LABEL_FONT = new Font("Avenir", 1, 20);

    public Label(String text) {
        super(text);
        stylePanel(this);
    }

    public Label(String text, int fontSize) {
        super(text);
        stylePanel(this, fontSize);
    }


    private void stylePanel(Label label) {
        label.setForeground(Color.white);
        label.setFont(LABEL_FONT);
    }

    private void stylePanel(JLabel label, int fontSize) {
        label.setForeground(Color.white);
        label.setFont(new Font("Avenir", 1, fontSize));
    }
}
