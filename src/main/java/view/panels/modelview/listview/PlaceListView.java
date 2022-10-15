package view.panels.modelview.listview;

import model.Place;
import view.GraphicsView;
import view.color.Colors;
import view.panels.modelview.PlaceView;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceListView extends ModelListView {
    private final Map<String, PlaceView> placeViewList = new HashMap<>();
    public PlaceListView(List<Place> places, GraphicsView frame) {
        for (Place place : places) {
            Color color;
            var mView = new PlaceView(place, frame);

            if (place.getDescription() != null) {
                color = Colors.ELEMENT;
                mView.setActive();
            } else {
                color = Colors.INACTIVE;
            }

            mView.setBackground(color);
            Arrays.stream(mView.getComponents()).toList().forEach(el -> el.setBackground(color));
            placeViewList.put(place.getXid(), mView);
            mView.setMaximumSize(new Dimension(7000, 30));

            add(mView);
            add(Box.createVerticalStrut(10));
        }
    }

    public void setActive(String xid) {
        placeViewList.get(xid).setActive();
    }

}
