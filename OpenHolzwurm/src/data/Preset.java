package data;

import data.types.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Preset implements Comparable<Preset>, JsonObjAble {

    private final String name;
    private final Type type;
    private final double price;

    public Preset(String name, Type type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    public Preset(String name, String type, Double price) {
        this.name = name;
        this.type = Type.getType(type);
        this.price = price;
    }
    
    public Preset(String name, String type, String price) {
        this.name = name;
        this.type = Type.getType(type);
        this.price = Double.parseDouble(price);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        b.add("Name", name);
        b.add("Type", type.getKey());
        b.add("Price", price);
        return b.build();
    }

    @Override
    public int compareTo(Preset o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }

}
