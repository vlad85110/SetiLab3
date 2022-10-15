package view.listeners;

import view.panels.SearchPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private final SearchPanel panel;

    public KeyboardListener(SearchPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            panel.search();
        }
    }
}
