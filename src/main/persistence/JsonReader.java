//package persistence;
//
//import model.Portfolio;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//public class JsonReader {
//    private String source;
//
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    public Portfolio read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parsePortfolio(jsonObject);
//    }
//
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses portfolio from JSON object and returns it
//    private Portfolio parsePortfolio(JSONObject jsonObject) {
//        Portfolio portfolio = new Portfolio();
//        addProperties(portfolio, jsonObject);
//        return portfolio;
//    }
//
//    private void addProperties(Portfolio portfolio, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("properties");
//        for (Object json : jsonArray) {
//            JSONObject nextProperty = (JSONObject) json;
//            addProperty(portfolio, nextProperty);
//        }
//    }
//
//    private void addProperty(Portfolio portfolio, JSONObject jsonObject) {
//        String civicAddress = jsonObject.getString("civic address");
//        int purchasePrice = jsonObject.getInt("market value");
//        int marketValue = jsonObject.getInt("desired rent");
//
//        JSONArray jsonArray = jsonObject.getJSONArray("tenants");
//        for (Object json : jsonArray) {
//            JSONObject nextProperty = (JSONObject) json;
//            addProperty(portfolio, nextProperty);
//        }
//
//        portfolio.addNewProperty(civicAddress, purchasePrice, marketValue);
//    }
//
//
//
//
//
//
//
//
//
//}
