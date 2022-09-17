package view;

import exceptions.view.ViewException;
import model.Condition;

import java.util.List;

public interface View {
    void updateView(Condition condition, List<?> data) throws ViewException;
}
