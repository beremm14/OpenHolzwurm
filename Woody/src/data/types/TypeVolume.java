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

    public double calcValue(double length, double width, double heigth) {
        return Config.getInstance().getUnit().getMeter(length) * Config.getInstance().getUnit().getMeter(width) * Config.getInstance().getUnit().getMeter(heigth);
    }

    @Override
    public String toString() {
        return "Volumen";
    }

}
