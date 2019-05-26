package data;

import javax.json.JsonObject;

/**
 *
 * @author emil
 */
public interface JsonObjAble {
    
    public JsonObject toJsonObject();
    
    default public Type toType(String s) {
        switch(s) {
            case "PIECE": return Type.PIECE;
            case "LENGTH": return Type.LENGTH;
            case "AREA": return Type.AREA;
            case "VOLUME": return Type.VOLUME;
            default: throw new RuntimeException("Wrong String...");
        }
    }
}
