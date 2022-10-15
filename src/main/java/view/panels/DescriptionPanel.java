package view.panels;

import view.GraphicsView;
import view.color.Colors;

import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {
    public DescriptionPanel(String description, GraphicsView frame) {
        setBackground(Colors.BACKGROUND);
        setLayout(new BorderLayout());

        var label = new JLabel("<html>" + description + "</html>");
        label.setForeground(Colors.ELEMENT);
        label.setPreferredSize(new Dimension(100, 100));
        add(label, BorderLayout.CENTER);
        JButton back = new JButton("back");
        back.addActionListener(e -> {
            frame.closeDescription();
        });

        add(back, BorderLayout.PAGE_END);
    }
}
