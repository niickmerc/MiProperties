package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

// REFERENCE: This code was developed with some references to the CPSC 210 JsonSerializationDemo project
// Source Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo