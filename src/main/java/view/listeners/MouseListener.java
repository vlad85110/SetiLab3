package view.listeners;

import view.GraphicsView;
import view.panels.modelview.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    protected final ModelView component;
    protected final String action;
    protected final Color color;
    protected final Color enteredColor;
    protected final GraphicsView frame;

    protected final boolean changeOnClick;

    public MouseListener(ModelView component, String action, Color color, Color enteredColor, GraphicsView frame,
                         boolean changeOnClick) {
        this.component = component;
        this.action = action;
        this.color = color;
        this.enteredColor = enteredColor;
        this.frame = frame;
        this.changeOnClick = changeOnClick;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(enteredColor);
        component.getListAttrs().forEach(el -> el.setBackground(enteredColor));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(color);
        component.getListAttrs().forEach(el -> el.setBackground(color));
    }

    protected void mouseExited(ModelView component) {
        component.setBackground(color);
        component.getListAttrs().forEach(el -> el.setBackground(color));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (changeOnClick) {
            mouseExited(e);
        }
        frame.setAction(action);
    }
}
