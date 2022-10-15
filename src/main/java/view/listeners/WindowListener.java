package view.listeners;

import view.GraphicsView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {
    private final GraphicsView view;

    public WindowListener(GraphicsView view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.setAction("exit");
    }
}
