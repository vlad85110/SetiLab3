package view.panels.modelview.listview;

import model.Location;
import view.GraphicsView;
import view.panels.modelview.LocationView;
import view.panels.modelview.PlaceView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LocationListView extends ModelListView {
    private LocationView chosen;

    public LocationListView(List<Location> models, GraphicsView frame) {
        add(Box.createVerticalStrut(10));

        for (int i = 0; i < models.size(); i++) {
            var mView = new LocationView(models.get(i), frame, i, this);
            mView.setPreferredSize(new Dimension(230, 65));
            mView.setMaximumSize(new Dimension(230, 65));
            add(mView);
            add(Box.createVerticalStrut(10));
        }
    }

    public synchronized LocationView getChosen() {
        return chosen;
    }


    public synchronized void setChosen(LocationView chosen) {
        this.chosen = chosen;
    }
}
