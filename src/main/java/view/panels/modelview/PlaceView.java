package view.panels.modelview;

import model.Place;
import view.GraphicsView;
import view.color.Colors;
import view.listeners.MouseListener;
import view.listeners.PlaceMouseListener;

import java.util.Arrays;

public class PlaceView extends ModelView {
    private boolean isActive = false;

    public PlaceView(Place model, GraphicsView frame) {
        super(model, true);
        var mouseListener = new PlaceMouseListener(this, "desc " + model.getXid(),
                Colors.ELEMENT, Colors.ACTIVE, frame, true);
        listAttrs.forEach(e -> e.addMouseListener(mouseListener));
        addMouseListener(mouseListener);
    }

    public void setActive() {
        isActive = true;
        setBackground(Colors.ELEMENT);
        Arrays.stream(getComponents()).forEach(el -> el.setBackground(Colors.ELEMENT));
    }

    public boolean isActive() {
        return isActive;
    }
}
