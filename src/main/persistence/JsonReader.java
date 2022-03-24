package persistence;

import model.*;
import model.TenantOG;
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
    public PortfolioOG read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private PortfolioOG parsePortfolio(JSONObject jsonObject) {
        PortfolioOG portfolio = new PortfolioOG();
        addProperties(portfolio, jsonObject);
        return portfolio;
    }

    // MODIFIES: this
    // EFFECTS: parses properties from JSON object and adds them to portfolio
    private void addProperties(PortfolioOG portfolio, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(portfolio, nextProperty);
        }
    }

    // EFFECTS: parses property from JSON object and adds it to the portfolio
    private void addProperty(PortfolioOG portfolio, JSONObject jsonObject) {
        String civicAddress = jsonObject.getString("civic address");
        int marketValue = jsonObject.getInt("desired rent");
        int purchasePrice = jsonObject.getInt("market value");

        ArrayList<TenantOG> tenantList = addTenants(jsonObject);

        portfolio.addNewProperty(civicAddress, purchasePrice, marketValue, tenantList);
    }

    // EFFECTS: parses tenants from JSON object and adds it to tenantList
    private ArrayList<TenantOG> addTenants(JSONObject jsonObject) {
        ArrayList<TenantOG> tenantList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("tenants");

        for (Object json : jsonArray) {
            JSONObject nextTenant = (JSONObject) json;
            String name = nextTenant.getString("tenant name");
            TenantOG newTenant = new TenantOG(name);
            tenantList.add(newTenant);
        }
        return tenantList;
    }
}

// REFERENCE: This code was developed with some references to the CPSC 210 JsonSerializationDemo project
// Source Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
