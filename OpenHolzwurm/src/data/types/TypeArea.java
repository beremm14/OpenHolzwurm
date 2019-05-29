package data.types;

import data.Config;

/**
 *
 * @author emil
 */
public class TypeArea extends Type {
    
    private static TypeArea instance;
    
    public static TypeArea getInstance() {
        if (instance == null) {
            instance = new TypeArea();
        }
        return instance;
    }
    
    private TypeArea() {}

    @Override
    public String getKey() {
        return "Area";
    }

    @Override
    public String getDisplayName() {
        return "Fläche";
    }

    @Override
    public String getUnit() {
        return "m²";
    }

    @Override
    public String toString() {
        return "Fläche";
    }

    @Override
    public double calcValue(double[] values) {
        return Config.getInstance().getUnit().getMeter(values[0]) * Config.getInstance().getUnit().getMeter(values[1]);
    }

}
