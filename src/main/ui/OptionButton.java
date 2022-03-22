package ui;

import javax.swing.*;
import java.awt.*;

public class OptionButton extends JButton {

    public OptionButton(String name) {
        super(name);
        setFont(new Font("Avenir", 1, 15));
        setForeground(Color.white);
        setBackground(Color.BLUE);
        setVerticalAlignment(SwingConstants.CENTER);
    }
}
