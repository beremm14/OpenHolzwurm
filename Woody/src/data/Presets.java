package data;

import data.types.Type;
import data.types.TypeArea;
import data.types.TypeLength;
import data.types.TypePiece;
import data.types.TypeVolume;
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
        return presets.add(e);
    }

    public Preset get(int index) {
        return presets.get(index);
    }

    public Preset set(int index, Preset element) {
        return presets.set(index, element);
    }

    public Preset remove(int index) {
        return presets.remove(index);
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Preset p : presets) {
            ab.add(p.toJsonObject());
        }
        JsonArray values = ab.build();
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Presets", values);
        JsonObject json = ob.build();
        
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
            Type type;
            switch (jobj.getString("Type")) {
                case "Area": type = new TypeArea(); break;
                case "Length": type = new TypeLength(); break;
                case "Piece": type = new TypePiece(); break;
                case "Volume": type = new TypeVolume(); break;
                default: type = null;
            }
            
            presets.add(new Preset(name, type, price));
        }
        Collections.sort(presets);
    }

}
