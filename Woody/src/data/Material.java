package data;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Material implements Comparable<Material>, JsonObjAble {
    
    private final Preset preset;
    private final double value;

    public Material(Preset preset, double value) {
        this.preset = preset;
        this.value = value;
    }

    public Preset getPreset() {
        return preset;
    }

    public double getValue() {
        return value;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        b.add("Preset", preset.toJsonObject());
        b.add("Value", value);
        return b.build();
    }

    @Override
    public int compareTo(Material o) {
        return this.getPreset().getName().compareTo(o.getPreset().getName());
    }

}
