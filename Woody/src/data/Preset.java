package data;

/**
 *
 * @author emil
 */
public class Preset {

    private final String name;
    private final Type type;
    private final double price;

    public Preset(String name, Type type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
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

}
