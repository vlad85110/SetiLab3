package view.panels.modelview;

import reflection.Mapper;
import view.color.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelView extends JPanel {
    protected final List<JPanel> listAttrs;

    protected ModelView(Object model, boolean isNeedStrut, int strutLen, boolean fieldNameNeed) {
        setBackground(Colors.ELEMENT);
        var objMap = Mapper.getAsMap(model);
        listAttrs = new ArrayList<>();

        for (var item : objMap.entrySet()) {
            String text;
            if (fieldNameNeed) {
                text = item.getValue().toString();
            } else  {
               text = item.getKey() + ": " + item.getValue();
            }
            var label = new JLabel(text);

            var panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(Colors.ELEMENT);
            panel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START);
            panel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);
            panel.add(label);
            listAttrs.add(panel);
            add(panel);

            if (isNeedStrut) {
                add(Box.createHorizontalStrut(strutLen));
            }
        }
    }

    public ModelView(Object model, int strutLen) {
        this(model, true, strutLen, false);
    }

    public ModelView(Object model, boolean isFieldNameNeed) {
        this(model, false, 0, isFieldNameNeed);
    }

    public ModelView(Object model) {
        this(model, false, 0, false);
    }

    public List<JPanel> getListAttrs() {
        return listAttrs;
    }
}
