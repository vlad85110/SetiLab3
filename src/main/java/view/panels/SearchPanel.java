package view.panels;

import view.GraphicsView;
import view.color.Colors;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private final JTextField textField;
    private final GraphicsView frame;
    private final JButton button;

    public SearchPanel(GraphicsView frame) {
        this.frame = frame;

        setBackground(Colors.BACKGROUND);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(10000, 20));
        textField.setBackground(Colors.SEARCH);
        add(textField);

        button = new JButton("search");
        button.addActionListener(e -> search());
        add(button);
    }

    public JTextField getTextField() {
        return textField;
    }

    public void search() {
        if (!textField.getText().equals("")) {
            frame.setAction("search " + textField.getText());
        }
    }
}
