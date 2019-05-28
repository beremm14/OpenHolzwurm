package data.types;

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

    public double calcValue(double length, double width) {
        return length * width;
    }

    @Override
    public String toString() {
        return "Fläche";
    }

}
