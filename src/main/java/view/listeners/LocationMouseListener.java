package view.listeners;

import view.GraphicsView;
import view.panels.modelview.LocationView;
import view.panels.modelview.listview.LocationListView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LocationMouseListener extends MouseListener {
    private final LocationListView locationListView;

    public LocationMouseListener(LocationView component, String action, Color color, Color enteredColor,
                                 GraphicsView frame, boolean changeOnClick, LocationListView locationListView) {
        super(component, action, color, enteredColor, frame, changeOnClick);
        this.locationListView = locationListView;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!component.equals(locationListView.getChosen())) {
            super.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!component.equals(locationListView.getChosen())) {
            super.mouseExited(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!component.equals(locationListView.getChosen())) {
            super.mouseClicked(e);
            super.mouseEntered(e);
            var prev = locationListView.getChosen();
            if (prev != null) {
                super.mouseExited(prev);
            }
            locationListView.setChosen((LocationView)component);
        }
    }
}
