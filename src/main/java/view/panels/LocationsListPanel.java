package view.panels;

import view.color.Colors;

import javax.swing.*;
import java.awt.*;

public class LocationsListPanel extends JPanel {
    public LocationsListPanel() {
        setPreferredSize(new Dimension(250, 10));
        setLayout(new BorderLayout());
        setBackground(Colors.BACKGROUND);
    }
}
