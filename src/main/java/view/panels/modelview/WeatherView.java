package view.panels.modelview;

import model.Weather;
import view.color.Colors;

import javax.swing.*;

public class WeatherView extends JPanel{
    public WeatherView(Weather model) {
        setBackground(Colors.BACKGROUND);
        String sgnStr;
        var temp = model.getTemp();

        if (temp >= 0) {
            sgnStr = "+";
        } else {
            sgnStr = "";
        }

        var weatherStr = "TEMP  " + sgnStr + "%.0f";
        var formatted = String.format(weatherStr, temp);
        var label = new JLabel("<html>" + formatted + "</html>");
        label.setForeground(Colors.ACTIVE);
        add(label);
    }
}
