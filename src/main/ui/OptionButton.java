package ui;

import javax.swing.*;
import java.awt.*;

// Represents a Button with specific styling attributes applied
public class OptionButton extends JButton {

    // EFFECTS: creates a new button with a given label and custom styling options
    public OptionButton(String name) {
        super(name);
        setFont(new Font("Avenir", 1, 15));
        setForeground(Color.white);
        setVerticalAlignment(SwingConstants.CENTER);
    }
}
