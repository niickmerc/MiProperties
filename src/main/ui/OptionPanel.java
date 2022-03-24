package ui;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {

    // EFFECTS: creates a new panel for displaying option buttons
    public OptionPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setPreferredSize(new Dimension(WIDTH, 50));
        setBackground(Color.BLACK);
    }
}
