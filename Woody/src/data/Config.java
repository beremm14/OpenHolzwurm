package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 *
 * @author emil
 */
public class Config implements JsonExport {
    
    private static Config instance;
    
    private Unit unit;
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private Config() {
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Unit", unit.name());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        JsonObject jsonObj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            jsonObj = jsonReader.readObject();
        }
        unit = Unit.valueOf(jsonObj.getString("Unit"));
    }
    
}
