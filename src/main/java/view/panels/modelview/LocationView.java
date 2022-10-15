package view.panels.modelview;

import model.Location;
import view.GraphicsView;
import view.color.Colors;
import view.listeners.LocationMouseListener;
import view.listeners.MouseListener;
import view.panels.modelview.listview.LocationListView;

import javax.swing.*;
import java.awt.*;

public class LocationView extends ModelView {
    public LocationView(Location location, GraphicsView frame, int index, LocationListView parent) {
        super(location);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        var mouseListener = new LocationMouseListener(this, "choose " + index, Colors.ELEMENT, Colors.ACTIVE,
                frame, false, parent);
        listAttrs.forEach(e -> e.addMouseListener(mouseListener));
    }

}
