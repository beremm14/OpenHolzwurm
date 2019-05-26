package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emil
 */
public class UsedMaterials {
    
    private static UsedMaterials instance;
    
    private final List<Material> materials = new ArrayList<>();
    
    public static UsedMaterials getInstance() {
        if (instance == null) {
            instance = new UsedMaterials();
        }
        return instance;
    }
    
    private UsedMaterials() {}

    public int size() {
        return materials.size();
    }

    public boolean isEmpty() {
        return materials.isEmpty();
    }

    public boolean add(Material e) {
        return materials.add(e);
    }

    public Material get(int index) {
        return materials.get(index);
    }

    public Material remove(int index) {
        return materials.remove(index);
    }

}
