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
public class Product implements Comparable<Product>, JsonExport, JsonObjAble {
    
    private final String name;
    private final List<Material> materials = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }
    
    public Product(JsonObject input) {
        this.name = input.getString("Name");
        JsonArray array = input.getJsonArray("Materials");
        for (JsonValue value : array) {
            JsonObject jobj = value.asJsonObject();
            
            JsonObject presetObj = jobj.getJsonObject("Preset");
            
            String presetName = presetObj.getString("Name");
            Preset preset = null;
            for (Preset p : Presets.getInstance().getPresets()) {
                if (p.getName().equals(presetName)) {
                    preset = p;
                    break;
                }
            }
            
            double materialValue = jobj.getJsonNumber("Value").doubleValue();
            
            materials.add(new Material(preset, materialValue));
        }
        Collections.sort(materials);
    }

    public String getName() {
        return name;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public double getPrice() {
        double price = 0;
        for (Material m : materials) {
            price += m.getPrice();
        }
        return price;
    }
    
    public int size() {
        return materials.size();
    }

    public boolean isEmpty() {
        return materials.isEmpty();
    }

    public boolean add(Material e) {
        return materials.add(e);
    }

    public Material get(int index) {
        return materials.get(index);
    }

    public Material remove(int index) {
        return materials.remove(index);
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        Collections.sort(materials);
        
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Material m : materials) {
            ab.add(m.toJsonObject());
        }
        JsonArray values = ab.build();
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Materials", values);
        JsonObject json = ob.build();
        
        w.write(json.toString());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        materials.clear();
        
        JsonObject jsonObj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            jsonObj = jsonReader.readObject();
        }
        
        JsonArray array = jsonObj.getJsonArray("Materials");
        for (JsonValue value : array) {
            JsonObject jobj = value.asJsonObject();
            
            JsonObject presetObj = jobj.getJsonObject("Preset");
            
            String presetName = presetObj.getString("Name");
            Preset preset = null;
            for (Preset p : Presets.getInstance().getPresets()) {
                if (p.getName().equals(presetName)) {
                    preset = p;
                    break;
                }
            }
            
            double materialValue = jobj.getJsonNumber("Value").doubleValue();
            
            materials.add(new Material(preset, materialValue));
        }
        Collections.sort(materials);
    }

    @Override
    public JsonObject toJsonObject() {
        Collections.sort(materials);
        
        JsonArrayBuilder ab = Json.createArrayBuilder();
        
        for (Material m : materials) {
            ab.add(m.toJsonObject());
        }
        JsonArray values = ab.build();
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Name", name);
        ob.add("Materials", values);
        return ob.build();
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }

}
