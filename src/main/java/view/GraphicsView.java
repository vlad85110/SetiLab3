package view;

import model.Location;
import model.Place;
import model.Weather;
import view.color.Colors;
import view.listeners.KeyboardListener;
import view.listeners.WindowListener;
import view.location.WindowLocation;
import view.panels.*;
import view.panels.modelview.WeatherView;
import view.panels.modelview.listview.LocationListView;
import view.panels.modelview.listview.PlaceListView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicsView extends JFrame implements View {
    private String action;

    private final JPanel main = new JPanel(new BorderLayout());
    private final WeatherPanel weatherPanel = new WeatherPanel();
    private final LocationsListPanel locationsListPanel = new LocationsListPanel();
    private final PlacesListPanel placesListPanel = new PlacesListPanel();

    private PlaceListView placeListView;

    public GraphicsView() throws HeadlessException {
        super("locations");
        setSize(950, 450);
        setLayout(new GridLayout(1,1));
        setMinimumSize(new Dimension(985, 50));

        SearchPanel searchPanel = new SearchPanel(this);
        KeyboardListener listener = new KeyboardListener(searchPanel);
        main.addKeyListener(listener);
        main.setFocusable(true);
        main.requestFocusInWindow();

        searchPanel.getTextField().addKeyListener(listener);

        JPanel pageStartPanel = new JPanel(new BorderLayout());
        pageStartPanel.add(searchPanel, BorderLayout.CENTER);
        JPanel sepPanel = new JPanel();
        sepPanel.setBackground(Colors.SEPARATOR);
        pageStartPanel.add(sepPanel, BorderLayout.PAGE_END);
        sepPanel.setPreferredSize(new Dimension(10, 1));

        JPanel lineStartPanel = new JPanel(new BorderLayout());
        lineStartPanel.add(locationsListPanel, BorderLayout.CENTER);
        sepPanel = new JPanel();
        sepPanel.setBackground(Colors.SEPARATOR);
        lineStartPanel.add(sepPanel, BorderLayout.LINE_END);
        sepPanel.setPreferredSize(new Dimension(1, 10));

        LocationInfoPanel locationInfoPanel = new LocationInfoPanel();
        main.add(locationInfoPanel, BorderLayout.CENTER);
        main.add(pageStartPanel, BorderLayout.PAGE_START);
        main.add(lineStartPanel, BorderLayout.LINE_START);

        placesListPanel.setLayout(new GridLayout(1, 1));
        locationInfoPanel.add(weatherPanel, BorderLayout.PAGE_START);
        locationInfoPanel.add(placesListPanel, BorderLayout.CENTER);

        setContentPane(main);
        WindowLocation.centreWindow(this);
        setVisible(true);

        addWindowListener(new WindowListener(this));
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

    @Override
    public void updateLocations(List<Location> locations) {
        Runnable task = () -> {
            locationsListPanel.removeAll();
            locationsListPanel.repaint();

            var listView = new LocationListView(locations, this);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(listView);
            scrollPane.setBackground(Colors.BACKGROUND);
            scrollPane.setForeground(Colors.BACKGROUND);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            locationsListPanel.add(scrollPane, BorderLayout.CENTER);
            locationsListPanel.setVisible(true);
            setVisible(true);
        };

        SwingUtilities.invokeLater(task);
    }

    @Override
    public void updateWeather(Weather weather) {
        Runnable task = () -> {
            weatherPanel.removeAll();
            weatherPanel.repaint();
            WeatherView weatherView = new WeatherView(weather);
            weatherPanel.add(weatherView);
            weatherPanel.setVisible(true);
            setVisible(true);
        };

        SwingUtilities.invokeLater(task);
    }

    @Override
    public void updatePlaces(List<Place> places) {
        Runnable task = () -> {
            placesListPanel.removeAll();
            placesListPanel.repaint();

            //placesListPanel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);

            var listView = new PlaceListView(places, this);
            this.placeListView = listView;

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(listView);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setBackground(Colors.BACKGROUND);
            scrollPane.setForeground(Colors.BACKGROUND);

            placesListPanel.add(scrollPane);
            main.setVisible(true);
            setVisible(true);
        };

        SwingUtilities.invokeLater(task);
    }

    @Override
    public void updatePlaceDescription(String description) {
        Runnable task = () -> {
            DescriptionPanel descriptionPanel = new DescriptionPanel(description, this);
            setContentPane(descriptionPanel);
            setVisible(true);
        };

        SwingUtilities.invokeLater(task);
    }

    public void closeDescription() {
        setContentPane(main);
        setVisible(true);
    }

    @Override
    public void clearPlaces() {
        placesListPanel.removeAll();
        placesListPanel.repaint();
    }

    @Override
    public void clearWeather() {
        weatherPanel.removeAll();
        weatherPanel.repaint();
    }

    @Override
    public void setActiveDescription(String xid) {
        placeListView.setActive(xid);
    }
}
