package data.types;

import data.Config;

/**
 *
 * @author emil
 */
public class TypeLength extends Type {
    
    private static TypeLength instance;
    
    public static TypeLength getInstance() {
        if (instance == null) {
            instance = new TypeLength();
        }
        return instance;
    }
    
    private TypeLength() {}

    @Override
    public String getKey() {
        return "Length";
    }

    @Override
    public String getDisplayName() {
        return "Länge";
    }

    @Override
    public String getUnit() {
        return "m";
    }

    @Override
    public String toString() {
        return "Länge";
    }
    
    public double calcValue(double length) {
        return Config.getInstance().getUnit().getMeter(length);
    }

}
