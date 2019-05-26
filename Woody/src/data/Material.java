package data;

/**
 *
 * @author emil
 */
public class Material {
    
    private final Preset preset;
    private final double value;

    public Material(Preset preset, double value) {
        this.preset = preset;
        this.value = value;
    }

    public Preset getPreset() {
        return preset;
    }

    public double getValue() {
        return value;
    }

}
