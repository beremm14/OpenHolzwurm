package data.types;

import data.Config;

/**
 *
 * @author emil
 */
public class TypeVolume extends Type {
    
    private static TypeVolume instance;
    
    public static TypeVolume getInstance() {
        if (instance == null) {
            instance = new TypeVolume();
        }
        return instance;
    }
    
    private TypeVolume() {}

    @Override
    public String getKey() {
        return "Volume";
    }

    @Override
    public String getDisplayName() {
        return "Volumen";
    }

    @Override
    public String getUnit() {
        return "m³";
    }

    @Override
    public String toString() {
        return "Volumen";
    }

    @Override
    public double calcValue(double[] values) {
        return Config.getInstance().getUnit().getMeter(values[0]) * Config.getInstance().getUnit().getMeter(values[1]) * Config.getInstance().getUnit().getMeter(values[2]);
    }

}
