package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author emil
 */
public class Presets implements JsonExport {
    
    private static Presets instance;
    
    private final List<Preset> presets = new ArrayList<>();
    
    public static Presets getInstance() {
        if (instance == null) {
            instance = new Presets();
        }
        return instance;
    }

    private Presets() {}

    public int size() {
        return presets.size();
    }

    public boolean add(Preset e) {
        boolean rv = presets.add(e);
        Collections.sort(presets);
        return rv;
    }

    public Preset get(int index) {
        return presets.get(index);
    }

    public Preset set(int index, Preset element) {
        Preset rv = presets.set(index, element);
        Collections.sort(presets);
        return rv;
    }

    public Preset remove(int index) {
        Preset rv = presets.remove(index);
        Collections.sort(presets);
        return rv;
    }
    
    public List<Preset> getPresets() {
        return presets;
    }

    public boolean isEmpty() {
        return presets.isEmpty();
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        Collections.sort(presets);
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Preset p : presets) {
            ab.add(p.toJsonObject());
        }
        JsonArray values = ab.build();
        System.out.println(values.toString());
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Presets", values);
        JsonObject json = ob.build();
        System.out.println(json.toString());
        
        w.write(json.toString());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        presets.clear();
        
        JsonObject jsonObj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            jsonObj = jsonReader.readObject();
        }
        
        JsonArray array = jsonObj.getJsonArray("Presets");
        for (JsonValue value : array) {
            JsonObject jobj = value.asJsonObject();
            
            String name = jobj.getString("Name");
            double price = jobj.getJsonNumber("Price").doubleValue();
            String type = jobj.getString("Type");
            
            presets.add(new Preset(name, type, price));
        }
        Collections.sort(presets);
    }

}
