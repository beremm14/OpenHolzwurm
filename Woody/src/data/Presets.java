package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emil
 */
public class Presets {
    
    private static Presets instance;
    
    private final List<Preset> presets = new ArrayList<>();
    
    public static Presets getInstance() {
        if (instance == null) {
            instance = new Presets();
        }
        return instance;
    }

    private Presets() {}

    public int size() {
        return presets.size();
    }

    public boolean add(Preset e) {
        return presets.add(e);
    }

    public Preset get(int index) {
        return presets.get(index);
    }

    public Preset set(int index, Preset element) {
        return presets.set(index, element);
    }

    public Preset remove(int index) {
        return presets.remove(index);
    }

}
