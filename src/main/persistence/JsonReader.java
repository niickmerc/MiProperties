package persistence;

import model.Portfolio;
import model.Tenant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public Portfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

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

    private void addProperty(Portfolio portfolio, JSONObject jsonObject) { // HOW DO I IMPLEMENT TENANTS

        String civicAddress = jsonObject.getString("civic address");
        int marketValue = jsonObject.getInt("desired rent");
        int purchasePrice = jsonObject.getInt("market value");
        ArrayList<Tenant> tenantList = addTenants(jsonObject); // What is happening here

        portfolio.addNewProperty(civicAddress, purchasePrice, marketValue, tenantList);
    }

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
