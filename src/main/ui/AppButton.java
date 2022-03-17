package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppButton extends JButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public AppButton(String action) {
        super(action);
    }
}
