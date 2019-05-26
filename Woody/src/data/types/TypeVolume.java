package data.types;

/**
 *
 * @author emil
 */
public class TypeVolume extends Type {

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
        return length * width * heigth;
    }

}
