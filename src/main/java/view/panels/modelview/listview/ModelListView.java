package view.panels.modelview.listview;

import view.GraphicsView;
import view.color.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class ModelListView extends JPanel {
    public ModelListView() {
        setBackground(Colors.BACKGROUND);
        var layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
    }
}
