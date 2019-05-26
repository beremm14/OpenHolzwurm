package data.types;

/**
 *
 * @author emil
 */
public class TypeArea extends Type {

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
    
}
