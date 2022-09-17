package view;

import exceptions.view.ViewException;
import model.Condition;

import javax.swing.*;
import java.util.List;

public class GraphicsView extends JFrame implements View {
    private String action;

    @Override
    public void updateView(Condition condition, List<?> data) throws ViewException {

    }

    public synchronized String waitAction() {
        if (action == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        var action = getAction();
        resetAction();
        return action;
    }

    public String getAction() {
        return action;
    }

    public synchronized void setAction(String action) {
        this.action = action;
        notify();
    }

    public void resetAction() {
        this.action = null;
    }
}
