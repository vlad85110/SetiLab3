package view.panels;

import view.color.Colors;

import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {
    public WeatherPanel() {
        setBackground(Colors.BACKGROUND);
        setPreferredSize(new Dimension(10, 30));
    }
}
