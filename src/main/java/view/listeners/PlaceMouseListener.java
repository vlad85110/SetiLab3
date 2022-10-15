package view.listeners;

import view.GraphicsView;
import view.panels.modelview.PlaceView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlaceMouseListener extends MouseListener {
    public PlaceMouseListener(PlaceView component, String action, Color color, Color enteredColor, GraphicsView frame,
                              boolean changeOnClick) {
        super(component, action, color, enteredColor, frame, changeOnClick);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (((PlaceView)component).isActive()) {
            super.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (((PlaceView)component).isActive()) {
            super.mouseExited(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (((PlaceView)component).isActive()) {
            super.mouseClicked(e);
        }
    }
}
