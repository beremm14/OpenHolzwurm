package data.types;

/**
 *
 * @author emil
 */
public class TypeLength extends Type {

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

}
