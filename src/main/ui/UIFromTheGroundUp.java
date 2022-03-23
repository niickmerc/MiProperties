package ui;

import persistence.*;

import javax.swing.*;

public class UIFromTheGroundUp extends JFrame  {

    private static final String JSON_STORE = "./data/portfolio.json";

    public UIFromTheGroundUp() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

        initializeGraphics();
        loadProperties();


    }

    private void initializeGraphics() {
    }

    private void loadProperties() {
    }
}
