package persistence;

import model.*;
import model.Tenant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws an IOException if an error occurs reading data from file
    public Portfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        Portfolio portfolio = new Portfolio();
        addProperties(portfolio, jsonObject);
        return portfolio;
    }

    // MODIFIES: this
    // EFFECTS: parses properties from JSON object and adds them to portfolio
    private void addProperties(Portfolio portfolio, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(portfolio, nextProperty);
        }
    }

    // EFFECTS: parses property from JSON object and adds it to the portfolio
    private void addProperty(Portfolio portfolio, JSONObject jsonObject) {
        String civicAddress = jsonObject.getString("civic address");
        int marketValue = jsonObject.getInt("desired rent");
        int purchasePrice = jsonObject.getInt("market value");

        ArrayList<Tenant> tenantList = addTenants(jsonObject);

        portfolio.addNewProperty(civicAddress, purchasePrice, marketValue, tenantList);
    }

    // EFFECTS: parses tenants from JSON object and adds it to tenantList
    private ArrayList<Tenant> addTenants(JSONObject jsonObject) {
        ArrayList<Tenant> tenantList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("tenants");

        for (Object json : jsonArray) {
            JSONObject nextTenant = (JSONObject) json;
            String name = nextTenant.getString("tenant name");
            Tenant newTenant = new Tenant(name);
            tenantList.add(newTenant);
        }
        return tenantList;
    }
}

// REFERENCE: This code was developed with some references to the CPSC 210 JsonSerializationDemo project
// Source Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
